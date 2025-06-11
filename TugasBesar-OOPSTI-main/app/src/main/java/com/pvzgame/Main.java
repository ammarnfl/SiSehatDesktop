package com.pvzgame;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

import com.pvzgame.Plant.*; // Import All the Plant class
import com.pvzgame.Zombie.*; // Import All the Zombie class
import com.pvzgame.ZombieFactory.*; // Import All the ZombieFactory class

public class Main {
    
    public static int gameTime;
    public static Boolean gameRunning = false;
    public static Boolean gameOver = false;
    public static Boolean timeHasReset = false; // to check day 2+++

    public static void main(String[] args) throws Exception {

        // Essentials
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Welcome to Michael vs Lalapan! =====");

        int command = 0;
        Menu.printMenu();
        // Game Menu Loop Condition
        while (true) {
            try {
                System.out.println("Masukkan command: ");
                // Input command
                command = Integer.parseInt(scanner.nextLine());

                if (command == 1) {
                    System.out.println("\nPersiapkan Deck!");
                    gameStart(scanner);
                } else if (command == 2) {
                    Menu.printMenu();
                } else if (command == 3) {
                    Menu.printPlantList();
                } else if (command == 4) {
                    Menu.printZombieList();
                } else if (command == 5) {
                    System.out.println("\nThank You for Playing!\nExiting game...");
                    scanner.close();
                    break;
                } else throw new IllegalArgumentException("\n===== Command tidak valid, masukkan command yang benar! =====");
            } catch (IllegalArgumentException e) {
                System.out.println("\n===== Command tidak valid, masukkan command yang benar! =====");
            }
        }
    }

    public static void gameStart(Scanner scanner) {
   
        // Preparation
        Random random = new Random(); // Random number generator
        Sun sun = Sun.getInstance(); // Starting Sun = 50
        Map map = Map.getInstance();
        Deck<Plant> deck = new Deck<>();
        Inventory inventory = new Inventory();
        GameAction action = new GameAction();

        prepareDeck(scanner, deck, inventory);
        System.out.println(" ");
        System.out.println("Game Started!");
        gameTime = 0;
        gameRunning = true;
        gameOver = false;

        // START OF timeThread: Sun Spawner, Zombie Spawner, GameOver Check, Win Check, gameTime increment
        Thread timeThread = new Thread(() -> {
            int sunLastSpawn = 0;

            while (gameRunning) {
                try {

                    // Sun Spawner
                    // Spawn sun randomly every 5 < gameTime < 10
                    if (gameTime <= 100) {
                        if (gameTime - sunLastSpawn >= (random.nextInt(6, 10))) {
                            sun.addSun(25);
                            sunLastSpawn = gameTime;
                        }
                    }

                    // Zombie Spawner (20s - 160s)
                    if (gameTime >= 20 && gameTime <= 160 && (gameTime - 20) % 3 == 0 && !timeHasReset) {
                        for (int i = 0; i < 6; i++) {
                            if (random.nextFloat() < 0.3) {
                                action.zombieSpawner(map.getTile(i, 10));
                            }
                        }
                    }

                    // Wave Zombie Spawner
                    if (gameTime >= 110 && gameTime <= 111 && !timeHasReset) {
                        for (int i = 0; i < 2; i++) {
                            if (random.nextFloat() < 0.3) {
                                map.getTile(i, 10).addZombie(new BucketHeadZombieFactory().create(gameTime));
                            }
                        }
                        
                        for (int i = 2; i < 4; i++) {
                            if (random.nextFloat() < 0.3) {
                                map.getTile(i, 10).addZombie(new DuckyTubeZombieFactory().create(gameTime));
                            
                            }
                        }

                        for (int i = 4; i < 6; i++) {
                            if (random.nextFloat() < 0.3) {
                                map.getTile(i, 10).addZombie(new BucketHeadZombieFactory().create(gameTime));
                            }
                        }   
                    }

                    // GameOver Check
                    for (int j = 0; j < 6; j++) {
                        if (!map.getTile(j, 0).getZombies().isEmpty()) {
                            gameRunning = false;
                            gameOver = true;
                            break;
                        }
                    }
                    if (!gameOver) {
                    // Win Check
                        if (!timeHasReset && gameTime > 160) { // 160-200s
                            Boolean winCheck = true;
                            // check if there is no more zombies in map
                            for(int i = 0; i < 6; i++) {
                                for (int j = 1; j < 11; j++) {
                                    if (!map.getTile(i, j).getZombies().isEmpty()) {
                                        winCheck = false;
                                    }
                                }
                            }
                            if(winCheck) gameRunning = false;

                        } else if (timeHasReset) { // day 2 +++
                            Boolean winCheck = true;
                            // check if there is no more zombies in map
                            for(int i = 0; i < 6; i++) {
                                for (int j = 1; j < 11; j++) {
                                    if (!map.getTile(i, j).getZombies().isEmpty()) {
                                        winCheck = false;
                                    }
                                }
                            }
                            if(winCheck) gameRunning = false;
                        }
                    }

                    for (int i = 0; i < 10; i++) { // reset plant cooldown
                        Plant.decCooldown(i);
                    }
                    
                    // gameTime increment
                    if (!gameOver) {
                        Thread.sleep(1000); // 1s sleep
                        gameTime++;
                    }

                    if (gameTime == 200) {
                        gameTime = 0; // Reset to Day
                        sunLastSpawn  = 0; // Reset sun spawn time
                        timeHasReset = true;
                        sunLastSpawn  = 0;
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameOver) {
                System.out.println("\n===== Michael Tyson emang jago! Lu cupsss ahhh :V =====\n===== Game Over! =====");
                gameReset();
                Menu.printMenu();
            } else {
                System.out.println("\n===== GG Jago bat dah! =====\n===== Game Over! =====\n");
                gameReset();
                Menu.printMenu();
            }
        });

      
        // END OF timeThread: Sun Spawner, Zombie Spawner, GameOver Check, Win Check, gameTime increment

        Thread gameThread1 = new Thread(() -> { // row = 0
            while(gameRunning) {
                try {
                    for (int i = 0; i < 11; i++) { // all col 
                        if (!map.getTile(0, i).getZombies().isEmpty()) {
                            for (Zombie zombie : map.getTile(0, i).getZombies()) {
                                action.zombieAction(zombie, map, 0, i);
                            }
                        }
                        if (map.getTile(0, i).getPlant() != null) {
                            action.plantAction(map, 0, i, map.getTile(0, i).getPlant(), sun);
                            
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread gameThread2 = new Thread(() -> { // row = 1
            while(gameRunning) {
                try {
                    for (int i = 0; i < 11; i++) { // all col 
                        if (!map.getTile(1, i).getZombies().isEmpty()) {
                            for (Zombie zombie : map.getTile(1, i).getZombies()) {
                                action.zombieAction(zombie, map, 1, i);
                            }
                        }
                        if (map.getTile(1, i).getPlant() != null) {
                            action.plantAction(map, 1, i, map.getTile(1, i).getPlant(), sun);
                            
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread gameThread3 = new Thread(() -> { // row = 2
            while(gameRunning) {
                try {
                    for (int i = 0; i < 11; i++) { // all col 
                        if (!map.getTile(2, i).getZombies().isEmpty()) {
                            for (Zombie zombie : map.getTile(2, i).getZombies()) {
                                action.zombieAction(zombie, map, 2, i);
                            }
                        }
                        if (map.getTile(2, i).getPlant() != null) {
                            action.plantAction(map, 2, i, map.getTile(2, i).getPlant(), sun);
                            
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread gameThread4 = new Thread(() -> { // row = 3
            while(gameRunning) {
                try {
                    for (int i = 0; i < 11; i++) { // all col 
                        if (!map.getTile(3, i).getZombies().isEmpty()) {
                            for (Zombie zombie : map.getTile(3, i).getZombies()) {
                                action.zombieAction(zombie, map, 3, i);
                            }
                        }
                        if (map.getTile(3, i).getPlant() != null) {
                            action.plantAction(map, 3, i, map.getTile(3, i).getPlant(), sun);
                            
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread gameThread5 = new Thread(() -> { // row = 4
            while(gameRunning) {
                try {
                    for (int i = 0; i < 11; i++) { // all col 
                        if (!map.getTile(4, i).getZombies().isEmpty()) {
                            for (Zombie zombie : map.getTile(4, i).getZombies()) {
                                action.zombieAction(zombie, map, 4, i);
                            }
                        }
                        if (map.getTile(4, i).getPlant() != null) {
                            action.plantAction(map, 4, i, map.getTile(4, i).getPlant(), sun);
                            
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread gameThread6 = new Thread(() -> { // row = 5
            while(gameRunning) {
                try {
                    for (int i = 0; i < 11; i++) { // all col 
                        if (!map.getTile(5, i).getZombies().isEmpty()) {
                            for (Zombie zombie : map.getTile(5, i).getZombies()) {
                                action.zombieAction(zombie, map, 5, i);
                            }
                        }
                        if (map.getTile(5, i).getPlant() != null) {
                            action.plantAction(map, 5, i, map.getTile(5, i).getPlant(), sun);
                            
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timeThread.start();
        gameThread1.start();
        gameThread2.start();
        gameThread3.start();
        gameThread4.start();
        gameThread5.start();
        gameThread6.start();

        // START OF mainThread : userInput
        while(gameRunning) {
            Boolean alreadyPrintMap = false;

            try {
                if (!timeHasReset) System.out.println("\n*** Game Time: " + gameTime + "s ***");
                else System.out.println("\n*** Game Time: " + (gameTime+200) + " ***");

                // Sun Print
                System.out.println("Sun: " + (sun.getSunPoints()));
                System.out.println("\nCommand: ");
                System.out.println("(1)       : Print Map");
                System.out.println("(2 p x y) : Tanam Plant ke-p dari Deck di baris x dan kolom y");
                System.out.println("(3 x y)   : Cabut Plant di baris x dan kolom y\n");
                if (!gameRunning) break;
                int userInput = 0;
                userInput = scanner.nextInt();
                if (!gameRunning) break;
                if (userInput == 1) {
                    if (!gameRunning) break;
                    alreadyPrintMap = true;
                    map.printMap();
                } else if (userInput == 2){
                    if (!gameRunning) break;
                    try {
                        int p = scanner.nextInt();
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();

                        if (p < 1 || p > 6) throw new Exception("\n===== Index Plant tidak valid! =====");
                        action.plant(x - 1, y, deck.plantPlant(p - 1, gameTime), map, sun);
                       
                    } catch (InputMismatchException e) {
                        System.out.println("\n===== Command tidak valid ======");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                } else if (userInput == 3) {
                    if (!gameRunning) break;
                    try {
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        action.dig(x - 1, y, map);

                    } catch (InputMismatchException e) {
                        System.out.println("\n===== Command tidak valid ======");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("\n===== Command tidak valid ======");
                    }
                } else throw new Exception("\n====== Command tidak valid ======"); 
            } catch (InputMismatchException e) {
                System.out.println("\n===== Command tidak valid ======");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n===== Command tidak valid ======");
            } finally {
                if (gameRunning) {
                    if (!alreadyPrintMap) map.printMap();
                    deck.printDeck();
                }
            }
        }

    }
    // END OF mainThread : userInput

    public static void prepareDeck(Scanner scanner, Deck<Plant> deck, Inventory inventory) {
        while (!deck.isDeckFull()) { // Deck is not full
            inventory.printInventory();
            deck.printDeck();
            int command = 0;

            System.out.println("Command: ");
            System.out.println("(1 x)   : Pilih tanaman ke-x dari inventory untuk dimasukkan ke deck");
            System.out.println("(2 x)   : Hapus tanaman ke-x dari deck");
            System.out.println("(3 x y) : Tukar tanaman ke-x dengan tanaman ke-y di deck");
            System.out.println("");
          
            try {

                // command available = [1, 2, 3]
                command = scanner.nextInt(); 
                if (command == 1) { // x
                    try {
                        int plantIndex = scanner.nextInt();
                        inventory.pickPlant(inventory.get(plantIndex - 1), deck);
                    } catch (InputMismatchException e) {
                        System.out.println("\n===== Index tidak valid! =====");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("\n===== Index tidak valid! =====");
                      
                    }
                } else if (command == 2) { // x
                    try {
                        int plantIndex = scanner.nextInt();
                        inventory.removePlant(plantIndex - 1, deck);
                    } catch (InputMismatchException e) {
                        System.out.println("\n===== Index tidak valid! =====");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("\n===== Index tidak valid! =====");
                    }
                } else if (command == 3) { // x y
                    try {
                        int plantIndex1 = scanner.nextInt();
                        int plantIndex2 = scanner.nextInt();
                        inventory.swapPlant(plantIndex1 - 1, plantIndex2 - 1, deck);
                    } catch (InputMismatchException e) {
                        System.out.println("\n===== Index tidak valid! =====");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("\n===== Index tidak valid! =====");
                    }
                } else {
                    System.out.println("\n=====Command tidak valid, masukkan command yang benar! =====");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("\n===== Command tidak valid, masukkan command yang benar! =====");
                scanner.nextLine();
            }
        }
        deck.printDeck();
    }

    public static void gameReset() {
        gameTime = 0;
        gameRunning = false;
        gameOver = false;
        timeHasReset = false;
        Zombie.resetZombieCount();
        Plant.resetCooldown();
        Sun.getInstance().setSunPoints(50);
        Map.resetMap();
    }
}
    