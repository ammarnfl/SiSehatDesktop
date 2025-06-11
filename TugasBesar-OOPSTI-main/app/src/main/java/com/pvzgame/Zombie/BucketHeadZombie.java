package com.pvzgame.Zombie;

public class BucketHeadZombie extends Zombie {
    
    // Constructor
    public BucketHeadZombie(int birthTime) {
        setBirthTime(birthTime);
        setZombieType(1);
        setZombieName("BucketHeadZombie");
        setZombieHealth(300);
        setZombieAttackDamage(100);
        setZombieAttackSpeed(1);
        setZombieMoveSpeed(2);
        setCurrentMovePoints(20);
        setIsAquatic(false);
        setIsHidden(false);
        setIsSlowed(false);
        setHasTool(true);   
    }
}
