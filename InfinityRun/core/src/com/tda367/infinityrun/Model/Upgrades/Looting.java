package com.tda367.infinityrun.Model.Upgrades;

/*

    //This upgrade increase the chances of you getting more coins. Each upgrade gives you a 15% chance to get an extra coin.
    // would this be more than 100%, you will always get +1 coin, and have the remainder of the chance to get an extra one.
    //this means that 225% chance would give you 1 base coin, 2 coins from the upgrade, and 25% chance to get a fourth coin.
 */
public class Looting extends Upgrade {

    public Looting(int level) {
        super(-1, level);
        perLevelMultiplier = 10;
        basePrice = 5;
    }
}
