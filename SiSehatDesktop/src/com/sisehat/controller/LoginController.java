package com.sisehat.controller;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.User;
import com.sisehat.view.DashboardView;
import com.sisehat.view.LoginView;

import javax.swing.*;

public class LoginController {
    private LoginView view;
    private DashboardView dashboardView;
    private NavigationController navigationController;

    public LoginController(LoginView view, DashboardView dashboardView, NavigationController navigationController) {
        this.view = view;
        this.dashboardView = dashboardView;
        this.navigationController = navigationController;

        this.view.getLoginButton().addActionListener(e -> performLogin());
        this.view.getRegisterButton().addActionListener(e -> navigationController.showCard("REGISTER"));
    }

    private void performLogin() {
        String identifier = view.getIdentifierField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if (identifier.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Harap isi semua field.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User authenticatedUser = null;
        for (User user : AppDatabase.users) {
            // Cek apakah identifier cocok dengan email ATAU username (abaikan besar kecil huruf)
            if ((user.getEmail().equalsIgnoreCase(identifier) || user.getUsername().equalsIgnoreCase(identifier))
                    && user.getPassword().equals(password)) {
                authenticatedUser = user;
                break;
            }
        }

        if (authenticatedUser != null) {
            SessionManager.currentUser = authenticatedUser;
            dashboardView.setWelcomeMessage(authenticatedUser.getFullName());
            navigationController.showCard("DASHBOARD");
        } else {
            JOptionPane.showMessageDialog(view, "Email/Username atau Password salah.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
}