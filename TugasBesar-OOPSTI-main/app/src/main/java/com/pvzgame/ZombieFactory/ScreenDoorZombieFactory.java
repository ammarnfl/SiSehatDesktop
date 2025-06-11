package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.ScreenDoorZombie;

public class ScreenDoorZombieFactory implements ZombieFactory<ScreenDoorZombie> {
    public ScreenDoorZombie create(int birthTime) {
        return new ScreenDoorZombie(birthTime);
    }
}