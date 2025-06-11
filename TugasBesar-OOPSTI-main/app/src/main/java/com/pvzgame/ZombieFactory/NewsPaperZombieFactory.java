package com.pvzgame.ZombieFactory;

import com.pvzgame.Zombie.NewsPaperZombie;

public class NewsPaperZombieFactory implements ZombieFactory<NewsPaperZombie> {
    public NewsPaperZombie create(int birthTime) {
        return new NewsPaperZombie(birthTime);
    }
}