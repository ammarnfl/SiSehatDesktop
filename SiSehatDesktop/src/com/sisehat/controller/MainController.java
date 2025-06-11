package com.sisehat.controller;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.*;
import com.sisehat.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainController {
    private MainView view;
    private NavigationController navigationController;

    public MainController(MainView view, NavigationController navigationController) {
        this.view = view;
        this.navigationController = navigationController;
        this.view.getDiagnoseButton().addActionListener(e -> performDiagnosis());
    }

    private void performDiagnosis() {
        // 1. KUMPULKAN GEJALA YANG DIPILIH PENGGUNA
        List<Symptom> selectedSymptoms = new ArrayList<>();
        for (Map.Entry<JCheckBox, Symptom> entry : view.getSymptomMap().entrySet()) {
            if (entry.getKey().isSelected()) {
                selectedSymptoms.add(entry.getValue());
            }
        }

        // Ambil hanya ID dari gejala yang dipilih untuk perbandingan
        List<Integer> selectedSymptomIds = selectedSymptoms.stream()
                .map(Symptom::getId)
                .collect(Collectors.toList());

        // 2. PROSES PENCARI SKOR UNTUK SETIAP PENYAKIT
        List<Diagnosis> results = new ArrayList<>();
        for (Disease disease : AppDatabase.diseases) {
            int score = 0;
            for (int symptomId : disease.getRelatedSymptomIds()) {
                if (selectedSymptomIds.contains(symptomId)) {
                    score++;
                }
            }
            // Hanya masukkan penyakit yang punya setidaknya 1 gejala cocok
            if (score > 0) {
                results.add(new Diagnosis(disease, score, disease.getRelatedSymptomIds().size()));
            }
        }

        // 3. URUTKAN HASIL DARI SKOR TERTINGGI
        results.sort((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));
        // 4. BUAT KONTEN UNTUK DI POP-UP
        StringBuilder resultText = new StringBuilder();
        resultText.append("--- HASIL DIAGNOSA SEMENTARA ---\n\n");

        List<Disease> top3Diseases = new ArrayList<>(); // Kita simpan 3 penyakit teratas

        if (results.isEmpty()) {
            resultText.append("Tidak ditemukan penyakit yang cocok dengan kombinasi gejala Anda.");
        } else {
            resultText.append("Berdasarkan gejala yang Anda pilih, berikut kemungkinan teratas:\n\n");
            int count = 0;
            for (Diagnosis result : results) {
                if (count < 3) {
                    top3Diseases.add(result.getDisease()); // Simpan penyakitnya
                    resultText.append(String.format("%d. %s\n", count + 1, result.getDisease().getName()));
                    resultText.append(String.format("   Tingkat Kecocokan: %d dari %d gejala (%.1f%%)\n\n",
                            result.getScore(),
                            result.getDisease().getRelatedSymptomIds().size(),
                            result.getMatchPercentage()));
                    count++;
                } else {
                    break;
                }
            }
            resultText.append("\nDISCLAIMER:\nSegera konsultasikan ke dokter untuk penanganan lebih lanjut.");
        }

        // 5. TAMPILKAN HASIL DI JENDELA POP-UP (JOptionPane)
        // Buat area teks yang bisa di-scroll untuk jaga-jaga jika teksnya panjang
        JTextArea textArea = new JTextArea(resultText.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 250)); // Ukuran pop-up

        // Opsi tombol custom
        String[] options = {"Simpan ke Riwayat & Kembali", "Tutup"};

        int userChoice = JOptionPane.showOptionDialog(
                view, // Parent component
                scrollPane, // Pesan yang ditampilkan (area teks kita)
                "Hasil Diagnosa", // Judul dialog
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        // 6. LAKUKAN AKSI BERDASARKAN PILIHAN PENGGUNA
        if (userChoice == JOptionPane.YES_OPTION) { // Indeks 0 -> "Simpan ke Riwayat & Kembali"
            // Buat objek History baru
            String userEmail = SessionManager.currentUser.getEmail();
            History newHistory = new History(userEmail, selectedSymptoms, top3Diseases);

            // Simpan ke database palsu kita
            AppDatabase.histories.add(newHistory);

            JOptionPane.showMessageDialog(view, "Riwayat diagnosa berhasil disimpan!");

            // Kembali ke dashboard
            navigationController.showCard("DASHBOARD");
        }
        // Jika user menekan "Tutup" (NO_OPTION) atau menutup dialog, tidak terjadi apa-apa.
    }
}