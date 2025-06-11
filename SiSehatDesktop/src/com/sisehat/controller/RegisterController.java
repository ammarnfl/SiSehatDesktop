package com.sisehat.controller;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.User;
import com.sisehat.view.RegisterView;
import javax.swing.*;

public class RegisterController {
    private RegisterView view;
    private NavigationController navigationController;

    public RegisterController(RegisterView view, NavigationController navigationController) {
        this.view = view;
        this.navigationController = navigationController;

        // Tambahkan listener ke tombol
        this.view.getRegisterButton().addActionListener(e -> registerUser());
        this.view.getBackToLoginButton().addActionListener(e -> goToLogin());
    }

    private void registerUser() {
        String fullName = view.getFullNameField().getText();
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if(fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Simpan user baru ke database palsu kita
        AppDatabase.users.add(new User(fullName, email, password));

        JOptionPane.showMessageDialog(view, "Registrasi berhasil! Silakan login.");

        // Pindahkan pengguna ke halaman login
        navigationController.showCard("LOGIN");
    }

    private void goToLogin() {
        // Pindahkan pengguna ke halaman login
        navigationController.showCard("LOGIN");
    }
}