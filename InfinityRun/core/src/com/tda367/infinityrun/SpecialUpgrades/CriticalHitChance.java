package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitChance extends Upgrade{


    public CriticalHitChance(int level) {
        super(60, level);
        upgradeValue = 1;
        basePrice = 10;
    }

    public boolean isCritical (){
        Random rnd = new Random();
        return getValueInt() >= (rnd.nextInt(100)+1);
    }
}
