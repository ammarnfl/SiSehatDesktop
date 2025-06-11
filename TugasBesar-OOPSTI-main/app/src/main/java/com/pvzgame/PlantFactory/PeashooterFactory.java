package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Peashooter;

public class PeashooterFactory implements PlantFactory<Peashooter> {
    public Peashooter create(int birthTime) {
        return new Peashooter(birthTime);
    }
}
