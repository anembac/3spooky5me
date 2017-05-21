package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Looting extends Upgrade {

    public Looting(int level) {
        super(-1, level);
        upgradeValue = 5;
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

}
