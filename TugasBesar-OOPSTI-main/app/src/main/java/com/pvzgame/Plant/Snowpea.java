package com.pvzgame.Plant;

public class Snowpea extends Plant {

    // Constructor
    public Snowpea(int birthTime) {
        setBirthTime(birthTime);
        setPlantType(7);
        setPlantName("Snowpea");
        setIsWaterType(false);
        setSunCost(175);
        setPlantHealth(100);
        setPlantCooldown(10);
        setPlantAttackRange(-1);
        setPlantAttackDamage(25);
        setPlantAttackSpeed(4);
        setPlantAttackPoints(4);
    }
}
