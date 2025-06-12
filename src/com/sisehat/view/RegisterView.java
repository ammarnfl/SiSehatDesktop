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
        this.setOpaque(false); // We'll paint the gradient in paintComponent

        gbc.gridx = 0; gbc.gridy = 0; this.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; fullNameField = new JTextField(20); this.add(fullNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; this.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; usernameField = new JTextField(20); this.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; this.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; emailField = new JTextField(20); this.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; passwordField = new JPasswordField(20); this.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        registerButton = new JButton("Register");
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setOpaque(false);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(registerButton.getFont().deriveFont(Font.BOLD));
        registerButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        Color regNormal = new Color(0x66, 0x68, 0xE0);
        Color regHover = regNormal.darker();
        registerButton.setBackground(regNormal);
        registerButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight(), new Color(0xD1, 0xD1, 0xF5));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                g2.setColor(registerButton.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                super.paint(g, c);
                g2.dispose();
            }
        });
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                registerButton.setBackground(regHover);
                registerButton.repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                registerButton.setBackground(regNormal);
                registerButton.repaint();
            }
        });
        this.add(registerButton, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        backToLoginButton = new JButton("Sudah punya akun? Login");
        backToLoginButton.setFocusPainted(false);
        backToLoginButton.setContentAreaFilled(false);
        backToLoginButton.setOpaque(false);
        backToLoginButton.setForeground(Color.BLACK);
        backToLoginButton.setFont(backToLoginButton.getFont().deriveFont(Font.PLAIN));
        backToLoginButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        Color loginNormal = Color.WHITE;
        Color loginHover = loginNormal.darker();
        backToLoginButton.setBackground(loginNormal);
        backToLoginButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight(), new Color(0xD1, 0xD1, 0xF5));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                g2.setColor(backToLoginButton.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                super.paint(g, c);
                g2.dispose();
            }
        });
        backToLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backToLoginButton.setBackground(loginHover);
                backToLoginButton.repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                backToLoginButton.setBackground(loginNormal);
                backToLoginButton.repaint();
            }
        });
        this.add(backToLoginButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), new Color(0xD1, 0xD1, 0xF5));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }

    // Getters
    public JTextField getFullNameField() { return fullNameField; }
    public JTextField getUsernameField() { return usernameField; } // <-- GETTER BARU
    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getRegisterButton() { return registerButton; }
    public JButton getBackToLoginButton() { return backToLoginButton; }
}