package com.sisehat.controller;

import com.sisehat.model.FasilitasKesehatan;
import com.sisehat.view.FaskesDetailView;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URLEncoder; // <-- Import baru
import java.nio.charset.StandardCharsets; // <-- Import baru

public class FaskesDetailController {
    private FaskesDetailView view;
    private NavigationController navigationController;
    private FasilitasKesehatan currentFaskes;
    private String previousPage;

    public FaskesDetailController(FaskesDetailView view, NavigationController navigationController) {
        this.view = view;
        this.navigationController = navigationController;

        // Pasang listener untuk semua tombol
        this.view.getBackButton().addActionListener(e -> goBack());
        this.view.getContactButton().addActionListener(e -> openWhatsApp());
        this.view.getOpenMapsButton().addActionListener(e -> openGoogleMaps()); // <-- LISTENER BARU
    }

    public void setFaskesToShow(FasilitasKesehatan faskes, String previousPage) {
        this.currentFaskes = faskes;
        this.previousPage = previousPage;
        view.displayFaskesDetails(faskes);
    }

    private void openWhatsApp() {
        if (currentFaskes == null) return;
        try {
            Desktop.getDesktop().browse(new URI("https://wa.me/" + currentFaskes.getContactWhatsapp()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal membuka browser.");
        }
    }

    // ==================================================================
    // METHOD BARU UNTUK MEMBUKA GOOGLE MAPS
    // ==================================================================
    private void openGoogleMaps() {
        if (currentFaskes == null) return;
        try {
            // Gabungkan nama dan alamat untuk query pencarian
            String query = currentFaskes.getName() + ", " + currentFaskes.getAddress();
            // Encode query agar aman untuk URL (mengganti spasi dengan %20, dll)
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            // Buat URL Google Maps
            URI mapsUri = new URI("https://www.google.com/maps/search/?api=1&query=" + encodedQuery);

            // Buka URL di browser default
            Desktop.getDesktop().browse(mapsUri);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal membuka Google Maps.");
            ex.printStackTrace();
        }
    }

    private void goBack() {
        if (previousPage != null) {
            navigationController.showCard(previousPage);
        } else {
            navigationController.showCard("DASHBOARD");
        }
    }
}