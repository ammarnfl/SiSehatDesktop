package com.pvzgame;

import java.util.Random;

import com.pvzgame.Zombie.*;
import com.pvzgame.ZombieFactory.*;
import com.pvzgame.Plant.*;
import com.pvzgame.PlantFactory.*;

public class GameAction implements ZombieEnum {
    
    // Attributes
    public ZombieEnum.landZombie[] landZombie = ZombieEnum.landZombie.values();
    public ZombieEnum.aquaticZombie[] aquaticZombie = ZombieEnum.aquaticZombie.values();

    // ============= ZOMBIE ACTION ==============
    // Zombie Spawner
    public void zombieSpawner(Tile tile) {

        // Max of total Zombie exist at a time is 10
        if (Zombie.getZombieCount() >= 10) {
            return;
        }

        // Randomly choose any type of zombie
        Random random = new Random();     
        if (tile.getWater()) { // Water
            ZombieEnum.aquaticZombie randomAquaticZombie = aquaticZombie[random.nextInt(aquaticZombie.length)];
            switch (randomAquaticZombie) {
                case SnorkelZombie:
                    tile.addZombie(new SnorkelZombieFactory().create(Main.gameTime));
                    break;
                case DuckyTubeZombie:
                    tile.addZombie(new DuckyTubeZombieFactory().create(Main.gameTime));
                    break;
                case DolphinRiderZombie:
                    tile.addZombie(new DolphinRiderZombieFactory().create(Main.gameTime));
                    break;
                default:
                    break;
            }
        } else { // Not Water
            ZombieEnum.landZombie randomLandZombie = landZombie[random.nextInt(landZombie.length)];
            switch (randomLandZombie) {
                case BucketHeadZombie:
                    tile.addZombie(new BucketHeadZombieFactory().create(Main.gameTime));
                    break;
                case ConeHeadZombie:
                    tile.addZombie(new ConeHeadZombieFactory().create(Main.gameTime));
                    break;
                case FootBallZombie:
                    tile.addZombie(new FootBallZombieFactory().create(Main.gameTime));
                    break;
                case NewsPaperZombie:
                    tile.addZombie(new NewsPaperZombieFactory().create(Main.gameTime));
                    break;
                case PoleVaultingZombie:
                    tile.addZombie(new PoleVaultingZombieFactory().create(Main.gameTime));
                    break;
                case ScreenDoorZombie:
                    tile.addZombie(new ScreenDoorZombieFactory().create(Main.gameTime));
                    break;
                case NormalZombie:
                    tile.addZombie(new NormalZombieFactory().create(Main.gameTime));
                    break;
                default:
                    break;
            }
        }
        Zombie.incZombieCount();
    }

    public void zombieMove(Zombie zombie, Map map, int row, int col){ // Immediately normally move
        if (col != 0) {
            map.getTile(row, col - 1).addZombie(zombie); // target tile
            map.getTile(row, col).removeZombie(zombie);  // current tile 
        }
    }

    public void zombieAction(Zombie zombie, Map map, int row, int col){
        switch (zombie.zombieCheck(map, row, col)){
            case 1:
                // zombieAttackdamage * zombieAttackSpeed
                map.getTile(row, col).getPlant().plantAttacked(zombie.getZombieAttackDamage()*zombie.getZombieAttackSpeed());
                if (map.getTile(row, col).getPlant().getPlantHealth() <= 0){ // cek apakah plant sudah mati terbunuh
                    map.getTile(row, col).removePlant();
                }
                break;
            case 2:
                zombieMove(zombie, map, row, col);
                break;
            case 3:
                zombieMove(zombie, map, row, col);
                map.getTile(row, col).removePlant();
                break;
            default: 
                break;
        }
    }

    // ============= PLANT ACTION ==============
    public void plantAction(Map map, int row, int col, Plant plant, Sun sun){

        // jalapeno: remove entire zombie in a row
        if (plant.getPlantType() == 1) {
            for (int i = 1; i < 10; i++){
                if (!map.getTile(row, i).getZombies().isEmpty()){
                    for (int j = 0; j < map.getTile(row, i).getZombies().size(); j++){
                        Zombie.decZombieCount();
                    }
                    map.getTile(row, i).removeAllZombies();
                }
            }
            if (map.getTile(row, col).getWater()) {
                map.getTile(row, col).removePlant(); // remove jalapeno immediately
                map.getTile(row, col).setPlant(new LilypadFactory().create(0));
            
            } else map.getTile(row, col).removePlant(); // remove jalapeno immediately
        }

        // kelp: remove front zombie only at the same tile
        else if (plant.getPlantType() == 2) {
            if (!map.getTile(row, col).getZombies().isEmpty()){
                map.getTile(row, col).removeZombie(map.getTile(row, col).getZombies().get(0)); 
                Zombie.decZombieCount();
                map.getTile(row, col).removePlant();
            }
        }

        // peashooter/repeater: hit all zombie in front
        else if (plant.getPlantType() == 4 || plant.getPlantType() == 6){
            if (plant.timeToAttack()){
                for (int i = col; i < 10; i++){
                    if(!map.getTile(row, i).getZombies().isEmpty()){
                        for (Zombie zombie : map.getTile(row, i).getZombies()){
                            zombie.zombieAttacked(plant.getPlantAttackDamage());
                            if (zombie.getZombieHealth() <= 0){
                                map.getTile(row, i).removeZombie(zombie);
                                Zombie.decZombieCount();
                            }
                        }
                        break; // front tile only
                    }
                }
            }
        }

        // potato mine: remove all zombies at the same tile
        else if (plant.getPlantType() == 5){
            if (plant.timeToAttack()){
                if (!map.getTile(row, col).getZombies().isEmpty()){
                    for (int j = 0; j < map.getTile(row, col).getZombies().size(); j++){
                        Zombie.decZombieCount();
                    }
                    map.getTile(row, col).removeAllZombies();
                    
                    if(map.getTile(row, col).getWater()) {
                        map.getTile(row, col).removePlant();
                        map.getTile(row, col).setPlant(new LilypadFactory().create(0));
                    } else map.getTile(row, col).removePlant();
                }
            }
        }

        // snowpea
        else if (plant.getPlantType() == 7){
            if (plant.timeToAttack()){
                for (int i = col; i < 10; i++){
                    if(!map.getTile(row, i).getZombies().isEmpty()){
                        for (Zombie zombie : map.getTile(row, i).getZombies()) {
                            zombie.zombieAttacked(plant.getPlantAttackDamage());
                            if (zombie.getZombieHealth() <= 0){
                                map.getTile(row, i).removeZombie(zombie);
                                Zombie.decZombieCount();
                            }
                        }
                        break; // front tile only
                    }
                }
            }
        }

        // squash
        else if (plant.getPlantType() == 8){
            if (!map.getTile(row, col-1).getZombies().isEmpty()){
                for (int i = 0; i < map.getTile(row, col-1).getZombies().size(); i++){
                    Zombie.decZombieCount();
                }
                map.getTile(row, col-1).removeAllZombies();
                if(map.getTile(row, col).getWater()) {
                    map.getTile(row, col).removePlant();
                    map.getTile(row, col).setPlant(new LilypadFactory().create(0));
                } else map.getTile(row, col).removePlant();
            }
            else if (!map.getTile(row, col).getZombies().isEmpty()){
                for (int i = 0; i < map.getTile(row, col).getZombies().size(); i++){
                    Zombie.decZombieCount();
                }
                map.getTile(row, col).removeAllZombies();
                if(map.getTile(row, col).getWater()) {
                    map.getTile(row, col).removePlant();
                    map.getTile(row, col).setPlant(new LilypadFactory().create(0));
                } else map.getTile(row, col).removePlant();
            }
            else if (!map.getTile(row, col+1).getZombies().isEmpty()){
                for (int i = 0; i < map.getTile(row, col+1).getZombies().size(); i++){
                    Zombie.decZombieCount();
                }
                map.getTile(row, col+1).removeAllZombies();
                if(map.getTile(row, col).getWater()) {
                    map.getTile(row, col).removePlant();
                    map.getTile(row, col).setPlant(new LilypadFactory().create(0));
                } else map.getTile(row, col).removePlant();
            } else {
                if(map.getTile(row, col).getWater()) {
                    map.getTile(row, col).removePlant();
                    map.getTile(row, col).setPlant(new LilypadFactory().create(0));
                } else map.getTile(row, col).removePlant();
            }
        }

        // sunflower
        else if (plant.getPlantType() == 9) {
            // add 25 sun every 3 seconds
            if (plant.timeToAttack() && Main.gameTime <= 100) {
                sun.addSun(25);
            }
        }
    }

    // Plant Spawner
    public void plant(int row, int col, Plant plant, Map map, Sun sun) {
        try {   
         
            // Error Handling
            if (Plant.getCooldown(plant.getPlantType()-1) > 0) { // Cooldown
                System.out.println("Tunggu cooldown: " + Plant.getCooldown(plant.getPlantType()-1) + " detik lagi!");
                throw new Exception("\n===== Tanaman belum siap untuk ditanam! =====");
            }

            if (plant.getSunCost() > sun.getSunPoints()) { // Sun Point not enough
                throw new Exception("\n===== Sun Point tidak cukup! =====");
            }

            if (row < 0 || col < 1 || row > 6 || col > 9) { // Index out of bound
                throw new IllegalArgumentException("\n===== Index row atau col tidak valid! =====");
            }

            // LAND PLANT
            if (!map.getTile(row, col).getWater()) {
                if (plant.getIsWaterType()) { 
                    throw new Exception("\n===== Tidak dapat menanam tanaman air di daratan! =====");
                } else if (map.getTile(row, col).getPlant() != null) {
                    throw new Exception("\n===== Sudah ada tanaman di tile tersebut! =====");
                } else if (map.getTile(row, col).getPlant() == null) {
                    map.getTile(row, col).setPlant(plant);
                    System.out.println("\n ===== Menanam " + plant.getPlantName() + " di daratan! =====");
                }
            }

            // WATER PLANT
            if (map.getTile(row, col).getWater()) {
                if (map.getTile(row, col).getPlant() != null) { // ada sesuatu di tile tersebut

                    // Ada lilypad
                    if (map.getTile(row, col).getPlant().getPlantName().equals("Lilypad")) {
                        if (plant.getIsWaterType()) {
                            throw new Exception("\n===== Tidak dapat menanam tanaman air di atas Lilypad! =====");
                        } else {
                            plant.setPlantHealth(plant.getPlantHealth() + 100);
                            map.getTile(row, col).setPlant(plant);
                            System.out.println("\n===== Menanam " + plant.getPlantName() + " di atas Lilypad! =====");
                        }
                    
                    // Ada tanaman air lain
                    } else if (!map.getTile(row, col).getPlant().getPlantName().equals("Lilypad")) {
                        throw new Exception("\n===== Sudah ada tanaman di tile tersebut! =====");
                    }

                // Tidak ada apa-apa di tile tersebut
                } else {
                    if (plant.getIsWaterType()) {
                        map.getTile(row, col).setPlant(plant);
                        System.out.println("\n===== Menanam " + plant.getPlantName() + " di air! =====");
                    } else {
                        throw new Exception("\n===== Tidak dapat menanam tanaman daratan di air, butuh Lilypad! =====");
                    }
                }
            }

            // Set cooldown
            Plant.setCooldown(plant.getPlantType()-1, plant.getPlantCooldown()); 

            // Subtract Sun Point and status
            sun.subtractSun(plant.getSunCost());
            System.out.println("\nPlant: " + plant.getPlantName());
            System.out.println("Sun berkurang: " + plant.getSunCost());
            System.out.println("PlantTime: " + plant.getBirthTime());
            System.out.println("Cooldown: " + Plant.getCooldown(plant.getPlantType()-1));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    // Dig
    public void dig(int row, int col, Map map) {
        try {

            // Error Handling
            if (row < 0 || col < 1 || row > 5 || col > 9) {
                throw new IllegalArgumentException("\n===== Index row atau col tidak valid! =====\n");
            }

            if (map.getTile(row, col).getPlant() == null) {
                throw new NullPointerException("\n===== Tidak ada tanaman untuk dicabut! =====\n");
            }

            // if it's land
            if (!map.getTile(row, col).getWater()) {
                map.getTile(row, col).removePlant();
            } else { // if it's water

                // Check if the tile has lilypad

                // if it's Lilypad
                if (map.getTile(row, col).getPlant().getIsWaterType()) {
                    map.getTile(row, col).removePlant();
                } else { // if it's land plant
                    map.getTile(row, col).setPlant(new LilypadFactory().create(0));
                } 
            }
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
} 
