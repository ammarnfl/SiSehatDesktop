package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Wallnut;

public class WallnutFactory implements PlantFactory<Wallnut> {
    public Wallnut create(int birthTime) {
        return new Wallnut(birthTime);
    }    
}
