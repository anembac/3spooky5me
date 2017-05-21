package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

import java.util.Timer;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Regeneration extends Upgrade {

    public Regeneration(int level) {
        super(-1, level);
        upgradeValue = 1;
    }

    @Override
    public int getValueInt() {
        return 1 + super.getValueInt();
    }
}
