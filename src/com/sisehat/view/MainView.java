package com.sisehat.view;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.Symptom;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainView extends JPanel {
    private Map<JCheckBox, Symptom> symptomMap = new HashMap<>();
    private JButton diagnoseButton;
    private JButton backButton; // <-- TOMBOL BARU

    public MainView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(new JLabel("Silakan Pilih Gejala yang Anda Rasakan:", SwingConstants.CENTER), BorderLayout.NORTH);

        // 2x4 grid, left-aligned checkboxes in each grid cell
        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30); // space-around effect
        gbc.anchor = GridBagConstraints.WEST; // Align to left in each cell
        int col = 0, row = 0;
        for (Symptom symptom : AppDatabase.symptoms) {
            JCheckBox checkBox = new JCheckBox(symptom.getName());
            symptomMap.put(checkBox, symptom);
            gbc.gridx = col;
            gbc.gridy = row;
            checkBoxesPanel.add(checkBox, gbc);
            row++;
            if (row == 4) { row = 0; col++; }
        }
        this.add(new JScrollPane(checkBoxesPanel), BorderLayout.CENTER);

        // Panel Bawah untuk Tombol
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // Grid 1 baris, 2 kolom
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        diagnoseButton = new JButton("Diagnosa Sekarang");
        backButton = new JButton("Kembali ke Dashboard"); // <-- TOMBOL BARU

        bottomPanel.add(backButton);
        bottomPanel.add(diagnoseButton);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void reset() {
        for (JCheckBox checkBox : symptomMap.keySet()) {
            checkBox.setSelected(false);
        }
    }

    // Getters
    public Map<JCheckBox, Symptom> getSymptomMap() { return symptomMap; }
    public JButton getDiagnoseButton() { return diagnoseButton; }
    public JButton getBackButton() { return backButton; } // <-- GETTER BARU
}