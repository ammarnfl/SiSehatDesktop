package com.sisehat.model;

import java.util.List;

public class FasilitasKesehatan {
    private String name;
    private String address;
    private String city;
    private String operatingHours;
    private boolean hasBpjs;
    private String contactWhatsapp;
    private double rating;
    private String type;
    private String imagePath;
    private List<String> specializations; // Ditambahkan

    public FasilitasKesehatan(String name, String address, String city, String operatingHours, boolean hasBpjs, String contactWhatsapp, double rating, String type, String imagePath, List<String> specializations) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.operatingHours = operatingHours;
        this.hasBpjs = hasBpjs;
        this.contactWhatsapp = contactWhatsapp;
        this.rating = rating;
        this.type = type;
        this.imagePath = imagePath;
        this.specializations = specializations; // Ditambahkan
    }

    // --- GETTERS ---
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getOperatingHours() { return operatingHours; }
    public boolean isHasBpjs() { return hasBpjs; }
    public String getContactWhatsapp() { return contactWhatsapp; }
    public double getRating() { return rating; }
    public String getType() { return type; }
    public String getImagePath() { return imagePath; }
    public List<String> getSpecializations() { return specializations; } // Ditambahkan
}