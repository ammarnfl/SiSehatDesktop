package com.pvzgame.ZombieFactory;
import com.pvzgame.Zombie.Zombie;

public interface ZombieFactory<T extends Zombie> {
    public T create(int birthTime);
}