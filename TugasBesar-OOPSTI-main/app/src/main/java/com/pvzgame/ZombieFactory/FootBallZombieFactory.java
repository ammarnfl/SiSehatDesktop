package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.FootBallZombie;

public class FootBallZombieFactory implements ZombieFactory<FootBallZombie> {
    public FootBallZombie create(int birthTime) {
        return new FootBallZombie(birthTime);
    }
}