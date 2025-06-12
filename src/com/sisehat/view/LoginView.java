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

        // Gradient background for the panel
        this.setOpaque(false); // We'll paint the gradient in paintComponent

        // Ubah label agar lebih jelas
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel("Email atau Username:"), gbc); // <-- LABEL DIUBAH
        gbc.gridx = 1;
        gbc.gridy = 0;
        identifierField = new JTextField(20);
        identifierField.setOpaque(false);
        identifierField.setBackground(new Color(0,0,0,0));
        this.add(identifierField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        passwordField.setOpaque(false);
        passwordField.setBackground(new Color(0,0,0,0));
        this.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(false);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(loginButton.getFont().deriveFont(Font.BOLD));
        loginButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        Color loginNormal = new Color(0x66, 0x68, 0xE0);
        Color loginHover = loginNormal.darker();
        loginButton.setBackground(loginNormal);
        loginButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight(), new Color(0xD1, 0xD1, 0xF5));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                g2.setColor(loginButton.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                super.paint(g, c);
                g2.dispose();
            }
        });
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginButton.setBackground(loginHover);
                loginButton.repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginButton.setBackground(loginNormal);
                loginButton.repaint();
            }
        });
        this.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        registerButton = new JButton("Belum punya akun? Register");
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setOpaque(false);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(registerButton.getFont().deriveFont(Font.PLAIN));
        registerButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        Color regNormal = Color.WHITE;
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Gradient background
        GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), new Color(0xD1, 0xD1, 0xF5));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
        // Draw logo at top right
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/com/sisehat/view/assets/sisehat-logo.png"));
            int logoWidth = getWidth() / 3; // scale logo to 1/3 panel width
            int logoHeight = logoIcon.getIconHeight() * logoWidth / logoIcon.getIconWidth();
            int x = getWidth() - logoWidth - 20; // 20px margin from right
            int y = 20; // 20px margin from top
            Image logoImg = logoIcon.getImage().getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.18f)); // 18% opacity
            g2.drawImage(logoImg, x, y, logoWidth, logoHeight, this);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } catch (Exception e) {
            // If logo not found, do nothing
        }
        g2.dispose();
    }

    // Getters
    public JTextField getIdentifierField() {
        return identifierField;
    } // Getter diubah

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}