package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Kelp;

public class KelpFactory implements PlantFactory<Kelp> {
    public Kelp create(int birthTime) {
        return new Kelp(birthTime);
    }
}
