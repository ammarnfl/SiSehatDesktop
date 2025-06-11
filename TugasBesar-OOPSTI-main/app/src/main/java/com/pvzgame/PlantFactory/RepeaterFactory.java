package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Repeater;

public class RepeaterFactory implements PlantFactory<Repeater> {
    public Repeater create(int birthTime) {
        return new Repeater(birthTime);
    }    
}
