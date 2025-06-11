package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Squash;

public class SquashFactory implements PlantFactory<Squash> {
    public Squash create(int birthTime) {
        return new Squash(birthTime);
    }
    
}
