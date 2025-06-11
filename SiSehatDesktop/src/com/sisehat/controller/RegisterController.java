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

        this.view.getRegisterButton().addActionListener(e -> performRegister());
        this.view.getBackToLoginButton().addActionListener(e -> navigationController.showCard("LOGIN"));
    }

    private void performRegister() {
        String fullName = view.getFullNameField().getText();
        String username = view.getUsernameField().getText();
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        // --- VALIDASI DIMULAI DI SINI ---

        // 1. Cek apakah ada field yang kosong
        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field wajib diisi!", "Error Registrasi", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses
        }

        // 2. Cek format email (harus ada '@')
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(view, "Format email tidak valid. Pastikan menggunakan format 'nama@domain.com'.", "Error Registrasi", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses
        }

        // 3. Cek panjang password (minimal 8 karakter)
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(view, "Password terlalu pendek. Harap gunakan minimal 8 karakter.", "Error Registrasi", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses
        }

        // 4. Cek apakah username atau email sudah digunakan
        for (User user : AppDatabase.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(view, "Username '" + username + "' sudah digunakan. Silakan pilih username lain.", "Error Registrasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (user.getEmail().equalsIgnoreCase(email)) {
                JOptionPane.showMessageDialog(view, "Email '" + email + "' sudah terdaftar. Silakan login.", "Error Registrasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // --- VALIDASI SELESAI ---

        // Jika semua validasi lolos, buat user baru
        User newUser = new User(fullName, username, email, password);
        AppDatabase.users.add(newUser);

        JOptionPane.showMessageDialog(view, "Registrasi berhasil! Silakan login dengan akun Anda.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        navigationController.showCard("LOGIN");
    }
}