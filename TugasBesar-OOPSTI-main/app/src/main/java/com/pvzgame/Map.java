package com.pvzgame;
import com.pvzgame.Zombie.*;

public class Map {
    // Singleton

    // Attributes
    private static Map instance = null;
    private Tile[][] tiles;
    private final int rows = 6;
    private final int cols = 11;
    
    // Private Constructor
    private Map() {
        tiles = new Tile[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new Tile(false, false, false);
            }
        }

        // Protected Area
        for (int j = 0; j < rows; j++) {
            tiles[j][0].setProtectedAreaStatus(true);

        }

        // Spawn Area
        for (int k = 0; k < rows; k++) {
            tiles[k][10].setSpawnAreaStatus(true);
        }

        // Water Area
        for (int l = 2; l < 4; l++) {
            for (int m = 1; m < cols; m++) {
                tiles[l][m].setWaterStatus(true);
            }
        }
    }

    // Singleton Instance and Thread Safety
    public static Map getInstance() {
        if (instance == null) instance = new Map();
        return instance;
    }

    // Getter
    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void printMap() {
        System.out.println("\nMap\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                synchronized(tiles[i][j]) {
                    System.out.print("["); // Opening bracket

                    // Protected Area
                    if (tiles[i][j].getIsProtectedArea()) {
                        System.out.print("*P|");
                    }

                    // Spawn Area
                    if (tiles[i][j].getIsSpawnArea()) {
                        System.out.print("*S|");
                    }

                    if (!tiles[i][j].getIsProtectedArea() && !tiles[i][j].getIsSpawnArea()) {

                        // Water or Land
                        if (tiles[i][j].getWater()) System.out.print("W| "); // Water
                        else System.out.print("L| "); // Land
                        
                        // Plant
                        if (tiles[i][j].getPlant() != null) {
                            System.out.print("P." + tiles[i][j].getPlant().getPlantType() + "| ");
                        }
                    }

                    // Zombies
                    if (!tiles[i][j].getZombies().isEmpty()) {
                        for (Zombie zombiePrint : tiles[i][j].getZombies()) {
                            System.out.print("Z." + zombiePrint.getZombieType() + " ");
                        }
                    }
                }
                System.out.print("]"); // Closing bracket
            }
            System.out.println(); // newLine
        }
    }

    public static void resetMap() {
        instance = null;
    }
}