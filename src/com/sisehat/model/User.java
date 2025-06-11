package com.sisehat.model;

public class User {
    private String fullName;
    private String username; // <-- ATRIBUT BARU
    private String email;
    private String password;

    // Constructor diupdate
    public User(String fullName, String username, String email, String password) {
        this.fullName = fullName;
        this.username = username; // <-- BARU
        this.email = email;
        this.password = password;
    }

    // --- GETTERS ---
    public String getFullName() { return fullName; }
    public String getUsername() { return username; } // <-- GETTER BARU
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}