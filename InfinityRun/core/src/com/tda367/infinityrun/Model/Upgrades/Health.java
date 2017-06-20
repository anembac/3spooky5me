package com.tda367.infinityrun.Model.Upgrades;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Health extends Upgrade {

    //This upgrades handles the player health
    public Health(int level) {
        super(-1, level);
        perLevelMultiplier = 25;
        basePrice = 10;
    }

    @Override
    public int getValueInt() {
        return 100 + super.getValueInt();
    }
}
