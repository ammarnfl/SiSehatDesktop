package com.sisehat.controller;

import com.sisehat.view.DashboardView;
import com.sisehat.view.FaskesListView;
import com.sisehat.view.MainView;

public class DashboardController {
    private DashboardView view;
    private NavigationController navigationController;
    private ProfileController profileController;
    private MainView mainView;
    private FaskesListView faskesListView;

    public DashboardController(DashboardView view, NavigationController nc, ProfileController pc, MainView mainView, FaskesListView faskesListView) {
        this.view = view;
        this.navigationController = nc;
        this.profileController = pc;
        this.mainView = mainView;
        this.faskesListView = faskesListView;

        // Listener untuk tombol Start Diagnosis
        this.view.getStartDiagnosisButton().addActionListener(e -> {
            mainView.reset(); // RESET di sini
            navigationController.showCard("MAIN");
        });

        // Listener untuk tombol Profile
        this.view.getProfileButton().addActionListener(e -> {
            profileController.loadUserProfileAndHistories();
            navigationController.showCard("PROFILE");
        });

        // Listener untuk tombol Faskes
        this.view.getFaskesButton().addActionListener(e -> {
            faskesListView.reset(); // RESET di sini
            navigationController.showCard("FASKES_LIST");
        });
    }
}