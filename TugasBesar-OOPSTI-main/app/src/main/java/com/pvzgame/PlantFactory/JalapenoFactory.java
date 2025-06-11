package com.pvzgame.PlantFactory;

import com.pvzgame.Plant.Jalapeno;

public class JalapenoFactory implements PlantFactory<Jalapeno> {
    public Jalapeno create(int birthTime) {
        return new Jalapeno(birthTime);
    }    
}
