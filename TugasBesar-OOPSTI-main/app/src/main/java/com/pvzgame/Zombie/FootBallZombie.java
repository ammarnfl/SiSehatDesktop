package com.pvzgame.Zombie;

public class FootBallZombie extends Zombie {
    
    // Constructor
    public FootBallZombie(int birthTime) {
        setBirthTime(birthTime);
        setZombieType(5);
        setZombieName("FootballZombie");
        setZombieHealth(500);
        setZombieAttackDamage(100);
        setZombieAttackSpeed(2);
        setZombieMoveSpeed(4);
        setCurrentMovePoints(20);
        setIsAquatic(false);
        setIsHidden(false);
        setIsSlowed(false);
        setHasTool(true);   
    }
}