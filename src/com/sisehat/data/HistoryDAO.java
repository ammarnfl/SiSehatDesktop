package com.sisehat.data;

import com.sisehat.model.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDAO {

    // Method untuk MENGAMBIL semua riwayat milik seorang user
    public List<History> getHistoriesByUser(User user) {
        String sql = "SELECT history_id, diagnosis_date FROM histories WHERE user_id = ? ORDER BY diagnosis_date DESC";
        List<History> histories = new ArrayList<>();
        int userId = getUserIdByEmail(user.getEmail());
        if (userId == -1) return histories; // Return daftar kosong jika user ID tidak ditemukan

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int historyId = rs.getInt("history_id");
                String dateString = rs.getString("diagnosis_date");
                Date diagnosisDate = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").parse(dateString);

                // "Rehydrate" objek History dengan mengambil detail dari tabel lain
                List<Symptom> symptoms = getSymptomsForHistory(historyId);
                List<Disease> diseases = getDiseasesForHistory(historyId);
                List<FasilitasKesehatan> faskes = getFaskesForHistory(historyId);
                Feedback feedback = getFeedbackForHistory(historyId);

                History history = new History(historyId, user.getEmail(), diagnosisDate, symptoms, diseases, faskes);
                history.setFeedback(feedback);
                histories.add(history);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return histories;
    }

    // Method untuk menyimpan satu objek History lengkap ke database
    public void saveHistory(History history) {
        String insertHistorySQL = "INSERT INTO histories(user_id, diagnosis_date) VALUES(?, ?)";
        Connection conn = DatabaseConnection.connect();

        try {
            // Matikan auto-commit untuk memulai transaksi
            conn.setAutoCommit(false);

            // 1. Simpan ke tabel histories dan dapatkan ID yang baru dibuat
            PreparedStatement pstmtHistory = conn.prepareStatement(insertHistorySQL, Statement.RETURN_GENERATED_KEYS);
            // Cari user_id berdasarkan email
            int userId = getUserIdByEmail(history.getUserEmail());
            if (userId == -1) throw new SQLException("User tidak ditemukan.");

            pstmtHistory.setInt(1, userId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
            pstmtHistory.setString(2, sdf.format(history.getDiagnosisDate()));
            pstmtHistory.executeUpdate();

            ResultSet generatedKeys = pstmtHistory.getGeneratedKeys();
            int historyId = -1;
            if (generatedKeys.next()) {
                historyId = generatedKeys.getInt(1);
            }
            if (historyId == -1) throw new SQLException("Gagal membuat history record.");

            // 2. Simpan gejala-gejala yang dipilih
            String insertSymptomsSQL = "INSERT INTO history_symptoms(history_id, symptom_id) VALUES(?,?)";
            PreparedStatement pstmtSymptoms = conn.prepareStatement(insertSymptomsSQL);
            for (Symptom symptom : history.getSelectedSymptoms()) {
                pstmtSymptoms.setInt(1, historyId);
                pstmtSymptoms.setInt(2, symptom.getId());
                pstmtSymptoms.addBatch();
            }
            pstmtSymptoms.executeBatch();

            // 3. Simpan penyakit hasil diagnosa
            String insertDiseasesSQL = "INSERT INTO history_diseases(history_id, disease_id) VALUES(?,?)";
            PreparedStatement pstmtDiseases = conn.prepareStatement(insertDiseasesSQL);
            // Kita perlu mengambil disease_id dari nama penyakit
            for (Disease disease : history.getResultingDiseases()) {
                pstmtDiseases.setInt(1, historyId);
                pstmtDiseases.setInt(2, getDiseaseIdByName(disease.getName()));
                pstmtDiseases.addBatch();
            }
            pstmtDiseases.executeBatch();

            // 4. Simpan faskes yang direkomendasikan (jika ada)
            if (history.getRecommendedFaskes() != null && !history.getRecommendedFaskes().isEmpty()) {
                String insertFaskesSQL = "INSERT INTO history_faskes(history_id, facility_id) VALUES(?,?)";
                PreparedStatement pstmtFaskes = conn.prepareStatement(insertFaskesSQL);
                for (FasilitasKesehatan faskes : history.getRecommendedFaskes()) {
                    pstmtFaskes.setInt(1, historyId);
                    pstmtFaskes.setInt(2, getFacilityIdByName(faskes.getName()));
                    pstmtFaskes.addBatch();
                }
                pstmtFaskes.executeBatch();
            }

            // Jika semua berhasil, commit transaksi
            conn.commit();

        } catch (SQLException e) {
            System.err.println("Transaksi Gagal! Melakukan rollback.");
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method untuk menyimpan atau mengupdate feedback
    public void saveFeedback(Feedback feedback, int historyId) {
        // "INSERT OR REPLACE" adalah fitur SQLite yang sangat berguna
        String sql = "INSERT OR REPLACE INTO feedback (history_id, disease_rating, faskes_rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, historyId);
            pstmt.setInt(2, feedback.getDiseaseRating());
            pstmt.setInt(3, feedback.getFaskesRating());
            pstmt.setString(4, feedback.getComment());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // === Kumpulan Method untuk Mengambil Detail Riwayat (Rehydration) ===

    private List<Symptom> getSymptomsForHistory(int historyId) throws SQLException {
        String sql = "SELECT s.* FROM symptoms s JOIN history_symptoms hs ON s.symptom_id = hs.symptom_id WHERE hs.history_id = ?";
        List<Symptom> symptoms = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, historyId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                symptoms.add(new Symptom(rs.getInt("symptom_id"), rs.getString("symptom_name")));
            }
        }
        return symptoms;
    }

    private List<Disease> getDiseasesForHistory(int historyId) throws SQLException {
        String sql = "SELECT d.* FROM diseases d JOIN history_diseases hd ON d.disease_id = hd.disease_id WHERE hd.history_id = ?";
        List<Disease> diseases = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, historyId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                diseases.add(new Disease(rs.getString("disease_name")));
            }
        }
        return diseases;
    }

    private List<FasilitasKesehatan> getFaskesForHistory(int historyId) throws SQLException {
        String sql = "SELECT f.* FROM health_facilities f JOIN history_faskes hf ON f.facility_id = hf.facility_id WHERE hf.history_id = ?";
        List<FasilitasKesehatan> faskesList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, historyId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                // Untuk simplifikasi, kita tidak load lagi specializations di sini
                faskesList.add(new FasilitasKesehatan(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("operating_hours"), rs.getInt("has_bpjs") == 1, rs.getString("contact_whatsapp"), rs.getDouble("rating"), rs.getString("type"), rs.getString("image_path"), new ArrayList<>()));
            }
        }
        return faskesList;
    }

    private Feedback getFeedbackForHistory(int historyId) throws SQLException {
        String sql = "SELECT * FROM feedback WHERE history_id = ?";
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, historyId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Feedback(rs.getInt("disease_rating"), rs.getInt("faskes_rating"), rs.getString("comment"));
            }
        }
        return null;
    }

    // === HELPER METHODS (yang sebelumnya placeholder, sekarang kita isi) ===

    private int getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("user_id");
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    private int getDiseaseIdByName(String name) {
        String sql = "SELECT disease_id FROM diseases WHERE disease_name = ?";
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("disease_id");
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    private int getFacilityIdByName(String name) {
        String sql = "SELECT facility_id FROM health_facilities WHERE name = ?";
        try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("facility_id");
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }
}

