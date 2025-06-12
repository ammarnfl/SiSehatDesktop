package com.sisehat.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JTextField identifierField; // Ganti nama dari emailField
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ubah label agar lebih jelas
        gbc.gridx = 0; gbc.gridy = 0; this.add(new JLabel("Email atau Username:"), gbc); // <-- LABEL DIUBAH
        gbc.gridx = 1; gbc.gridy = 0; identifierField = new JTextField(20); this.add(identifierField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; passwordField = new JPasswordField(20); this.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2; loginButton = new JButton("Login"); this.add(loginButton, gbc);
        gbc.gridx = 1; gbc.gridy = 3; registerButton = new JButton("Belum punya akun? Register"); this.add(registerButton, gbc);
    }

    public void reset() {
        identifierField.setText("");
        passwordField.setText("");
    }

    // Getters
    public JTextField getIdentifierField() { return identifierField; } // Getter diubah
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JButton getRegisterButton() { return registerButton; }
}