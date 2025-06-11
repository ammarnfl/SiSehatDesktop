package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.PotatoMine;

public class PotatoMineFactory implements PlantFactory<PotatoMine> {
    public PotatoMine create(int birthTime) {
        return new PotatoMine(birthTime);
    }    
}
