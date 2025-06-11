package com.sisehat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Merepresentasikan satu Penyakit (Disease).
 * Setiap penyakit punya nama dan daftar ID gejala yang berhubungan.
 */
public class Disease {

    private String name; // Nama penyakit, contoh: "Demam Berdarah"
    private List<Integer> relatedSymptomIds; // Daftar ID dari gejala-gejala yang berhubungan

    public Disease(String name) {
        this.name = name;
        this.relatedSymptomIds = new ArrayList<>(); // Inisialisasi list agar tidak error
    }

    // --- GETTERS & SETTERS ---

    public String getName() {
        return name;
    }

    public List<Integer> getRelatedSymptomIds() {
        return relatedSymptomIds;
    }

    // Setter khusus untuk mengatur daftar ID gejala
    public void setRelatedSymptomIds(List<Integer> relatedSymptomIds) {
        this.relatedSymptomIds = relatedSymptomIds;
    }
}