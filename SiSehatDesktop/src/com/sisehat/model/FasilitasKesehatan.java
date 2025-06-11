package com.sisehat.model;

/**
 * Merepresentasikan satu Fasilitas Kesehatan (Rumah Sakit, Klinik, dll).
 */
public class FasilitasKesehatan {

    private String name;
    private String address;

    public FasilitasKesehatan(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // --- GETTERS ---

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}