package com.sisehat.controller;

import com.sisehat.data.FasilitasKesehatanDAO;
import com.sisehat.data.HistoryDAO;
import com.sisehat.model.Disease;
import com.sisehat.model.Symptom;
import com.sisehat.model.History;
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
        History newHistory = new History(
                SessionManager.currentUser.getEmail(),
                currentSelectedSymptoms,
                currentResultingDiseases,
                null // Faskes tidak disimpan di halaman ini
        );
        new HistoryDAO().saveHistory(newHistory); // <-- Panggil DAO
        JOptionPane.showMessageDialog(view, "Riwayat penyakit berhasil disimpan!");
        navigationController.showCard("DASHBOARD");
    }

    private void deleteAndReturn() {
        navigationController.showCard("DASHBOARD");
    }

    private void showRecommendations() {
        // ==================================================================
        // PERUBAHAN UTAMA ADA DI SINI
        // Ambil data faskes dari DAO, bukan dari AppDatabase lagi.
        // ==================================================================
        FasilitasKesehatanDAO faskesDAO = new FasilitasKesehatanDAO();
        faskesResultController.setResults(currentSelectedSymptoms, currentResultingDiseases, faskesDAO.getAllFasilitasKesehatan());
        navigationController.showCard("FASKES_RESULT");
    }
}