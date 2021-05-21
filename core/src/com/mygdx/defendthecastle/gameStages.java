package com.mygdx.defendthecastle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class gameStages {
    public int roundNumber,totalSpawned,maxEnemies;
    float clock;
    Array<swordMan> swordMen;

    public gameStages() {
        roundNumber = 1;
        maxEnemies = 10;
        totalSpawned = 0;
        swordMen = new Array<>();
        swordMen.add(new swordMan());
    }
    public Array<swordMan> returnSwordMan() { // Enemy types | 0: None | 1: swordMan |
        clock += Gdx.graphics.getDeltaTime();
        if (totalSpawned < maxEnemies+roundNumber) {
            if (clock >= 1) {
                clock = 0;
                totalSpawned++;
                swordMen.add(new swordMan());
            }
        }
        return swordMen;
    }
    public boolean isRoundOver() {
        if (swordMen.size <= 0 && totalSpawned <= maxEnemies+roundNumber) {
            return true;
        }
        else {
            return false;
        }
    }
}
