package com.sisehat.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JPanel {
    // Tombol-tombol untuk navbar
    private JButton navDiagnosaButton;
    private JButton navFaskesButton;
    private JButton navProfileButton;
    private JButton navLogoutButton;

    // Konten utama yang sudah ada
    private JLabel welcomeLabel;
    private JButton startDiagnosisButton;
    private JButton profileButton;
    private JButton faskesButton;

    public DashboardView() {
        // Layout utama sekarang BorderLayout
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // ==========================================================
        // BAGIAN 1: MEMBUAT NAVBAR KECIL DI ATAS (NORTH)
        // ==========================================================
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JPanel leftNavPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        navDiagnosaButton = new JButton("Diagnosa");
        navFaskesButton = new JButton("Daftar Faskes");
        navProfileButton = new JButton("Profil & Riwayat");
        formatNavButton(navDiagnosaButton);
        formatNavButton(navFaskesButton);
        formatNavButton(navProfileButton);
        leftNavPanel.add(navDiagnosaButton);
        leftNavPanel.add(navFaskesButton);
        leftNavPanel.add(navProfileButton);

        JPanel rightNavPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        navLogoutButton = new JButton("Logout");
        formatNavButton(navLogoutButton);
        navLogoutButton.setForeground(Color.RED);
        rightNavPanel.add(navLogoutButton);

        navPanel.add(leftNavPanel, BorderLayout.WEST);
        navPanel.add(rightNavPanel, BorderLayout.EAST);

        this.add(navPanel, BorderLayout.NORTH);


        // ==========================================================
        // BAGIAN 2: KONTEN UTAMA DENGAN IKON DI TENGAH (CENTER)
        // ==========================================================
        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        welcomeLabel = new JLabel("Selamat Datang!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // Load icons
        ImageIcon diagnosisIcon = new ImageIcon(getClass().getResource("/assets/diagnosisIcon.png"));
        ImageIcon profileIcon = new ImageIcon(getClass().getResource("/assets/profileIcon.png"));
        ImageIcon faskesIcon = new ImageIcon(getClass().getResource("/assets/faskesIcon.png"));

        startDiagnosisButton = new JButton("Mulai Diagnosa Baru", diagnosisIcon);
        profileButton = new JButton("Profil & Riwayat Diagnosa", profileIcon);
        faskesButton = new JButton("Daftar Fasilitas Kesehatan", faskesIcon);

        formatBigButton(startDiagnosisButton);
        formatBigButton(profileButton);
        formatBigButton(faskesButton);

        mainContentPanel.add(welcomeLabel, gbc);
        mainContentPanel.add(startDiagnosisButton, gbc);
        mainContentPanel.add(profileButton, gbc);
        mainContentPanel.add(faskesButton, gbc);

        this.add(mainContentPanel, BorderLayout.CENTER);
    }

    // Helper untuk styling tombol navbar
    private void formatNavButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    // Helper untuk styling tombol besar (dari kodemu)
    private void formatBigButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    // Getters
    public JButton getNavDiagnosaButton() { return navDiagnosaButton; }
    public JButton getNavFaskesButton() { return navFaskesButton; }
    public JButton getNavProfileButton() { return navProfileButton; }
    public JButton getNavLogoutButton() { return navLogoutButton; }

    public JButton getStartDiagnosisButton() { return startDiagnosisButton; }
    public JButton getProfileButton() { return profileButton; }
    public JButton getFaskesButton() { return faskesButton; }

    // Menggunakan setter agar lebih rapi (sesuai kodemu)
    public void setWelcomeMessage(String name) {
        this.welcomeLabel.setText("Selamat Datang, " + name + "!");
    }
}