package com.tda367.infinityrun.Model.Upgrades;

import com.tda367.infinityrun.Model.InputState;

/*

    This class upgrades speed of a livingobject. Each upgrade increases speed by 50, or 10%

 */
public class Speed extends Upgrade {

    //This upgrades handles the movement speed
    public Speed(int level) {
        super(-1, level);
        perLevelMultiplier = 50;
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
