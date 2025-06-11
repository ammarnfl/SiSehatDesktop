package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.DolphinRiderZombie;

public class DolphinRiderZombieFactory implements ZombieFactory<DolphinRiderZombie> {
    public DolphinRiderZombie create(int birthTime) {
        return new DolphinRiderZombie(birthTime);
    }
}
