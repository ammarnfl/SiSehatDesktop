package com.sisehat.controller;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.History;
import com.sisehat.model.User;
import com.sisehat.view.ProfileView;

import java.util.stream.Collectors;

public class ProfileController {
    private ProfileView view;
    private NavigationController navigationController;

    public ProfileController(ProfileView view, NavigationController navigationController) {
        this.view = view;
        this.navigationController = navigationController;

        this.view.getBackButton().addActionListener(e -> goBackToDashboard());
    }

    // Method ini akan dipanggil saat pengguna ingin melihat profil
    public void loadUserProfileAndHistories() {
        User currentUser = SessionManager.currentUser;
        if (currentUser == null) return; // Seharusnya tidak terjadi

        // 1. Tampilkan info profil
        view.displayProfile(currentUser.getFullName(), currentUser.getEmail());

        // 2. Ambil riwayat milik pengguna ini saja dari database
        java.util.List<History> userHistories = AppDatabase.histories.stream()
                .filter(history -> history.getUserEmail().equals(currentUser.getEmail()))
                .collect(Collectors.toList());

        // 3. Tampilkan riwayat
        view.displayHistories(userHistories);
    }

    private void goBackToDashboard() {
        navigationController.showCard("DASHBOARD");
    }
}