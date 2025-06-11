package com.sisehat.model;

import java.util.Date;
import java.util.List;

public class History {
    private String userEmail;
    private Date diagnosisDate;
    private List<Symptom> selectedSymptoms;
    private List<Disease> resultingDiseases;
    private List<FasilitasKesehatan> recommendedFaskes; // ATRIBUT BARU
    private Feedback feedback;

    // CONSTRUCTOR BARU YANG LEBIH FLEKSIBEL
    public History(String userEmail, List<Symptom> selectedSymptoms, List<Disease> resultingDiseases, List<FasilitasKesehatan> recommendedFaskes) {
        this.userEmail = userEmail;
        this.selectedSymptoms = selectedSymptoms;
        this.resultingDiseases = resultingDiseases;
        this.recommendedFaskes = recommendedFaskes; // Bisa null jika hanya save penyakit
        this.diagnosisDate = new Date();
        this.feedback = null;
    }

    // --- GETTERS & SETTERS ---
    public String getUserEmail() { return userEmail; }
    public Date getDiagnosisDate() { return diagnosisDate; }
    public List<Symptom> getSelectedSymptoms() { return selectedSymptoms; }
    public List<Disease> getResultingDiseases() { return resultingDiseases; }
    public List<FasilitasKesehatan> getRecommendedFaskes() { return recommendedFaskes; } // Getter baru
    public Feedback getFeedback() { return feedback; }
    public void setFeedback(Feedback feedback) { this.feedback = feedback; }
}