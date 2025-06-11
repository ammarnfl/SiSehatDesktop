package com.sisehat.view;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.Symptom;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;

// Kelas MainView sekarang adalah sebuah JPanel itu sendiri
public class MainView extends JPanel {

    // 1. Deklarasikan semua komponen yang kita butuhkan sebagai properti kelas
    private JButton diagnoseButton;
    private final Map<JCheckBox, Symptom> symptomMap = new LinkedHashMap<>();

    // 2. Constructor: Tempat semua komponen dibuat dan disusun
    public MainView() {
        // Mengatur layout utama untuk panel ini menjadi BorderLayout dengan jarak 10 pixel
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Memberi jarak dari tepi jendela

        // --- BAGIAN UTARA: JUDUL ---
        JLabel titleLabel = new JLabel("Pilih Gejala yang Anda Rasakan:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(titleLabel, BorderLayout.NORTH);

        // --- BAGIAN TENGAH: DAFTAR GEJALA ---
        // Buat panel khusus untuk menampung checkbox
        JPanel symptomsPanel = new JPanel();
        symptomsPanel.setLayout(new BoxLayout(symptomsPanel, BoxLayout.Y_AXIS)); // Layout vertikal

        // Isi panel dengan checkbox dari database
        for (Symptom symptom : AppDatabase.symptoms) {
            JCheckBox checkBox = new JCheckBox(symptom.getName());
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            symptomsPanel.add(checkBox);
            symptomMap.put(checkBox, symptom);
        }
        // Bungkus panel gejala dengan JScrollPane agar bisa di-scroll
        JScrollPane symptomsScrollPane = new JScrollPane(symptomsPanel);
        this.add(symptomsScrollPane, BorderLayout.CENTER);

        // --- BAGIAN SELATAN: TOMBOL ---
        diagnoseButton = new JButton("Diagnosa Penyakit Saya");
        diagnoseButton.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(diagnoseButton, BorderLayout.SOUTH);
    }

    public JButton getDiagnoseButton() {
        return diagnoseButton;
    }

    // Getter untuk Map
    public Map<JCheckBox, Symptom> getSymptomMap() {
        return symptomMap;
    }
}