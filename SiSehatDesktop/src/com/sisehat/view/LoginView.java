package com.sisehat.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {

    // Deklarasi komponen UI
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton goToRegisterButton;

    public LoginView() {
        // Menggunakan GridBagLayout untuk menempatkan komponen di tengah
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Jarak antar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL; // Komponen mengisi ruang horizontal

        // Judul Halaman
        JLabel titleLabel = new JLabel("Login SiSehat", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Inisialisasi komponen
        emailField = new JTextField(20); // Lebar 20 karakter
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        goToRegisterButton = new JButton("Belum punya akun? Register di sini");
        goToRegisterButton.setForeground(Color.BLUE); // Memberi warna biru agar seperti link
        goToRegisterButton.setBorderPainted(false);
        goToRegisterButton.setContentAreaFilled(false);
        goToRegisterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- Mengatur Posisi Komponen di Grid ---

        // Posisi Judul
        gbc.gridwidth = 2; // Lebar 2 kolom
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 20, 5); // Beri jarak bawah lebih besar
        this.add(titleLabel, gbc);
        gbc.insets = new Insets(5, 5, 5, 5); // Kembalikan jarak normal

        // Posisi Label & Field Email
        gbc.gridwidth = 1; // Kembali ke 1 kolom
        gbc.gridy = 1;
        gbc.gridx = 0;
        this.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        this.add(emailField, gbc);

        // Posisi Label & Field Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        this.add(passwordField, gbc);

        // Posisi Tombol Login
        gbc.gridwidth = 2; // Lebar 2 kolom lagi
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(loginButton, gbc);

        // Posisi Tombol ke Register
        gbc.gridy = 4;
        this.add(goToRegisterButton, gbc);
    }

    // --- GETTERS ---
    // Agar Controller bisa mengambil komponen-komponen ini
    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JButton getGoToRegisterButton() { return goToRegisterButton; }
}