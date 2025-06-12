package com.sisehat.data;

import com.sisehat.model.Disease;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseDAO {
    public List<Disease> getAllDiseases() {
        String sql = "SELECT disease_id, disease_name FROM diseases";
        List<Disease> diseases = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int diseaseId = rs.getInt("disease_id");
                String diseaseName = rs.getString("disease_name");

                Disease disease = new Disease(diseaseName);
                disease.setRelatedSymptomIds(getSymptomIdsForDisease(diseaseId)); // Ambil gejala terkait
                diseases.add(disease);
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil data penyakit: " + e.getMessage());
        }
        return diseases;
    }

    // Helper method untuk mengambil ID gejala dari tabel penghubung
    private List<Integer> getSymptomIdsForDisease(int diseaseId) {
        String sql = "SELECT symptom_id FROM disease_symptoms WHERE disease_id = ?";
        List<Integer> symptomIds = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, diseaseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                symptomIds.add(rs.getInt("symptom_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil relasi gejala: " + e.getMessage());
        }
        return symptomIds;
    }
}