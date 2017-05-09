package com.tda367.infinityrun.SpecialUpgrades;

import com.tda367.infinityrun.Upgrade;

import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitChance extends Upgrade{


    public CriticalHitChance(int cap, int basevalue) {
        super(cap, basevalue);
    }

    @Override
    public int getValueInt(){
        return (5 + super.getValueInt());
    }

    public boolean isCritical (){
        Random rnd = new Random();
        return getValueInt() >= (rnd.nextInt(100)+1);
    }

    @Override
    public void frame(float dt) {
    /*
        if(character.attacks) {
        isCritical();
    }
    */
    }
}
