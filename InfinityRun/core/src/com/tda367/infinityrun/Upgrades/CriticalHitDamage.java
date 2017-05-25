package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitDamage extends Upgrade {

    //This upgrade handles the damage increase of your critical hits
    public CriticalHitDamage(int baseValue) {
        super(-1, baseValue);
        perLevelMultiplier = 5;
        basePrice = 10;
    }

    //The base damage increase of a critical hit is to 200% of the normal damage and then 5% for every level of this upgrade
    @Override
    public double getValueDouble() {
        return 2 + (level * perLevelMultiplier) / 100;
    }
}
