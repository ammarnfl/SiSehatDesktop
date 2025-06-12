package com.sisehat.controller;

import com.sisehat.data.HistoryDAO;
import com.sisehat.model.Feedback;
import com.sisehat.model.History;
import com.sisehat.model.User;
import com.sisehat.view.ProfileView;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class ProfileController {
    private ProfileView view;
    private NavigationController navigationController;

    public ProfileController(ProfileView view, NavigationController navigationController) {
        this.view = view;
        this.navigationController = navigationController;
        this.view.getBackButton().addActionListener(e -> goBackToDashboard());
    }

    public void loadUserProfileAndHistories() {
        User currentUser = SessionManager.currentUser;
        if (currentUser == null) return;

        view.displayProfile(currentUser.getFullName(), currentUser.getEmail());

        HistoryDAO historyDAO = new HistoryDAO();
        List<History> userHistories = historyDAO.getHistoriesByUser(currentUser);

        view.displayHistories(userHistories, this);
    }

    public void showFeedbackDialog(History history) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(new JLabel("Akurasi Diagnosa Penyakit:"));

        ButtonGroup diseaseRatingGroup = new ButtonGroup();
        JPanel diseaseRatingPanel = createRatingPanel(diseaseRatingGroup);
        dialogPanel.add(diseaseRatingPanel);

        boolean hasFaskesData = history.getRecommendedFaskes() != null && !history.getRecommendedFaskes().isEmpty();
        ButtonGroup faskesRatingGroup = null;

        if (hasFaskesData) {
            dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            dialogPanel.add(new JLabel("Kesesuaian Rekomendasi Faskes:"));
            faskesRatingGroup = new ButtonGroup();
            JPanel faskesRatingPanel = createRatingPanel(faskesRatingGroup);
            dialogPanel.add(faskesRatingPanel);
        }

        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        dialogPanel.add(new JLabel("Komentar (Opsional):"));
        JTextArea commentArea = new JTextArea(3, 20);
        dialogPanel.add(new JScrollPane(commentArea));

        // ==================================================================
        // BAGIAN 1 YANG DIPERBAIKI: Mengisi form jika feedback sudah ada
        // ==================================================================
        if (history.getFeedback() != null) {
            Feedback existingFeedback = history.getFeedback();
            commentArea.setText(existingFeedback.getComment());

            // Pilih otomatis radio button untuk rating penyakit
            for(AbstractButton btn : Collections.list(diseaseRatingGroup.getElements())) {
                if(btn.getActionCommand().equals(String.valueOf(existingFeedback.getDiseaseRating()))) {
                    btn.setSelected(true);
                    break;
                }
            }

            // Pilih otomatis radio button untuk rating faskes jika ada
            if (hasFaskesData && faskesRatingGroup != null && existingFeedback.getFaskesRating() != -1) {
                for(AbstractButton btn : Collections.list(faskesRatingGroup.getElements())) {
                    if(btn.getActionCommand().equals(String.valueOf(existingFeedback.getFaskesRating()))) {
                        btn.setSelected(true);
                        break;
                    }
                }
            }
        }

        int result = JOptionPane.showConfirmDialog(view, dialogPanel, "Beri Feedback", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // ==================================================================
            // BAGIAN 2 YANG DIPERBAIKI: Mengambil nilai rating yang dipilih
            // ==================================================================
            ButtonModel diseaseSelectedModel = diseaseRatingGroup.getSelection();
            if (diseaseSelectedModel == null) {
                JOptionPane.showMessageDialog(view, "Harap pilih rating untuk akurasi penyakit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int diseaseRating = Integer.parseInt(diseaseSelectedModel.getActionCommand());

            int faskesRating = -1; // Default -1 (tidak ada rating)
            if (hasFaskesData) {
                ButtonModel faskesSelectedModel = faskesRatingGroup.getSelection();
                if (faskesSelectedModel == null) {
                    JOptionPane.showMessageDialog(view, "Harap pilih rating untuk kesesuaian faskes.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                faskesRating = Integer.parseInt(faskesSelectedModel.getActionCommand());
            }

            String comment = commentArea.getText();
            Feedback newFeedback = new Feedback(diseaseRating, faskesRating, comment);

            new HistoryDAO().saveFeedback(newFeedback, history.getHistoryId());

            JOptionPane.showMessageDialog(view, "Feedback berhasil disimpan!");
            loadUserProfileAndHistories();
        }
    }

    // ==================================================================
    // BAGIAN 3 YANG DIPERBAIKI: Implementasi lengkap createRatingPanel
    // ==================================================================
    private JPanel createRatingPanel(ButtonGroup buttonGroup) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        for (int i = 1; i <= 5; i++) {
            JRadioButton starButton = new JRadioButton(i + " ★");
            starButton.setActionCommand(String.valueOf(i)); // Ini penting untuk mengambil nilainya
            buttonGroup.add(starButton);
            panel.add(starButton);
        }
        return panel;
    }

    private void goBackToDashboard() {
        navigationController.showCard("DASHBOARD");
    }
}