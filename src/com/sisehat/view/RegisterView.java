package com.sisehat.view;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {
    private JTextField fullNameField;
    private JTextField usernameField; // <-- FIELD BARU
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backToLoginButton;

    public RegisterView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; this.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; fullNameField = new JTextField(20); this.add(fullNameField, gbc);

        // Tambahkan baris untuk username
        gbc.gridx = 0; gbc.gridy = 1; this.add(new JLabel("Username:"), gbc); // <-- BARU
        gbc.gridx = 1; gbc.gridy = 1; usernameField = new JTextField(20); this.add(usernameField, gbc); // <-- BARU

        gbc.gridx = 0; gbc.gridy = 2; this.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; emailField = new JTextField(20); this.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; passwordField = new JPasswordField(20); this.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 4; registerButton = new JButton("Register"); this.add(registerButton, gbc);
        gbc.gridx = 1; gbc.gridy = 5; backToLoginButton = new JButton("Sudah punya akun? Login"); this.add(backToLoginButton, gbc);
    }

    // Getters
    public JTextField getFullNameField() { return fullNameField; }
    public JTextField getUsernameField() { return usernameField; } // <-- GETTER BARU
    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getRegisterButton() { return registerButton; }
    public JButton getBackToLoginButton() { return backToLoginButton; }
}