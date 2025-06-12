package com.sisehat.view;

import com.sisehat.data.SymptomDAO; // <-- Import DAO
import com.sisehat.model.Symptom;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List; // <-- Import List
import java.util.Map;

public class MainView extends JPanel {
    private Map<JCheckBox, Symptom> symptomMap = new HashMap<>();
    private JButton diagnoseButton;
    private JButton backButton;

    public MainView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(new JLabel("Silakan Pilih Gejala yang Anda Rasakan:", SwingConstants.CENTER), BorderLayout.NORTH);

        // === PERUBAHAN UTAMA DI SINI ===
        JPanel checkBoxesPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        SymptomDAO symptomDAO = new SymptomDAO();
        List<Symptom> allSymptoms = symptomDAO.getAllSymptoms();

        for (Symptom symptom : allSymptoms) {
            JCheckBox checkBox = new JCheckBox(symptom.getName());
            symptomMap.put(checkBox, symptom);
            checkBoxesPanel.add(checkBox);
        }
        this.add(new JScrollPane(checkBoxesPanel), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        diagnoseButton = new JButton("Diagnosa Sekarang");
        backButton = new JButton("Kembali ke Dashboard");
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