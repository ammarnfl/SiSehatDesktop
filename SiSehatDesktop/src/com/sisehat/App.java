package com.sisehat;

import com.sisehat.controller.*;
import com.sisehat.data.AppDatabase;
import com.sisehat.view.DashboardView;
import com.sisehat.view.LoginView;
import com.sisehat.view.MainView;
import com.sisehat.view.ProfileView;
import com.sisehat.view.RegisterView;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        AppDatabase.initialize();
        SwingUtilities.invokeLater(() -> {
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);
            NavigationController navigationController = new NavigationController(mainPanel, cardLayout);

            // Buat semua view
            MainView mainView = new MainView();
            RegisterView registerView = new RegisterView();
            LoginView loginView = new LoginView();
            DashboardView dashboardView = new DashboardView();
            ProfileView profileView = new ProfileView(); // View baru

            // Buat semua controller
            new MainController(mainView, navigationController);
            new RegisterController(registerView, navigationController);
            ProfileController profileController = new ProfileController(profileView, navigationController); // Buat ProfileController
            new LoginController(loginView, dashboardView, navigationController);
            new DashboardController(dashboardView, navigationController, profileController); // Beri profileController ke DashboardController

            // Masukkan semua view sebagai "kartu"
            mainPanel.add(loginView, "LOGIN");
            mainPanel.add(registerView, "REGISTER");
            mainPanel.add(dashboardView, "DASHBOARD");
            mainPanel.add(mainView, "MAIN");
            mainPanel.add(profileView, "PROFILE"); // Daftarkan kartu profil

            // ... (sisa kode JFrame, JMenuBar, dll sama persis) ...
            JFrame mainFrame = new JFrame("SiSehat");
            mainFrame.setContentPane(mainPanel);

            JMenuBar menuBar = new JMenuBar();
            JMenu menuAplikasi = new JMenu("Opsi");
            JMenuItem profileMenuItem = new JMenuItem("Profil & Riwayat");
            JMenuItem logoutMenuItem = new JMenuItem("Logout");
            profileMenuItem.addActionListener(e -> {
                profileController.loadUserProfileAndHistories();
                navigationController.showCard("PROFILE");
            });
            logoutMenuItem.addActionListener(e -> {
                SessionManager.currentUser = null;
                navigationController.showCard("LOGIN");
            });
            menuAplikasi.add(profileMenuItem);
            menuAplikasi.add(logoutMenuItem);
            menuBar.add(menuAplikasi);
            mainFrame.setJMenuBar(menuBar);

            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 600);
            mainFrame.setLocationRelativeTo(null);
            navigationController.showCard("LOGIN");
            mainFrame.setVisible(true);
        });
    }
}