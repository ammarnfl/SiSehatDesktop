package com.sisehat.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL ini mengasumsikan file sisehat.db ada di folder utama proyek
    private static final String URL = "jdbc:sqlite:sisehat.db";

    /**
     * Membuat dan mengembalikan sebuah koneksi ke database SQLite.
     * @return Objek Connection atau null jika gagal.
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // Class.forName("org.sqlite.JDBC"); // Baris ini kadang diperlukan di setup lama
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Koneksi ke SQLite gagal: " + e.getMessage());
        }
        return conn;
    }
}