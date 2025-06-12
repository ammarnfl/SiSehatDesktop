package com.sisehat.controller;

import com.sisehat.data.UserDAO; // <-- Import DAO
import com.sisehat.model.User;
import com.sisehat.view.LoginView;
import com.sisehat.view.RegisterView;
import javax.swing.JOptionPane;

public class RegisterController {
    private RegisterView view;
    private NavigationController navigationController;
    private LoginView loginView;

    public RegisterController(RegisterView view, NavigationController nc, LoginView loginView) {
        this.view = view;
        this.navigationController = nc;
        this.loginView = loginView;
        this.view.getRegisterButton().addActionListener(e -> performRegister());
        this.view.getBackToLoginButton().addActionListener(e -> {
            loginView.reset();
            navigationController.showCard("LOGIN");
        });
    }

    private void performRegister() {
        // ... (kode validasi input yang sudah ada biarkan sama)
        String fullName = view.getFullNameField().getText();
        String username = view.getUsernameField().getText();
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        // ... (semua blok if untuk validasi)

        // === PERUBAHAN UTAMA DI SINI ===
        UserDAO userDAO = new UserDAO();
        if (userDAO.isUsernameOrEmailTaken(username, email)) {
            JOptionPane.showMessageDialog(view, "Username atau Email sudah digunakan.", "Error Registrasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User newUser = new User(fullName, username, email, password);
        userDAO.registerUser(newUser);

        JOptionPane.showMessageDialog(view, "Registrasi berhasil! Silakan login.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        navigationController.showCard("LOGIN");
    }
}