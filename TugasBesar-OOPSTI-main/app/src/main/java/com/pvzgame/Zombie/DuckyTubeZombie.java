package com.pvzgame.Zombie;

public class DuckyTubeZombie extends Zombie {
        
    // Constructor
    public DuckyTubeZombie(int birthTime) {
        setBirthTime(birthTime);
        setZombieType(4);
        setZombieName("DuckyTubeZombie");
        setZombieHealth(100);
        setZombieAttackDamage(100);
        setZombieAttackSpeed(1);
        setZombieMoveSpeed(2);
        setCurrentMovePoints(20);
        setIsAquatic(true);
        setIsHidden(false);
        setIsSlowed(false);
        setHasTool(false);   
    }
}