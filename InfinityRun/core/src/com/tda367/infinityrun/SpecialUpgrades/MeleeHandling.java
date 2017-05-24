package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class MeleeHandling extends Upgrade{
    public MeleeHandling(int level) {
        super(-1, level);
        perLevelMultiplier = 5;
        basePrice = 20;
    }
}
