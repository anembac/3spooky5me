package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitDamage extends Upgrade{


    public CriticalHitDamage(int cap, int basevalue) {
        super(cap, basevalue);
    }

    @Override
    public double getValueDouble(){
        return  2 + super.getValueDouble();
    }

    @Override
    public void frame(float dt) {
        /*
        if(attack.criticalHit) {
            character.damage*getValueDouble();
        }
        */
    }
}
