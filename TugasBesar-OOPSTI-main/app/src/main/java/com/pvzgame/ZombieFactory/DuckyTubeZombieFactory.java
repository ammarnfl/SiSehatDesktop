package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.DuckyTubeZombie;

public class DuckyTubeZombieFactory implements ZombieFactory<DuckyTubeZombie> {
    public DuckyTubeZombie create(int birthTime) {
        return new DuckyTubeZombie(birthTime);
    }
}