package com.sisehat.controller;

import com.sisehat.view.*;
import javax.swing.*;

public class DashboardController {
    private DashboardView view;
    private NavigationController navigationController;
    private LoginView loginView;
    private MainView mainView;
    private FaskesListView faskesListView;
    private ProfileController profileController;

    public DashboardController(DashboardView view, NavigationController nc, LoginView loginView, MainView mainView, FaskesListView faskesListView, ProfileController pc) {
        this.view = view;
        this.navigationController = nc;
        this.loginView = loginView;
        this.mainView = mainView;
        this.faskesListView = faskesListView;
        this.profileController = pc;

        // --- PASANG LISTENER ---
        // Aksi Diagnosa (untuk 2 tombol)
        this.view.getNavDiagnosaButton().addActionListener(e -> goToDiagnosa());
        this.view.getStartDiagnosisButton().addActionListener(e -> goToDiagnosa());

        // Aksi Daftar Faskes (untuk 2 tombol)
        this.view.getNavFaskesButton().addActionListener(e -> goToFaskes());
        this.view.getFaskesButton().addActionListener(e -> goToFaskes());

        // Aksi Profil (untuk 2 tombol)
        this.view.getNavProfileButton().addActionListener(e -> goToProfile());
        this.view.getProfileButton().addActionListener(e -> goToProfile());

        // Aksi Logout
        this.view.getNavLogoutButton().addActionListener(e -> performLogout());
    }

    // --- Kumpulan Aksi Navigasi ---
    private void goToDiagnosa() {
        mainView.reset();
        navigationController.showCard("MAIN");
    }

    private void goToFaskes() {
        faskesListView.reset();
        navigationController.showCard("FASKES_LIST");
    }

    private void goToProfile() {
        profileController.loadUserProfileAndHistories();
        navigationController.showCard("PROFILE");
    }

    private void performLogout() {
        int response = JOptionPane.showConfirmDialog(view,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            SessionManager.currentUser = null;
            loginView.reset();
            navigationController.showCard("LOGIN");
        }
    }
}