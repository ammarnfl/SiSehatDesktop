package com.sisehat.view;

import com.sisehat.model.Disease;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class DiseaseResultView extends JPanel {
    private JPanel diseaseListPanel;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton goToFaskesButton;

    public DiseaseResultView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Berikut Hasil Sementara Penyakit Anda", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel, BorderLayout.NORTH);

        diseaseListPanel = new JPanel();
        diseaseListPanel.setLayout(new BoxLayout(diseaseListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(diseaseListPanel);
        scrollPane.setBorder(new TitledBorder("Peringkat Kemungkinan Penyakit"));
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10,10));
        JPanel topButtonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        saveButton = new JButton("Simpan & Tutup");
        deleteButton = new JButton("Jangan Simpan (Hapus)");
        topButtonsPanel.add(saveButton);
        topButtonsPanel.add(deleteButton);

        goToFaskesButton = new JButton("Lihat Rekomendasi Fasilitas Kesehatan >>");
        goToFaskesButton.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(topButtonsPanel, BorderLayout.NORTH);
        bottomPanel.add(goToFaskesButton, BorderLayout.SOUTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void displayDiseases(List<Disease> diseases) {
        diseaseListPanel.removeAll();
        if (diseases.isEmpty()) {
            diseaseListPanel.add(new JLabel("Tidak ada hasil."));
        } else {
            int rank = 1;
            for (Disease disease : diseases) {
                JLabel label = new JLabel(String.format("%d. %s", rank++, disease.getName()));
                label.setFont(new Font("Arial", Font.PLAIN, 16));
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                diseaseListPanel.add(label);
            }
        }
        this.revalidate();
        this.repaint();
    }

    // Getters
    public JButton getSaveButton() { return saveButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getGoToFaskesButton() { return goToFaskesButton; }
}