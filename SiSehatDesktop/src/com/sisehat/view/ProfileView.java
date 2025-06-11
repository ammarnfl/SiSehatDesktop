package com.sisehat.view;

import com.sisehat.controller.ProfileController;
import com.sisehat.model.Disease;
import com.sisehat.model.FasilitasKesehatan;
import com.sisehat.model.History;
import com.sisehat.model.Symptom;
import com.sisehat.model.Feedback;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

public class ProfileView extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JPanel historyListPanel;
    private JButton backButton;

    public ProfileView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel profileInfoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        profileInfoPanel.setBorder(new TitledBorder("Informasi Pengguna"));
        nameLabel = new JLabel("-");
        emailLabel = new JLabel("-");
        profileInfoPanel.add(new JLabel("Nama Lengkap:"));
        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(new JLabel("Email:"));
        profileInfoPanel.add(emailLabel);
        this.add(profileInfoPanel, BorderLayout.NORTH);

        historyListPanel = new JPanel();
        historyListPanel.setLayout(new BoxLayout(historyListPanel, BoxLayout.Y_AXIS));
        JScrollPane historyScrollPane = new JScrollPane(historyListPanel);
        historyScrollPane.setBorder(new TitledBorder("Riwayat Diagnosa"));
        this.add(historyScrollPane, BorderLayout.CENTER);

        backButton = new JButton("Kembali ke Dashboard");
        this.add(backButton, BorderLayout.SOUTH);
    }

    public void displayProfile(String name, String email) {
        nameLabel.setText(name);
        emailLabel.setText(email);
    }

    public void displayHistories(java.util.List<History> histories, ProfileController controller) {
        historyListPanel.removeAll();
        if (histories.isEmpty()) {
            historyListPanel.add(new JLabel("Belum ada riwayat diagnosa."));
        } else {
            for (History history : histories) {
                historyListPanel.add(createHistoryItemPanel(history, controller));
                historyListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        historyListPanel.revalidate();
        historyListPanel.repaint();
    }

    private JPanel createHistoryItemPanel(History history, ProfileController controller) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm");
        JLabel dateLabel = new JLabel("  Diagnosa pada: " + sdf.format(history.getDiagnosisDate()));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(dateLabel, BorderLayout.NORTH);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("Gejala yang dipilih:\n");
        for (Symptom s : history.getSelectedSymptoms()) {
            sb.append("- ").append(s.getName()).append("\n");
        }
        sb.append("\nHasil Kemungkinan Penyakit:\n");
        for (Disease d : history.getResultingDiseases()) {
            sb.append("- ").append(d.getName()).append("\n");
        }
        if (history.getRecommendedFaskes() != null && !history.getRecommendedFaskes().isEmpty()) {
            sb.append("\nRekomendasi Faskes yang Disimpan:\n");
            for (FasilitasKesehatan faskes : history.getRecommendedFaskes()) {
                sb.append("- ").append(faskes.getName()).append("\n");
            }
        }
        detailsArea.setText(sb.toString());
        panel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton feedbackButton = new JButton();
        feedbackButton.addActionListener(e -> controller.showFeedbackDialog(history));

        if (history.getFeedback() == null) {
            feedbackButton.setText("Beri Feedback");
            feedbackPanel.add(feedbackButton, BorderLayout.CENTER);
        } else {
            Feedback feedback = history.getFeedback();
            String feedbackText = String.format("<html><b>Feedback Anda:</b><br>" +
                            "Akurasi Penyakit: %d ★ | " +
                            "%s" +
                            "Komentar: %s</html>",
                    feedback.getDiseaseRating(),
                    (feedback.getFaskesRating() != -1) ? String.format("Kesesuaian Faskes: %d ★ | ", feedback.getFaskesRating()) : "",
                    feedback.getComment().isEmpty() ? "<i>(tidak ada)</i>" : feedback.getComment());
            feedbackPanel.add(new JLabel(feedbackText), BorderLayout.CENTER);
            feedbackButton.setText("Edit Feedback");
            feedbackPanel.add(feedbackButton, BorderLayout.EAST);
        }
        panel.add(feedbackPanel, BorderLayout.SOUTH);
        return panel;
    }

    public JButton getBackButton() { return backButton; }
}