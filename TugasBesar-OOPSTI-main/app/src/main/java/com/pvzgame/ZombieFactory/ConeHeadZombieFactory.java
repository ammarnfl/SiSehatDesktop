package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.ConeHeadZombie;

public class ConeHeadZombieFactory implements ZombieFactory<ConeHeadZombie> {
    public ConeHeadZombie create(int birthTime) {
        return new ConeHeadZombie(birthTime);
    }
}