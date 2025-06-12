package com.sisehat.data;

import com.sisehat.model.FasilitasKesehatan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FasilitasKesehatanDAO {
    public List<FasilitasKesehatan> getAllFasilitasKesehatan() {
        String sql = "SELECT * FROM health_facilities";
        List<FasilitasKesehatan> facilities = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int facilityId = rs.getInt("facility_id");
                FasilitasKesehatan faskes = new FasilitasKesehatan(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("operating_hours"),
                        rs.getInt("has_bpjs") == 1, // Konversi 1/0 ke boolean
                        rs.getString("contact_whatsapp"),
                        rs.getDouble("rating"),
                        rs.getString("type"),
                        rs.getString("image_path"),
                        getSpecializationsForFacility(facilityId) // Ambil spesialisasi
                );
                facilities.add(faskes);
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil data faskes: " + e.getMessage());
        }
        return facilities;
    }

    // Helper method untuk mengambil nama spesialisasi penyakit
    private List<String> getSpecializationsForFacility(int facilityId) {
        // Query ini menggabungkan 3 tabel untuk mendapatkan nama penyakit
        String sql = "SELECT d.disease_name FROM facility_specializations fs " +
                "JOIN diseases d ON fs.disease_id = d.disease_id " +
                "WHERE fs.facility_id = ?";
        List<String> specializations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, facilityId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                specializations.add(rs.getString("disease_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil spesialisasi faskes: " + e.getMessage());
        }
        return specializations;
    }
}