package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Regeneration extends Upgrade {

    //This upgrade increases health regeneration per second
    public Regeneration(int level) {
        super(-1, level);
        perLevelMultiplier = 1;
        basePrice = 100;
    }

    //The base value is 1 health/second
    @Override
    public int getValueInt() {
        return 1 + super.getValueInt();
    }
}
