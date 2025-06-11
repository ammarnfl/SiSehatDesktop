package com.sisehat.model;

/**
 * Kelas ini merepresentasikan satu Gejala (Symptom).
 * Setiap gejala memiliki ID unik dan sebuah nama.
 */
public class Symptom {

    private int id; // ID unik untuk setiap gejala
    private String name; // Nama dari gejala, contoh: "Demam tinggi"

    /**
     * Constructor untuk membuat objek Symptom baru.
     * @param id ID unik gejala.
     * @param name Nama deskriptif gejala.
     */
    public Symptom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // --- GETTERS ---
    // Metode ini digunakan untuk mengambil data dari objek tanpa bisa mengubahnya.

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}