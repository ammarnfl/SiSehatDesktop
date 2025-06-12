package com.sisehat.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JPanel {
    private JLabel welcomeLabel;
    private JButton startDiagnosisButton;
    private JButton profileButton;
    private JButton faskesButton;

    public DashboardView() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        welcomeLabel = new JLabel("Selamat Datang!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // load icons
        ImageIcon diagnosisIcon = new ImageIcon(getClass().getResource("/com/sisehat/view/assets/diagnosisIcon.png"));
        ImageIcon profileIcon = new ImageIcon(getClass().getResource("/com/sisehat/view/assets/profileIcon.png"));
        ImageIcon faskesIcon = new ImageIcon(getClass().getResource("/com/sisehat/view/assets/faskesIcon.png"));

        startDiagnosisButton = new JButton("Mulai Diagnosa Baru", diagnosisIcon);
        startDiagnosisButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startDiagnosisButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startDiagnosisButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        profileButton = new JButton("Profil & Riwayat Diagnosa", profileIcon);
        profileButton.setFont(new Font("Arial", Font.PLAIN, 18));
        profileButton.setHorizontalTextPosition(SwingConstants.CENTER);
        profileButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        faskesButton = new JButton("Daftar Fasilitas Kesehatan", faskesIcon);
        faskesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        faskesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        faskesButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(welcomeLabel, gbc);
        this.add(startDiagnosisButton, gbc);
        this.add(profileButton, gbc);
        this.add(faskesButton, gbc);
    }

    // Getters
    public JButton getStartDiagnosisButton() { return startDiagnosisButton; }
    public JButton getProfileButton() { return profileButton; }
    public void setWelcomeMessage(String name) {
        this.welcomeLabel.setText("Selamat Datang, " + name + "!");
    }
    public JButton getFaskesButton() { return faskesButton; }
}