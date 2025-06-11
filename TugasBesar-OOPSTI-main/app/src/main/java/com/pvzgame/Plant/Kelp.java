package com.pvzgame.Plant;
public class Kelp extends Plant {
    // Immediately kills the zombie in the same tile (water)
    // Wait first until it kills, then sacrifices itself

    // Constructor
    public Kelp(int birthTime) {
        setBirthTime(birthTime);
        setPlantType(2);
        setPlantName("Kelp");
        setIsWaterType(true);
        setSunCost(25);
        setPlantHealth(100);
        setPlantCooldown(10);
        setPlantAttackRange(0);
        setPlantAttackDamage(5000);
        setPlantAttackSpeed(0);
        setPlantAttackPoints(0);
    }
}
