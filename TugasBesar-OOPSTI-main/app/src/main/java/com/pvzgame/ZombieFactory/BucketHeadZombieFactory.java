package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.BucketHeadZombie;

public class BucketHeadZombieFactory implements ZombieFactory<BucketHeadZombie> {
    public BucketHeadZombie create(int birthTime) {
        return new BucketHeadZombie(birthTime);
    }
}