package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.SnorkelZombie;

public class SnorkelZombieFactory implements ZombieFactory<SnorkelZombie> {
    public SnorkelZombie create(int birthTime) {
        return new SnorkelZombie(birthTime);
    }
}