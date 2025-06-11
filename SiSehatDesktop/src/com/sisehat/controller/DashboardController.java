package com.sisehat.controller;

import com.sisehat.view.DashboardView;

public class DashboardController {
    private DashboardView view;
    private NavigationController navigationController;
    private ProfileController profileController; // Tambahkan referensi ke ProfileController

    public DashboardController(DashboardView view, NavigationController navigationController, ProfileController profileController) {
        this.view = view;
        this.navigationController = navigationController;
        this.profileController = profileController; // Simpan referensinya

        this.view.getStartDiagnosisButton().addActionListener(e -> goToDiagnosis());
        this.view.getProfileButton().addActionListener(e -> goToProfile());
    }

    private void goToDiagnosis() {
        navigationController.showCard("MAIN");
    }

    private void goToProfile() {
        profileController.loadUserProfileAndHistories(); // PENTING: Muat data dulu
        navigationController.showCard("PROFILE");      // Baru pindah halaman
    }
}