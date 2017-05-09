package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Looting extends Upgrade {

    public Looting(String upgradeName, int cap, boolean multiply, int basevalue) {
        super(upgradeName, cap, multiply, basevalue);
    }

    public int numberOfCoins() {
        int coins = ((int) (getValueDouble() * 100)) / 100;
        if (extraCoin(((int) (getValueDouble() * 100)) % 100)) {
            return (coins + 1);
        } else {
            return coins;
        }
    }

    private boolean extraCoin(int chance) {
        Random rnd = new Random();
        return chance >= (rnd.nextInt(100) + 1);
    }

    @Override
    public void frame(float dt) {
        /*
        if(character.findCoin) {
            character.addCoin(numberOfCoins);
        }
        */
    }
}
