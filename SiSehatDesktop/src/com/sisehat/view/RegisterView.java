package com.sisehat.view;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backToLoginButton;

    public RegisterView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Buat Akun Baru", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        fullNameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");
        backToLoginButton = new JButton("Sudah punya akun? Login di sini");

        // Mengatur posisi komponen
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1;
        this.add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        this.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        this.add(passwordField, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(registerButton, gbc);

        gbc.gridy = 5;
        this.add(backToLoginButton, gbc);
    }

    // Getters untuk diakses Controller
    public JTextField getFullNameField() { return fullNameField; }
    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getRegisterButton() { return registerButton; }
    public JButton getBackToLoginButton() { return backToLoginButton; }
}