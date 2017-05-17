package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Health extends Upgrade{

    public Health(int baseValue) {
        super(-1, baseValue);
    }

    @Override
    public int getValueInt(){
        return 100 + super.getValueInt();
    }
}
