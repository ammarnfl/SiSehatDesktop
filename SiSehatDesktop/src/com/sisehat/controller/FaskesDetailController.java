package com.sisehat.controller;

import com.sisehat.model.FasilitasKesehatan;
import com.sisehat.view.FaskesDetailView;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class FaskesDetailController {
    private FaskesDetailView view;
    private NavigationController navigationController;
    private FasilitasKesehatan currentFaskes;
    private String previousPage; // Untuk tahu harus kembali ke mana

    public FaskesDetailController(FaskesDetailView view, NavigationController navigationController) {
        this.view = view;
        this.navigationController = navigationController;
        this.view.getBackButton().addActionListener(e -> goBack());
        this.view.getContactButton().addActionListener(e -> openWhatsApp());
    }

    public void setFaskesToShow(FasilitasKesehatan faskes, String previousPage) {
        this.currentFaskes = faskes;
        this.previousPage = previousPage; // Simpan halaman sebelumnya
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

    private void goBack() {
        // Kembali ke halaman yang benar
        if (previousPage != null) {
            navigationController.showCard(previousPage);
        } else {
            navigationController.showCard("DASHBOARD"); // Fallback
        }
    }
}