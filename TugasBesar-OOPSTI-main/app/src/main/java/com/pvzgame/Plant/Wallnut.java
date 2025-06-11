package com.pvzgame.Plant;

public class Wallnut extends Plant {

    // Constructor
    public Wallnut(int birthTime) {
        setBirthTime(birthTime);
        setPlantType(10);
        setPlantName("Wallnut");
        setIsWaterType(false);
        setSunCost(50);
        setPlantHealth(1000);
        setPlantCooldown(20);
        setPlantAttackRange(0);
        setPlantAttackDamage(0);
        setPlantAttackSpeed(0);
        setPlantAttackPoints(0);
    }
}
