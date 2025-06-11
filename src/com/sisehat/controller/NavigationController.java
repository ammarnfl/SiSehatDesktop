package com.sisehat.controller;

import javax.swing.*;
import java.awt.*;

/**
 * Controller pusat yang bertanggung jawab untuk navigasi antar panel (View).
 */
public class NavigationController {
    private JPanel mainPanel; // Panel yang menggunakan CardLayout
    private CardLayout cardLayout;

    public NavigationController(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
    }

    /**
     * Method untuk menampilkan sebuah "kartu" atau panel berdasarkan namanya.
     * @param cardName nama panel yang ingin ditampilkan (misal: "LOGIN", "REGISTER", "MAIN")
     */
    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }
}