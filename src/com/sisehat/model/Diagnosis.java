package com.sisehat.model;

public class Diagnosis {
    private Disease disease;
    private int score;
    private int totalGejalaPenyakit;

    public Diagnosis(Disease disease, int score, int totalGejalaPenyakit) {
        this.disease = disease;
        this.score = score;
        this.totalGejalaPenyakit = totalGejalaPenyakit;
    }

    public Disease getDisease() {
        return disease;
    }

    public int getScore() {
        return score;
    }

    public double getMatchPercentage() {
        if (totalGejalaPenyakit == 0) return 0.0;
        return ((double) score / totalGejalaPenyakit) * 100;
    }
}