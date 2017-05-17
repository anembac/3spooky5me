package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitChance extends Upgrade{


    public CriticalHitChance(int baseValue) {
        super(60, baseValue);
    }

    @Override
    public int getValueInt(){
        return (5 + super.getValueInt());
    }

    public boolean isCritical (){
        Random rnd = new Random();
        return getValueInt() >= (rnd.nextInt(100)+1);
    }
}
