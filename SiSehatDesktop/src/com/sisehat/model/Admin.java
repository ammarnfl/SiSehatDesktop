package com.sisehat.model;

/**
 * Kelas Admin, merupakan turunan dari User.
 * Untuk saat ini, propertinya sama dengan User,
 * namun kelas ini disiapkan untuk pengembangan fitur admin di masa depan.
 */
public class Admin extends User {

    public Admin(String fullName, String email, String password) {
        // Memanggil constructor dari kelas induknya (User)
        super(fullName, email, password);
    }
}