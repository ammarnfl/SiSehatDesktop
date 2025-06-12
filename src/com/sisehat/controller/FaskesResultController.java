package com.sisehat.controller;

import com.sisehat.data.HistoryDAO;
import com.sisehat.model.*;
import com.sisehat.view.FaskesResultView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaskesResultController {
    private FaskesResultView view;
    private NavigationController navigationController;
    private FaskesDetailController faskesDetailController; // Tambahkan ini

    private List<Symptom> currentSelectedSymptoms;
    private List<Disease> currentResultingDiseases;
    private List<FasilitasKesehatan> currentRecommendations;

    public FaskesResultController(FaskesResultView view, NavigationController nc, FaskesDetailController fdc) {
        this.view = view;
        this.navigationController = nc;
        this.faskesDetailController = fdc; // Simpan referensinya
        this.view.getSaveButton().addActionListener(e -> saveAndClose());
        this.view.getDeleteButton().addActionListener(e -> deleteAndReturn());
    }

    // Ganti total method setResults
    public void setResults(List<Symptom> selectedSymptoms, List<Disease> resultingDiseases, List<FasilitasKesehatan> allFaskes) {
        this.currentSelectedSymptoms = selectedSymptoms;
        this.currentResultingDiseases = resultingDiseases;

        // Logika ranking Faskes (Top 3)
        java.util.Map<FasilitasKesehatan, Double> faskesScores = new java.util.HashMap<>();
        List<String> diseaseNames = resultingDiseases.stream().map(Disease::getName).collect(Collectors.toList());
        for (FasilitasKesehatan faskes : allFaskes) {
            double score = 0;
            for (String diseaseName : diseaseNames) {
                if (faskes.getSpecializations().contains(diseaseName)) score += 10;
            }
            score += faskes.getRating();
            if(faskes.isHasBpjs()) score += 5;
            faskesScores.put(faskes, score);
        }
        List<FasilitasKesehatan> top3Recommendations = faskesScores.entrySet().stream()
                .sorted(java.util.Map.Entry.<FasilitasKesehatan, Double>comparingByValue().reversed())
                .limit(3)
                .map(java.util.Map.Entry::getKey)
                .collect(Collectors.toList());
        this.currentRecommendations = top3Recommendations;

        // Buat daftar panel yang bisa diklik
        List<JPanel> faskesPanels = new ArrayList<>();
        for (FasilitasKesehatan faskes : top3Recommendations) {
            JPanel panel = createFaskesItemPanel(faskes);
            panel.addMouseListener(createFaskesClickListener(faskes));
            faskesPanels.add(panel);
        }
        view.displayFaskes(faskesPanels);
    }

    // Pindahkan method createFaskesItemPanel ke sini
    private JPanel createFaskesItemPanel(FasilitasKesehatan faskes) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Color.WHITE);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(faskes.getImagePath()));
            Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            panel.add(new JLabel(new ImageIcon(image)), BorderLayout.WEST);
        } catch (Exception e) {
            panel.add(new JLabel("  No Img  "), BorderLayout.WEST);
        }
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        JLabel nameLabel = new JLabel(faskes.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        textPanel.add(nameLabel);
        JLabel addressLabel = new JLabel(faskes.getAddress());
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(addressLabel);
        panel.add(textPanel, BorderLayout.CENTER);
        return panel;
    }

    private MouseAdapter createFaskesClickListener(FasilitasKesehatan faskes) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                faskesDetailController.setFaskesToShow(faskes, "FASKES_RESULT");
                navigationController.showCard("FASKES_DETAIL");
            }
        };
    }

    private void saveAndClose() {
        History newHistory = new History(
                SessionManager.currentUser.getEmail(),
                currentSelectedSymptoms,
                currentResultingDiseases,
                currentRecommendations
        );
        new HistoryDAO().saveHistory(newHistory); // <-- Panggil DAO
        JOptionPane.showMessageDialog(view, "Riwayat diagnosa dan rekomendasi Faskes berhasil disimpan!");
        navigationController.showCard("DASHBOARD");
    }

    private void deleteAndReturn() {
        navigationController.showCard("DASHBOARD");
    }
}