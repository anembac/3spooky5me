package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class MeleeHandling extends Upgrade {

    //This upgrade increase your damage by a percentage modifier.
    public MeleeHandling(int level) {
        super(-1, level);
        perLevelMultiplier = 5;
        basePrice = 10;
    }
}
