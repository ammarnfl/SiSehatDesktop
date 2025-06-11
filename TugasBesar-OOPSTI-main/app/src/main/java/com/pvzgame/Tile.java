package com.pvzgame;

// Import
import com.pvzgame.Zombie.Zombie;
import com.pvzgame.Plant.Plant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tile {

    // Attributes
    private Boolean isProtectedArea;
    private Boolean isSpawnArea;
    private Boolean water;
    private Plant plant;
    private List<Zombie> zombies;

    // Constructor
    public Tile(Boolean isProtectedArea, Boolean isSpawnArea, Boolean water) { 

        // Initial state:
        // Plant = null
        // Zombies = []
        this.isProtectedArea = isProtectedArea;
        this.isSpawnArea = isSpawnArea;
        this.water = water;
        this.plant = null;
        this.zombies = new CopyOnWriteArrayList<>();
    }

    // Getter
    public Boolean getIsProtectedArea() {
        return isProtectedArea;
    }
    
    public Boolean getIsSpawnArea() {
        return isSpawnArea;
    }

    public Boolean getWater() {
        return water;
    }

    public Plant getPlant() {
        return plant;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    // Setter
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public void removeAllZombies() {
        zombies.clear();
    }

    public void removePlant() {
        plant = null;
    }

    public void setProtectedAreaStatus(Boolean bool) {
        isProtectedArea = bool;
    }

    public void setSpawnAreaStatus(Boolean bool) {
        isSpawnArea = bool;
    }

    public void setWaterStatus(Boolean bool) {
        water = bool;
    }
}