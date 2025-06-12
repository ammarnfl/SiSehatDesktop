package com.sisehat.model;

import java.util.Date;
import java.util.List;

public class History {
    private int historyId; // <-- ATRIBUT BARU
    private String userEmail;
    private Date diagnosisDate;
    private List<Symptom> selectedSymptoms;
    private List<Disease> resultingDiseases;
    private List<FasilitasKesehatan> recommendedFaskes;
    private Feedback feedback;

    // Constructor utama kita sekarang butuh historyId
    public History(int historyId, String userEmail, Date diagnosisDate, List<Symptom> selectedSymptoms, List<Disease> resultingDiseases, List<FasilitasKesehatan> recommendedFaskes) {
        this.historyId = historyId; // <-- BARU
        this.userEmail = userEmail;
        this.diagnosisDate = diagnosisDate;
        this.selectedSymptoms = selectedSymptoms;
        this.resultingDiseases = resultingDiseases;
        this.recommendedFaskes = recommendedFaskes;
        this.feedback = null; // Feedback di-load terpisah
    }

    // Constructor yang lebih simpel untuk saat menyimpan (ID belum ada)
    public History(String userEmail, List<Symptom> selectedSymptoms, List<Disease> resultingDiseases, List<FasilitasKesehatan> recommendedFaskes) {
        this.historyId = -1; // -1 menandakan belum disimpan
        this.userEmail = userEmail;
        this.selectedSymptoms = selectedSymptoms;
        this.resultingDiseases = resultingDiseases;
        this.recommendedFaskes = recommendedFaskes;
        this.diagnosisDate = new Date();
        this.feedback = null;
    }

    // --- GETTERS & SETTERS ---
    public int getHistoryId() { return historyId; } // <-- GETTER BARU YANG DIBUTUHKAN
    public String getUserEmail() { return userEmail; }
    public Date getDiagnosisDate() { return diagnosisDate; }
    public List<Symptom> getSelectedSymptoms() { return selectedSymptoms; }
    public List<Disease> getResultingDiseases() { return resultingDiseases; }
    public List<FasilitasKesehatan> getRecommendedFaskes() { return recommendedFaskes; }
    public Feedback getFeedback() { return feedback; }
    public void setFeedback(Feedback feedback) { this.feedback = feedback; }
}