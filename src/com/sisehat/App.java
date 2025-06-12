package com.sisehat;

import com.sisehat.controller.*;
import com.sisehat.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        // Hapus AppDatabase.initialize() dari sini

        // --- BAGIAN SPLASH SCREEN ---
        JWindow splashWindow = new JWindow();
        ImageIcon splashIcon = new ImageIcon(App.class.getResource("/images/splash.png"));
        JLabel splashLabel = new JLabel(splashIcon);
        splashWindow.add(splashLabel);
        splashWindow.pack();
        splashWindow.setLocationRelativeTo(null);
        splashWindow.setVisible(true);

        // --- BAGIAN UTAMA APLIKASI ---
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splashWindow.dispose();

                JFrame mainFrame = new JFrame("SiSehat - Sistem Diagnosa & Informasi Kesehatan");
                CardLayout cardLayout = new CardLayout();
                JPanel mainPanel = new JPanel(cardLayout);
                NavigationController navigationController = new NavigationController(mainPanel, cardLayout);

                // Buat semua View
                LoginView loginView = new LoginView();
                RegisterView registerView = new RegisterView();
                DashboardView dashboardView = new DashboardView();
                MainView mainView = new MainView();
                ProfileView profileView = new ProfileView();
                DiseaseResultView diseaseResultView = new DiseaseResultView();
                FaskesResultView faskesResultView = new FaskesResultView();
                FaskesListView faskesListView = new FaskesListView();
                FaskesDetailView faskesDetailView = new FaskesDetailView();

                // ==================================================================
                // PENGKABELAN ULANG CONTROLLER ADA DI SINI
                // ==================================================================

                // Buat semua Controller dan hubungkan
                FaskesDetailController faskesDetailController = new FaskesDetailController(faskesDetailView, navigationController);
                FaskesResultController faskesResultController = new FaskesResultController(faskesResultView, navigationController, faskesDetailController);
                ProfileController profileController = new ProfileController(profileView, navigationController);

                new FaskesListController(faskesListView, navigationController, faskesDetailController);

                // Constructor Login dan Register diupdate untuk saling memberi referensi
                new LoginController(loginView, dashboardView, navigationController, registerView);
                new RegisterController(registerView, navigationController, loginView);

                new DashboardController(dashboardView, navigationController, loginView, mainView, faskesListView, profileController);
                DiseaseResultController diseaseResultController = new DiseaseResultController(diseaseResultView, navigationController, faskesResultController);
                new MainController(mainView, navigationController, diseaseResultController);

                // Masukkan semua View sebagai "kartu"
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

                mainFrame.setVisible(true);
                navigationController.showCard("LOGIN");
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
}