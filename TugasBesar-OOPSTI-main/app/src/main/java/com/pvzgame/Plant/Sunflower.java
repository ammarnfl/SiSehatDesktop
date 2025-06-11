package com.pvzgame.Plant;

public class Sunflower extends Plant {

    // Constructor
    public Sunflower(int birthTime) {
        setBirthTime(birthTime);
        setPlantType(9);
        setPlantName("Sunflower");
        setIsWaterType(false);
        setSunCost(50);
        setPlantHealth(100);
        setPlantCooldown(10);
        setPlantAttackRange(0);
        setPlantAttackDamage(0);
        setPlantAttackSpeed(3);
        setPlantAttackPoints(0);
    }
}
