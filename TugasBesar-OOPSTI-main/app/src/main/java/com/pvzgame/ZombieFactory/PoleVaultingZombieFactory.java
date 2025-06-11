package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.PoleVaultingZombie;

public class PoleVaultingZombieFactory implements ZombieFactory<PoleVaultingZombie> {
    public PoleVaultingZombie create(int birthTime) {
        return new PoleVaultingZombie(birthTime);
    }
}