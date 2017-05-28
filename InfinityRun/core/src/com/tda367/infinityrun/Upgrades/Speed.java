package com.tda367.infinityrun.Upgrades;

import com.tda367.infinityrun.Controller.InputState;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Speed extends Upgrade {

    //This upgrades handles the movement speed
    public Speed(int level) {
        super(-1, level);
        perLevelMultiplier = 10;
        basePrice = 10;
    }

    //Everything that moves needs a base value to be able to move
    @Override
    public int getValueInt() {
        return 500 + super.getValueInt();
    }

    //This handles the movement with acceleration, if you press forward you will get acceleration in positive x-direction.
    @Override
    public void frame(float dt, InputState state, ObjectModifiers modify) {
        if (state.forwardPressed() && modify.acceleration.x < getValueInt()) modify.acceleration.x += 100;
        if (state.backPressed() && modify.acceleration.x > -getValueInt()) modify.acceleration.x -= 100;
        if (!state.backPressed() && !state.forwardPressed()) {
            if (modify.acceleration.x < -0.000001) {
                modify.acceleration.x += 10;
                if (modify.acceleration.x > 0) modify.acceleration.x = 0;
            } else {
                modify.acceleration.x -= 10;
                if (modify.acceleration.x < 0) modify.acceleration.x = 0;
            }
        }
        //modify.acceleration.x = Utils.limit(-getValueInt(), modify.acceleration.x, getValueInt());
    }
}
