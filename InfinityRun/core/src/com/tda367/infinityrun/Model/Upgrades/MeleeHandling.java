package com.tda367.infinityrun.Model.Upgrades;

/*
This upgrade increases your damage by a percentage modifier. The base damage is dependent on your weapon.

 */
public class MeleeHandling extends Upgrade {

    //This upgrade increase your damage by a percentage modifier.
    public MeleeHandling(int level) {
        super(-1, level);
        perLevelMultiplier = 5;
        basePrice = 10;
    }
}
