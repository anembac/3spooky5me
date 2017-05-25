package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Looting extends Upgrade {

    //This upgrade increase the chances of you getting more coins.
    public Looting(int level) {
        super(-1, level);
        perLevelMultiplier = 5;
        basePrice = 10;
    }


    /*
    This method determines how many coins you will get, 200% would equal 2 coins.
    250% equals 2 coins and a 50% chance to get an extra coin.
    */
    public int numberOfCoins() {
        int coins = ((int) (getValueDouble() * 100)) / 100;
        if (extraCoin(((int) (getValueDouble() * 100)) % 100)) {
            return (coins + 1);
        } else {
            return coins;
        }
    }

    //Determines if you will get the extra coin based on you extra percentage chance.
    private boolean extraCoin(int chance) {
        Random rnd = new Random();
        return chance >= (rnd.nextInt(100) + 1);
    }

}
