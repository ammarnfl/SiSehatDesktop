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

        this.view.getLoginButton().addActionListener(e -> loginUser());
        this.view.getGoToRegisterButton().addActionListener(e -> goToRegister());
    }

    private void loginUser() {
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email dan password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean loginSuccess = false;
        User loggedInUser = null;
        for (User user : AppDatabase.users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loginSuccess = true;
                loggedInUser = user;
                break;
            }
        }

        if (loginSuccess) {
            SessionManager.currentUser = loggedInUser;
            dashboardView.setWelcomeMessage(loggedInUser.getFullName());
            navigationController.showCard("DASHBOARD");
        } else {
            JOptionPane.showMessageDialog(view, "Email atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goToRegister() {
        navigationController.showCard("REGISTER");
    }
}