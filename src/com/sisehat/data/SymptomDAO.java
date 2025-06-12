package com.sisehat.data;

import com.sisehat.model.Symptom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SymptomDAO {
    public List<Symptom> getAllSymptoms() {
        String sql = "SELECT symptom_id, symptom_name FROM symptoms ORDER BY symptom_id";
        List<Symptom> symptoms = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                symptoms.add(new Symptom(
                        rs.getInt("symptom_id"),
                        rs.getString("symptom_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return symptoms;
    }
}