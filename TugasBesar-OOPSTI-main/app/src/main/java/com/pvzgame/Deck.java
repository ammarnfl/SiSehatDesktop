package com.pvzgame;
import java.util.ArrayList;
import java.util.List;

import com.pvzgame.Plant.*;
import com.pvzgame.PlantFactory.*;

public class Deck<T> {

    // Attributes
    private List<PlantFactory<? extends Plant>> currentDeck;
    private final int maxSlot = 6;

    // Constructor
    public Deck(){
        currentDeck = new ArrayList<>();
    }

    // Getter
    public List<PlantFactory<? extends Plant>> getCurrentDeck(){
        return currentDeck;
    }

    public PlantFactory<? extends Plant> get(int index){
        return currentDeck.get(index);
    }

    public Boolean isDeckFull(){
        return currentDeck.size() == maxSlot;
    }

    public void addPlant(Plant plant){
        try {
            if (!this.isDeckFull()) {
                switch (plant.getPlantName()) {
                    case "Jalapeno":
                        currentDeck.add(new JalapenoFactory());
                        break;
                    case "Kelp":
                        currentDeck.add(new KelpFactory());
                        break;
                    case "Lilypad":
                        currentDeck.add(new LilypadFactory());
                        break;
                    case "Peashooter":
                        currentDeck.add(new PeashooterFactory());
                        break;
                    case "PotatoMine":
                        currentDeck.add(new PotatoMineFactory());
                        break;
                    case "Repeater":
                        currentDeck.add(new RepeaterFactory());
                        break;
                    case "Snowpea":
                        currentDeck.add(new SnowpeaFactory());
                        break;
                    case "Squash":
                        currentDeck.add(new SquashFactory());
                        break;
                    case "Sunflower":
                        currentDeck.add(new SunflowerFactory());
                        break;
                    case "Wallnut":
                        currentDeck.add(new WallnutFactory());
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Deck sudah penuh!");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void removePlant(PlantFactory<? extends Plant> plant) {
        currentDeck.remove(plant);
    }

    public void swapPlant(int index1, int index2) {
        PlantFactory<? extends Plant> temp = currentDeck.get(index1);
        try {
            currentDeck.set(index1, currentDeck.get(index2));
            currentDeck.set(index2, temp);
        } catch (Exception E) {
            System.out.println("Index tidak valid!");
            System.out.println("Masukkan index dengan benar!");
        }
    }

    public void printDeck(){
        System.out.println("\nDaftar Plant di dalam Deck:");
        int count = 1;
        for (PlantFactory<? extends Plant> plant : currentDeck) {
            System.out.println(count + ". " + plant.create(0).getPlantName());
            count++;
        }
        System.out.println("");
    }

    public Plant plantPlant(int index, int birthTime){
        return currentDeck.get(index).create(birthTime);
    }

}
