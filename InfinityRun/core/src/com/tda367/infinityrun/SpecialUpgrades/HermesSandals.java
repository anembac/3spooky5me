package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */

public class HermesSandals extends Upgrade {
    private boolean isUsed = false;

    public HermesSandals(int cap, int basevalue) {
        super(cap, basevalue);
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if(state.jumpPressed() && mod.inAir && !isUsed)
        {
            mod.acceleration.y += 800;
            isUsed = true;
        } if(!mod.inAir){
            isUsed = false;
        }
    }
}