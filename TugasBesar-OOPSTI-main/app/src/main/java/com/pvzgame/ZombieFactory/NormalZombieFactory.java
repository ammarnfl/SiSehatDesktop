package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.NormalZombie;

public class NormalZombieFactory implements ZombieFactory<NormalZombie> {
    public NormalZombie create(int birthTime) {
        return new NormalZombie(birthTime);
    }
}