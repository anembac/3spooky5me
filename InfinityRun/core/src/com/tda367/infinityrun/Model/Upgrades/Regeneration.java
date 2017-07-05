package com.tda367.infinityrun.Model.Upgrades;

/*
    This upgrade increases health regeneration per second each level gives one extra health per second.

 */
public class Regeneration extends Upgrade {


    public Regeneration(int level) {
        super(-1, level);
        perLevelMultiplier = 1;
        basePrice = 10;
    }

    //The base value is 1 health/second
    @Override
    public int getValueInt() {
        return 1 + super.getValueInt();
    }
}
