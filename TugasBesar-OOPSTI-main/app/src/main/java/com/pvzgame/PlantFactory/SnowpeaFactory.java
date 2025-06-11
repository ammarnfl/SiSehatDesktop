package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Snowpea;

public class SnowpeaFactory implements PlantFactory<Snowpea> {
    public Snowpea create(int birthTime) {
        return new Snowpea(birthTime);
    }
}
