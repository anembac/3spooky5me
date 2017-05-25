package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Health extends Upgrade {

    //This upgrades handles the player health
    public Health(int level) {
        super(-1, level);
        perLevelMultiplier = 20;
        basePrice = 10;
    }

    @Override
    public int getValueInt() {
        return 100 + super.getValueInt();
    }
}
