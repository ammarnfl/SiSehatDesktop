package com.sisehat.model;

import java.util.Date;
import java.util.List;

public class History {
    private String userEmail;
    private Date diagnosisDate;
    private List<Symptom> selectedSymptoms;
    private List<Disease> resultingDiseases;
    private Feedback feedback; // Awalnya null, bisa diisi nanti

    public History(String userEmail, List<Symptom> selectedSymptoms, List<Disease> resultingDiseases) {
        this.userEmail = userEmail;
        this.selectedSymptoms = selectedSymptoms;
        this.resultingDiseases = resultingDiseases;
        this.diagnosisDate = new Date(); // Otomatis catat waktu saat ini
        this.feedback = null; // Feedback belum ada saat riwayat dibuat
    }

    // --- GETTERS & SETTERS ---
    public String getUserEmail() { return userEmail; }
    public Date getDiagnosisDate() { return diagnosisDate; }
    public List<Symptom> getSelectedSymptoms() { return selectedSymptoms; }
    public List<Disease> getResultingDiseases() { return resultingDiseases; }
    public Feedback getFeedback() { return feedback; }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}