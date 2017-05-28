package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Controller.InputState;

/**
 * Created by Jacob on 5/9/2017.
 */
public class JumpH extends Upgrade {

    //This upgrade handles the height of your jumps
    public JumpH(int level) {
        super(100, level);
        perLevelMultiplier = 20;
        basePrice = 20;
    }

    //Everyone needs to have a base height to the jumps so they can jump.
    @Override
    public int getValueInt() {
        return 800 + super.getValueInt();
    }

    //If you jump while not in air this will get you the acceleration upwards.
    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if (state.jumpPressed() && !mod.inAir) {
            mod.acceleration.y += getValueInt();
        }
    }
}
