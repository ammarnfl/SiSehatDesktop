package com.sisehat.model;

public class Feedback {
    private int rating;       // Rating bintang (misal, 1-5)
    private String comment;   // Komentar atau masukan

    public Feedback(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    // --- GETTERS ---
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}