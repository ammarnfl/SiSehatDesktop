package com.sisehat.controller;

import com.sisehat.data.DiseaseDAO;
import com.sisehat.model.Diagnosis;
import com.sisehat.model.Disease;
import com.sisehat.model.Symptom;
import com.sisehat.view.MainView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainController {
    private MainView view;
    private NavigationController navigationController;
    private DiseaseResultController diseaseResultController;

    public MainController(MainView view, NavigationController navigationController, DiseaseResultController diseaseResultController) {
        this.view = view;
        this.navigationController = navigationController;
        this.diseaseResultController = diseaseResultController;
        this.view.getDiagnoseButton().addActionListener(e -> performDiagnosis());
        this.view.getBackButton().addActionListener(e -> navigationController.showCard("DASHBOARD"));
    }

    private void performDiagnosis() {
        List<Symptom> selectedSymptoms = new ArrayList<>();
        for (Map.Entry<JCheckBox, Symptom> entry : view.getSymptomMap().entrySet()) {
            if (entry.getKey().isSelected()) {
                selectedSymptoms.add(entry.getValue());
            }
        }

        if (selectedSymptoms.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Silakan pilih minimal satu gejala.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // === PERUBAHAN DI SINI: Ambil data penyakit dari DAO ===
        DiseaseDAO diseaseDAO = new DiseaseDAO();
        List<Disease> allDiseases = diseaseDAO.getAllDiseases();
        List<Diagnosis> diagnosisResults = new ArrayList<>();

        for (Disease disease : allDiseases) {
            long matchCount = disease.getRelatedSymptomIds().stream()
                    .filter(id -> selectedSymptoms.stream().anyMatch(s -> s.getId() == id))
                    .count();

            if (matchCount > 0) {
                disease.setSelectedSymptoms(selectedSymptoms);
                int totalGejalaPenyakit = disease.getRelatedSymptomIds().size();
                diagnosisResults.add(new Diagnosis(disease, (int) matchCount, totalGejalaPenyakit));
            }
        }

        List<Disease> topDiseases = diagnosisResults.stream()
                .sorted(Comparator.comparingDouble(Diagnosis::getMatchPercentage).reversed())
                .limit(3)
                .map(Diagnosis::getDisease)
                .collect(Collectors.toList());

        diseaseResultController.setResults(selectedSymptoms, topDiseases);
        navigationController.showCard("DISEASE_RESULT");
    }
}