package com.sisehat.controller;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.Disease;
import com.sisehat.model.History;
import com.sisehat.model.Symptom;
import com.sisehat.view.DiseaseResultView;
import javax.swing.*;
import java.util.List;

public class DiseaseResultController {
    private DiseaseResultView view;
    private NavigationController navigationController;
    private FaskesResultController faskesResultController;

    private List<Symptom> currentSelectedSymptoms;
    private List<Disease> currentResultingDiseases;

    public DiseaseResultController(DiseaseResultView view, NavigationController navigationController, FaskesResultController faskesResultController) {
        this.view = view;
        this.navigationController = navigationController;
        this.faskesResultController = faskesResultController;

        this.view.getSaveButton().addActionListener(e -> saveAndClose());
        this.view.getDeleteButton().addActionListener(e -> deleteAndReturn());
        this.view.getGoToFaskesButton().addActionListener(e -> showRecommendations());
    }

    public void setResults(List<Symptom> selectedSymptoms, List<Disease> resultingDiseases) {
        this.currentSelectedSymptoms = selectedSymptoms;
        this.currentResultingDiseases = resultingDiseases;
        view.displayDiseases(resultingDiseases);
    }

    private void saveAndClose() {
        // Menyimpan riwayat HANYA dengan hasil penyakit (faskes = null)
        History newHistory = new History(
                SessionManager.currentUser.getEmail(),
                currentSelectedSymptoms,
                currentResultingDiseases,
                null // Faskes tidak disimpan
        );
        AppDatabase.histories.add(newHistory);
        JOptionPane.showMessageDialog(view, "Riwayat penyakit berhasil disimpan!");
        navigationController.showCard("DASHBOARD");
    }

    private void deleteAndReturn() {
        navigationController.showCard("DASHBOARD");
    }

    private void showRecommendations() {
        // Oper data ke controller halaman berikutnya
        faskesResultController.setResults(currentSelectedSymptoms, currentResultingDiseases, AppDatabase.healthFacilities);
        navigationController.showCard("FASKES_RESULT");
    }
}