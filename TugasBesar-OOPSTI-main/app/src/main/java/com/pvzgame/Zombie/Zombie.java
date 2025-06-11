package com.pvzgame.Zombie;
import com.pvzgame.*;

public abstract class Zombie {

    // Attributes 
    private String zombieName;
    private int zombieType;
    private int birthTime;

    private Boolean isAquatic;

    private int zombieHealth;
    private int zombieAttackDamage;
    private int zombieAttackSpeed;
    private int zombieMoveSpeed; // berapa point yang ditambahkan setiap detik
    private int currentMovePoints; // berapa point yang sudah ditambahkan
    private final int movePoint = 20; // berapa point yang dibutuhkan untuk bergerak
    private int slowPoints = 0; // berapa point yang sudah ditambahkan

    private Boolean isHidden;
    private Boolean isSlowed;
    private Boolean hasTool;

    private static int zombieCount = 0;
    
    // Constructor
    // Will be implemented by subclasses

    // Getters
    public String getZombieName() {
        return zombieName;
    }

    public int getZombieType() {
        return zombieType;
    }

    public int getBirthTime() {
        return birthTime;
    }

    public Boolean getIsAquatic() {
        return isAquatic;
    }

    public int getZombieHealth() {
        return zombieHealth;
    }

    public int getZombieAttackDamage() {
        return zombieAttackDamage;
    }

    public int getZombieAttackSpeed() {
        return zombieAttackSpeed;
    }

    public int getZombieMoveSpeed() {
        return zombieMoveSpeed;
    }

    public int getCurrentMovePoints() {
        return currentMovePoints;
    }

    public int getMovePoint() {
        return movePoint;
    }
    
    public boolean getIsHidden() {
        return isHidden;
    }

    public boolean getIsSlowed() {
        return isSlowed;
    }

    public boolean getHasTool() {
        return hasTool;
    }

    public int getSlowPoints() {
        return slowPoints;
    }

    public static int getZombieCount() {
        return zombieCount;
    }

    // Setters
    public void setZombieName(String zombieName) {
        this.zombieName = zombieName;
    }

    public void setZombieType(int zombieType) {
        this.zombieType = zombieType;
    }

    public void setBirthTime(int birthTime) {
        this.birthTime = birthTime;
    }

    public void setIsAquatic(Boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public void setZombieHealth(int zombieHealth) {
        this.zombieHealth = zombieHealth;
    }

    public void setZombieAttackDamage(int zombieAttackDamage) {
        this.zombieAttackDamage = zombieAttackDamage;
    }

    public void setZombieAttackSpeed(int zombieAttackSpeed) {
        this.zombieAttackSpeed = zombieAttackSpeed;
    }

    public void setZombieMoveSpeed(int zombieMoveSpeed) {
        this.zombieMoveSpeed = zombieMoveSpeed;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public void setIsSlowed(boolean isSlowed) {
        this.isSlowed = isSlowed;
    }

    public void setHasTool(boolean hasTool) {
        this.hasTool = hasTool;
    }

    public void addSlowPoints() {
        this.slowPoints++;
    }

    public void resetSlowPoints() {
        this.slowPoints = 0;
    }

    public static void incZombieCount() {
        zombieCount++;
    }

    public static void decZombieCount() {
        zombieCount--;
    }

    public static void resetZombieCount() {
        zombieCount = 0;
    }

    // methods
    public int zombieCheck(Map map, int row, int col){

        // slow mechanism
        if (getSlowPoints() >= 3){ // pengecekan apakah sudah waktunya unslow
            unslowZombie();
        } else if (getIsSlowed()){ // counter slow zombie
            addSlowPoints();
        }

        // tool check
        if (getZombieHealth() <= 125){ // pengecekan tool dan mengubah status tool
            this.setHasTool(false);
        }
        
        // move mechanism
        if (map.getTile(row, col).getPlant() != null){ // mengecek ada tanaman atau tidak
            return 1; 

        } else { // Tidak ada tanaman, siap jalan
            addCurrentMovePoints(getZombieMoveSpeed()); // movemodifier defaultnya 2 jadinya movetime setiap detik nambah 2
            if (getCurrentMovePoints() >= getMovePoint()){ // apakah sudah waktunya bergerak
                resetCurrentMovePoints();
                return 2;
            } else {
                return 0;
            }
        }
    }

    public void zombieAttacked(int damage) {
        zombieHealth -= damage;
    }   

    public void slowZombie() {
        isSlowed = true;
        this.zombieMoveSpeed /= 2;
        this.zombieAttackDamage /= 2;
        this.slowPoints = 0;
    }

    public void unslowZombie() {
        isSlowed = false;
        this.zombieMoveSpeed *= 2;
        this.zombieAttackDamage *= 2;
    }

    public void setCurrentMovePoints(int points) {
        this.currentMovePoints = points;
    }
    
    public void addCurrentMovePoints(int points) {
       this.currentMovePoints += points;
    }

    public void resetCurrentMovePoints() {
        this.currentMovePoints = 0;
    }
}