package com.sisehat.view;

import com.sisehat.model.Disease;
import com.sisehat.model.History;
import com.sisehat.model.Symptom;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

public class ProfileView extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JPanel historyListPanel; // Panel untuk menampung semua item riwayat
    private JButton backButton;

    public ProfileView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Info Profil (ATAS) ---
        JPanel profileInfoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        profileInfoPanel.setBorder(new TitledBorder("Informasi Pengguna"));
        nameLabel = new JLabel("-");
        emailLabel = new JLabel("-");
        profileInfoPanel.add(new JLabel("Nama Lengkap:"));
        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(new JLabel("Email:"));
        profileInfoPanel.add(emailLabel);
        this.add(profileInfoPanel, BorderLayout.NORTH);

        // --- Panel Daftar Riwayat (TENGAH) ---
        historyListPanel = new JPanel();
        historyListPanel.setLayout(new BoxLayout(historyListPanel, BoxLayout.Y_AXIS));
        JScrollPane historyScrollPane = new JScrollPane(historyListPanel);
        historyScrollPane.setBorder(new TitledBorder("Riwayat Diagnosa"));
        this.add(historyScrollPane, BorderLayout.CENTER);

        // --- Tombol Kembali (BAWAH) ---
        backButton = new JButton("Kembali ke Dashboard");
        this.add(backButton, BorderLayout.SOUTH);
    }

    // Method untuk mengisi data profil dan riwayat
    public void displayProfile(String name, String email) {
        nameLabel.setText(name);
        emailLabel.setText(email);
    }

    public void displayHistories(java.util.List<History> histories) {
        historyListPanel.removeAll(); // Kosongkan daftar sebelum mengisi ulang

        if (histories.isEmpty()) {
            historyListPanel.add(new JLabel("Belum ada riwayat diagnosa."));
        } else {
            for (History history : histories) {
                historyListPanel.add(createHistoryItemPanel(history));
                historyListPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi antar item
            }
        }
        historyListPanel.revalidate();
        historyListPanel.repaint();
    }

    // Helper untuk membuat satu panel item riwayat
    private JPanel createHistoryItemPanel(History history) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Tanggal
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm");
        JLabel dateLabel = new JLabel("  Diagnosa pada: " + sdf.format(history.getDiagnosisDate()));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(dateLabel, BorderLayout.NORTH);

        // Gejala dan Hasil
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("Gejala yang dipilih:\n");
        for(Symptom s : history.getSelectedSymptoms()) {
            sb.append("- ").append(s.getName()).append("\n");
        }
        sb.append("\nHasil Kemungkinan:\n");
        for(Disease d : history.getResultingDiseases()) {
            sb.append("- ").append(d.getName()).append("\n");
        }
        detailsArea.setText(sb.toString());
        panel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        // Tombol Feedback (belum berfungsi)
        JButton feedbackButton = new JButton("Beri Feedback Akurasi");
        panel.add(feedbackButton, BorderLayout.SOUTH);

        return panel;
    }

    // Getter
    public JButton getBackButton() { return backButton; }
}