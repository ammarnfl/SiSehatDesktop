package com.sisehat;

import com.sisehat.controller.*;
import com.sisehat.data.AppDatabase;
import com.sisehat.view.*;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        AppDatabase.initialize();

        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("SiSehat - Sistem Diagnosa & Informasi Kesehatan");
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // ==================================================================
            // KODE YANG DIPERBAIKI ADA DI SINI
            // Constructor NavigationController sekarang hanya menerima 2 argumen
            // ==================================================================
            NavigationController navigationController = new NavigationController(mainPanel, cardLayout);

            // 1. Buat semua View
            LoginView loginView = new LoginView();
            RegisterView registerView = new RegisterView();
            DashboardView dashboardView = new DashboardView();
            MainView mainView = new MainView();
            ProfileView profileView = new ProfileView();
            DiseaseResultView diseaseResultView = new DiseaseResultView();
            FaskesResultView faskesResultView = new FaskesResultView();
            FaskesListView faskesListView = new FaskesListView();
            FaskesDetailView faskesDetailView = new FaskesDetailView();

            // 2. Buat semua Controller dan hubungkan
            FaskesDetailController faskesDetailController = new FaskesDetailController(faskesDetailView, navigationController);
            FaskesResultController faskesResultController = new FaskesResultController(faskesResultView, navigationController, faskesDetailController);
            ProfileController profileController = new ProfileController(profileView, navigationController);

            new FaskesListController(faskesListView, navigationController, faskesDetailController);
            new LoginController(loginView, dashboardView, navigationController, registerView);
            new RegisterController(registerView, navigationController, loginView);
            new DashboardController(dashboardView, navigationController, profileController, mainView, faskesListView);

            DiseaseResultController diseaseResultController = new DiseaseResultController(diseaseResultView, navigationController, faskesResultController);
            new MainController(mainView, navigationController, diseaseResultController);

            // 3. Masukkan semua View sebagai "kartu"
            mainPanel.add(loginView, "LOGIN");
            mainPanel.add(registerView, "REGISTER");
            mainPanel.add(dashboardView, "DASHBOARD");
            mainPanel.add(mainView, "MAIN");
            mainPanel.add(profileView, "PROFILE");
            mainPanel.add(diseaseResultView, "DISEASE_RESULT");
            mainPanel.add(faskesResultView, "FASKES_RESULT");
            mainPanel.add(faskesListView, "FASKES_LIST");
            mainPanel.add(faskesDetailView, "FASKES_DETAIL");

            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setContentPane(mainPanel);
            mainFrame.setSize(800, 700);
            mainFrame.setLocationRelativeTo(null);

            // Menampilkan frame setelah semua siap
            mainFrame.setVisible(true);

            // Pindah ke halaman login setelah frame terlihat
            navigationController.showCard("LOGIN");
        });
    }
}