package com.tda367.infinityrun.Upgrades;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Looting extends Upgrade {

    //This upgrade increase the chances of you getting more coins.
    public Looting(int level) {
        super(-1, level);
        perLevelMultiplier = 10;
        basePrice = 5;
    }
}
