package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Lilypad;

public class LilypadFactory implements PlantFactory<Lilypad> {
    public Lilypad create(int birthTime) {
        return new Lilypad(birthTime);
    }
}
