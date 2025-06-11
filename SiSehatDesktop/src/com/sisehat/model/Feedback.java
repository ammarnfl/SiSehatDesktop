package com.sisehat.model;

public class Feedback {
    private int diseaseRating;   // Rating untuk akurasi diagnosa penyakit
    private int faskesRating;    // Rating untuk kesesuaian rekomendasi faskes
    private String comment;      // Komentar gabungan

    /**
     * Constructor untuk membuat objek Feedback.
     * @param diseaseRating Rating penyakit (1-5).
     * @param faskesRating Rating faskes (1-5). Jika tidak ada, bisa diisi 0 atau -1.
     * @param comment Komentar dari pengguna.
     */
    public Feedback(int diseaseRating, int faskesRating, String comment) {
        this.diseaseRating = diseaseRating;
        this.faskesRating = faskesRating;
        this.comment = comment;
    }

    // --- GETTERS ---
    public int getDiseaseRating() { return diseaseRating; }
    public int getFaskesRating() { return faskesRating; }
    public String getComment() { return comment; }
}