package com.pvzgame.Zombie;

public class NormalZombie extends Zombie {
    
    // Constructor
    public NormalZombie(int birthTime) {
        setBirthTime(birthTime);
        setZombieType(10);
        setZombieName("NormalZombie");
        setZombieHealth(125);
        setZombieAttackDamage(100);
        setZombieAttackSpeed(1);
        setZombieMoveSpeed(2);
        setCurrentMovePoints(20);
        setIsAquatic(false);
        setIsHidden(false);
        setIsSlowed(false);
        setHasTool(false);        
    }
}
