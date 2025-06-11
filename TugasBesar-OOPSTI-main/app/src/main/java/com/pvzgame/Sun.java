package com.pvzgame;

public class Sun {
    // Singleton

    /// Attributes
    private static Sun instance = null;
    private int sunPoints;

    // Private Constructor
    private Sun() {
    sunPoints = 50;
    }

    // Singleton Instance and Thread Safety
    public static Sun getInstance() {
        if (instance == null) {
            synchronized(Sun.class) {
                if (instance == null) instance = new Sun();
            }
        }
        return instance;
    }

    // Getters and Setters
    public int getSunPoints() {
        return sunPoints;
    }

    public void setSunPoints(int sunPoints) {
        this.sunPoints = sunPoints;
    }

    public void addSun(int sunPoints) {
        this.sunPoints += sunPoints;
    }

    public void subtractSun(int sunPoints) {
        this.sunPoints -= sunPoints;
    }
}