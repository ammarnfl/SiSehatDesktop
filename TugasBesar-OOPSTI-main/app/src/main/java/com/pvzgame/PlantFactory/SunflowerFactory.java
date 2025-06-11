package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Sunflower;

public class SunflowerFactory implements PlantFactory<Sunflower> {
    public Sunflower create(int birthTime) {
        return new Sunflower(birthTime);
    }
}
