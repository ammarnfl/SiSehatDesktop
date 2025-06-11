package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Plant;

public interface PlantFactory<T extends Plant> {
    T create(int birthTime);
}
