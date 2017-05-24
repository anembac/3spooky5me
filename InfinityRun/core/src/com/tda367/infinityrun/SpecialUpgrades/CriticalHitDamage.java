package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitDamage extends Upgrade{


    public CriticalHitDamage(int baseValue) {
        super(-1, baseValue);
        perLevelMultiplier = 5;
        basePrice = 10;
    }

    @Override
    public double getValueDouble(){
        return  0.05 + super.getValueDouble();
    }
}
