package com.sisehat.view;

import com.sisehat.model.FasilitasKesehatan;
import javax.swing.*;
import java.awt.*;

public class FaskesDetailView extends JPanel {
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel hoursLabel;
    private JLabel bpjsLabel;
    private JLabel ratingLabel;
    private JLabel typeLabel;
    private JButton contactButton;
    private JButton backButton;

    public FaskesDetailView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        mainContentPanel.add(imageLabel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        nameLabel = new JLabel("Nama Fasilitas Kesehatan", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(nameLabel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        addressLabel = new JLabel("Alamat lengkap", SwingConstants.CENTER);
        addressLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(addressLabel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel detailsGridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        detailsGridPanel.add(new JLabel("Jam Operasional:"));
        hoursLabel = new JLabel();
        detailsGridPanel.add(hoursLabel);
        detailsGridPanel.add(new JLabel("Status BPJS:"));
        bpjsLabel = new JLabel();
        detailsGridPanel.add(bpjsLabel);
        detailsGridPanel.add(new JLabel("Rating Pengguna:"));
        ratingLabel = new JLabel();
        detailsGridPanel.add(ratingLabel);
        detailsGridPanel.add(new JLabel("Tipe Faskes:"));
        typeLabel = new JLabel();
        detailsGridPanel.add(typeLabel);
        mainContentPanel.add(detailsGridPanel);

        contactButton = new JButton("Hubungi via WhatsApp");
        contactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContentPanel.add(contactButton);

        this.add(mainContentPanel, BorderLayout.CENTER);

        backButton = new JButton("Kembali");
        this.add(backButton, BorderLayout.SOUTH);
    }

    public void displayFaskesDetails(FasilitasKesehatan faskes) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(faskes.getImagePath()));
            Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            imageLabel.setText("Gambar tidak tersedia");
            imageLabel.setIcon(null);
        }
        nameLabel.setText(faskes.getName());
        addressLabel.setText(faskes.getAddress() + ", " + faskes.getCity());
        hoursLabel.setText(faskes.getOperatingHours());
        bpjsLabel.setText(faskes.isHasBpjs() ? "✓ Menerima BPJS" : "✗ Non-BPJS");
        ratingLabel.setText(faskes.getRating() + " ★");
        typeLabel.setText(faskes.getType());
    }

    public JButton getBackButton() { return backButton; }
    public JButton getContactButton() { return contactButton; }
}