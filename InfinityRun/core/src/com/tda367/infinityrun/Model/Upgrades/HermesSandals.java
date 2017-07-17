package com.tda367.infinityrun.Model.Upgrades;

import com.tda367.infinityrun.Model.InputState;

/**
 * Created by Jacob on 5/9/2017.
 */

public class HermesSandals extends Upgrade {
    private int charges;

    //This is an upgrade which lets you jump one more time in the air for each level
    public HermesSandals(int level) {
        super(100, level);
        charges = getValueInt();
        basePrice = 100;
    }

    //You start with one "Air jump" without any levels in this upgrade
    @Override
    public int getValueInt() {
        return (level + 1);
    }


    /*
    This method handles jumping in the air. Everytime you jump in air you consumes one of your charges,
    when you don't have any more charges you can't jump in air anymore. When you land on the ground you replenish your charges.'


    It also resets your vertical acceleration to make jumping more fluid.
    */
    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if (state.jumpPressed() && mod.inAir && (charges > 0)) {
            mod.acceleration.y = 0;
            mod.acceleration.y += 2000;
            --charges;
        }
        if (!mod.inAir) {
            charges = getValueInt();
        }
    }
}