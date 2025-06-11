package com.sisehat.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

// Nama kelas diganti
public class FaskesListView extends JPanel {
    private JTextField searchField;
    private JComboBox<String> bpjsFilter;
    private JComboBox<String> typeFilter;
    private JButton filterButton;
    private JPanel faskesListPanel;
    private JButton backButton;

    public FaskesListView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBorder(new TitledBorder("Pencarian & Filter"));

        searchField = new JTextField(20);
        bpjsFilter = new JComboBox<>(new String[]{"Semua BPJS", "Menerima BPJS", "Non-BPJS"});
        typeFilter = new JComboBox<>(new String[]{"Semua Tipe", "RS Umum", "Klinik", "Puskesmas"});
        filterButton = new JButton("Cari");

        filterPanel.add(new JLabel("Cari Nama:"));
        filterPanel.add(searchField);
        filterPanel.add(new JLabel("BPJS:"));
        filterPanel.add(bpjsFilter);
        filterPanel.add(new JLabel("Tipe:"));
        filterPanel.add(typeFilter);
        filterPanel.add(filterButton);
        this.add(filterPanel, BorderLayout.NORTH);

        faskesListPanel = new JPanel();
        faskesListPanel.setLayout(new BoxLayout(faskesListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(faskesListPanel);
        this.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Kembali ke Dashboard");
        this.add(backButton, BorderLayout.SOUTH);
    }

    public void reset() {
        searchField.setText(""); // Kosongkan search bar
        bpjsFilter.setSelectedIndex(0); // Kembalikan ke pilihan pertama
        typeFilter.setSelectedIndex(0); // Kembalikan ke pilihan pertama
        // Setelah di-reset, panggil controller untuk memperbarui daftar sesuai kondisi default
        filterButton.doClick();
    }

    // Getter untuk diakses Controller
    public JTextField getSearchField() { return searchField; }
    public JComboBox<String> getBpjsFilter() { return bpjsFilter; }
    public JComboBox<String> getTypeFilter() { return typeFilter; }
    public JButton getFilterButton() { return filterButton; }
    public JPanel getFaskesListPanel() { return faskesListPanel; }
    public JButton getBackButton() { return backButton; }
}