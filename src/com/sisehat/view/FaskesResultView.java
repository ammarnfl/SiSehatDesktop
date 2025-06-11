package com.sisehat.view;

import com.sisehat.model.FasilitasKesehatan;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class FaskesResultView extends JPanel {
    private JPanel faskesListPanel;
    private JButton saveButton;
    private JButton deleteButton;

    public FaskesResultView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Berikut Top 3 Rekomendasi Faskes Untuk Anda", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel, BorderLayout.NORTH);

        faskesListPanel = new JPanel();
        faskesListPanel.setLayout(new BoxLayout(faskesListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(faskesListPanel);
        scrollPane.setBorder(new TitledBorder("Klik untuk melihat detail"));
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        saveButton = new JButton("Simpan Riwayat Lengkap & Tutup");
        deleteButton = new JButton("Jangan Simpan (Hapus)");
        bottomPanel.add(saveButton);
        bottomPanel.add(deleteButton);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    // View hanya menerima panel yang sudah jadi dari controller
    public void displayFaskes(List<JPanel> faskesPanels) {
        faskesListPanel.removeAll();
        if (faskesPanels.isEmpty()) {
            faskesListPanel.add(new JLabel("Tidak ada rekomendasi faskes yang cocok."));
        } else {
            for (JPanel panel : faskesPanels) {
                faskesListPanel.add(panel);
                faskesListPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        faskesListPanel.revalidate();
        faskesListPanel.repaint();
    }

    // Getters
    public JButton getSaveButton() { return saveButton; }
    public JButton getDeleteButton() { return deleteButton; }
}