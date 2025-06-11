package com.pvzgame.Zombie;
import com.pvzgame.*;

public class NewsPaperZombie extends Zombie {
    
    // Constructor
    public NewsPaperZombie(int birthTime) {
        setBirthTime(birthTime);
        setZombieType(6);
        setZombieName("NewsPaperZombie");
        setZombieHealth(200);
        setZombieAttackDamage(100);
        setZombieAttackSpeed(1);
        setZombieMoveSpeed(2);
        setCurrentMovePoints(20);
        setIsAquatic(false);
        setIsHidden(false);
        setIsSlowed(false);
        setHasTool(true);   
    }

    @Override
    public int zombieCheck(Map map, int row, int col){

        // slow mechanism
        if (getSlowPoints() >= 3){ // pengecekan apakah sudah waktunya unslow
            unslowZombie();
        } else if (getIsSlowed()){ // counter slow zombie
            addSlowPoints();
        }

        // tool check
        if (getZombieHealth() <= 125){ // pengecekan tool dan mengubah status tool
            setHasTool(false);
            if (!getHasTool()){
                setZombieMoveSpeed(4);
                setZombieAttackSpeed(2);
            }
        }

        // move mechanism
        if (map.getTile(row, col).getPlant() != null){ // mengecek ada tanaman atau tidak
            return 1;
        } else {
            addCurrentMovePoints(getZombieMoveSpeed()); // movemodifier defaultnya 2 jadinya movetime setiap detik nambah 2
            if (getCurrentMovePoints() >= getMovePoint()){ // apakah sudah waktunya bergerak
                resetCurrentMovePoints();
                return 2;
            } else {
                return 0;
            }
        }
    }
}
