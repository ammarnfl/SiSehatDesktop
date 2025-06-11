package com.pvzgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pvzgame.Plant.*;

public class Inventory {
    private Map<Plant, Boolean> plantList;

    public Inventory() {
        plantList = new LinkedHashMap<>();

        plantList.put(new Jalapeno(0), true);
        plantList.put(new Kelp(0), true);
        plantList.put(new Lilypad(0), true);
        plantList.put(new Peashooter(0), true);
        plantList.put(new PotatoMine(0), true);
        plantList.put(new Repeater(0), true);
        plantList.put(new Snowpea(0), true);
        plantList.put(new Squash(0), true);
        plantList.put(new Sunflower(0), true);
        plantList.put(new Wallnut(0), true);
    }

    public void pickPlant(Plant plant, Deck<?> deck) throws Exception {
        for (Map.Entry<Plant, Boolean> entry : plantList.entrySet()) {
            if (entry.getKey().getPlantName().equals(plant.getPlantName())) {
                if (entry.getValue()) {
                    deck.addPlant(plant); // deck penuh dicover di deck class
                    entry.setValue(false);
                    return;
                } else {
                    System.out.println("\n===== Plant tersebut sudah ditambahkan ke dalam deck =====");
                }
            }
        }
    }
 
    public void removePlant(int index, Deck<?> deck) throws Exception {
        if (deck.get(index) == null) throw new Exception("\n===== Tidak dapat menghapus tanaman yang kosong =====");
        else {
            Plant plant = deck.get(index).create(0);
            deck.removePlant(deck.get(index));
            for (Map.Entry<Plant, Boolean> entry : plantList.entrySet()) {
                if (entry.getKey().getPlantName().equals(plant.getPlantName())) {
                    entry.setValue(true);
                    return;
                }
            }
        }
    }

    public void swapPlant(int index1, int index2, Deck<?> deck) throws Exception {
        if (index1 > 6 || index2 > 6) throw new Exception("\n===== Index tidak valid =====");
        else if (index1 == index2) throw new Exception("\n===== Tidak dapat menukar tanaman dengan dirinya sendiri =====");
        else if (deck.get(index1) == null || deck.get(index2) == null) throw new Exception ("\n===== Tidak dapat menukar tanaman yang kosong =====");
        else {
            deck.swapPlant(index1, index2);
        }
    }

    public Plant get(int index) {
        List<Plant> keys = new ArrayList<>(plantList.keySet());
        return keys.get(index);
    }

    public void printInventory() {
        System.out.println("\nDaftar Plant di Inventory: ");
        int count = 1;
        for (Plant plant : plantList.keySet()) {
            System.out.println(count + ". " + plant.getPlantName());
            count++;
        }
        System.out.println("");
    }
}
