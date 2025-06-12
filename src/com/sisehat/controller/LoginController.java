package com.sisehat.controller;

import com.sisehat.data.UserDAO;
import com.sisehat.model.User;
import com.sisehat.view.DashboardView;
import com.sisehat.view.LoginView;
import com.sisehat.view.RegisterView;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginView view;
    private DashboardView dashboardView;
    private NavigationController navigationController;
    private RegisterView registerView;

    public LoginController(LoginView view, DashboardView dashboardView, NavigationController nc, RegisterView registerView) {
        this.view = view;
        this.dashboardView = dashboardView;
        this.navigationController = nc;
        this.registerView = registerView;

        this.view.getLoginButton().addActionListener(e -> performLogin());
        this.view.getRegisterButton().addActionListener(e -> {
            registerView.reset();
            navigationController.showCard("REGISTER");
        });
    }

    private void performLogin() {
        String identifier = view.getIdentifierField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if (identifier.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Harap isi semua field.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDAO userDAO = new UserDAO();
        User authenticatedUser = userDAO.findUser(identifier, password);

        if (authenticatedUser != null) {
            SessionManager.currentUser = authenticatedUser;

            // Menggunakan method setWelcomeMessage yang sudah kamu buat
            dashboardView.setWelcomeMessage(authenticatedUser.getFullName());

            navigationController.showCard("DASHBOARD");
        } else {
            JOptionPane.showMessageDialog(view, "Email/Username atau Password salah.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
}