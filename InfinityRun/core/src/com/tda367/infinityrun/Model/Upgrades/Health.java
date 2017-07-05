package com.tda367.infinityrun.Model.Upgrades;

/*
    //This upgrades handles the player health. Each level of the upgrade increases health by 25.
 */
public class Health extends Upgrade {



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
