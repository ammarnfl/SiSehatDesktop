package com.pvzgame.Plant;

public class PotatoMine extends Plant {
    // Immediately kills all zombies in the same tile (land)
    // Wait 10 seconds before it ready to kill
    // Immediately sacrifices itself after kills

    // Constructor
    public PotatoMine(int birthTime) {
        setBirthTime(birthTime);
        setPlantType(5);
        setPlantName("PotatoMine");
        setIsWaterType(false);
        setSunCost(25);
        setPlantHealth(100);
        setPlantCooldown(10);
        setPlantAttackRange(0);
        setPlantAttackDamage(5000);
        setPlantAttackSpeed(20);
        setPlantAttackPoints(0);
        // nunggu 20 detik dulu baru bisa kill
    }
}
