package com.sisehat.controller;

import com.sisehat.data.AppDatabase;
import com.sisehat.model.FasilitasKesehatan;
import com.sisehat.view.FaskesListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class FaskesListController {
    private FaskesListView view;
    private NavigationController navigationController;
    private FaskesDetailController faskesDetailController;

    public FaskesListController(FaskesListView view, NavigationController nc, FaskesDetailController fdc) {
        this.view = view;
        this.navigationController = nc;
        this.faskesDetailController = fdc;
        this.view.getFilterButton().addActionListener(e -> updateFaskesList());
        this.view.getBackButton().addActionListener(e -> navigationController.showCard("DASHBOARD"));
        updateFaskesList();
    }

    private void updateFaskesList() {
        String searchText = view.getSearchField().getText().toLowerCase();
        String bpjsStatus = (String) view.getBpjsFilter().getSelectedItem();
        String faskesType = (String) view.getTypeFilter().getSelectedItem();
        List<FasilitasKesehatan> allFaskes = AppDatabase.healthFacilities;
        List<FasilitasKesehatan> filteredFaskes = allFaskes.stream()
                .filter(f -> f.getName().toLowerCase().contains(searchText))
                .filter(f -> {
                    if ("Menerima BPJS".equals(bpjsStatus)) return f.isHasBpjs();
                    if ("Non-BPJS".equals(bpjsStatus)) return !f.isHasBpjs();
                    return true;
                })
                .filter(f -> {
                    if (!"Semua Tipe".equals(faskesType)) return f.getType().equals(faskesType);
                    return true;
                })
                .collect(Collectors.toList());
        displayResults(filteredFaskes);
    }

    private void displayResults(List<FasilitasKesehatan> faskesList) {
        JPanel listPanel = view.getFaskesListPanel();
        listPanel.removeAll();
        if (faskesList.isEmpty()) {
            listPanel.add(new JLabel("Fasilitas kesehatan tidak ditemukan."));
        } else {
            for (FasilitasKesehatan faskes : faskesList) {
                JPanel itemPanel = createFaskesItemPanel(faskes);
                itemPanel.addMouseListener(createFaskesClickListener(faskes));
                listPanel.add(itemPanel);
                listPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        listPanel.revalidate();
        listPanel.repaint();
    }

    private JPanel createFaskesItemPanel(FasilitasKesehatan faskes) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Color.WHITE);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(faskes.getImagePath()));
            Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            panel.add(new JLabel(new ImageIcon(image)), BorderLayout.WEST);
        } catch (Exception e) {
            panel.add(new JLabel("  No Img  "), BorderLayout.WEST);
        }
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        JLabel nameLabel = new JLabel(faskes.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        textPanel.add(nameLabel);
        JLabel addressLabel = new JLabel(faskes.getAddress());
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textPanel.add(addressLabel);
        panel.add(textPanel, BorderLayout.CENTER);
        return panel;
    }

    private MouseAdapter createFaskesClickListener(FasilitasKesehatan faskes) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                faskesDetailController.setFaskesToShow(faskes, "FASKES_LIST");
                navigationController.showCard("FASKES_DETAIL");
            }
        };
    }
}