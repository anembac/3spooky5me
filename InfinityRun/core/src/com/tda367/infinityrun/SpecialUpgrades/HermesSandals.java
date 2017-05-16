package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */

public class HermesSandals extends Upgrade {
    private int charges;

    public HermesSandals(int cap, int basevalue) {
        super(cap, basevalue);
        charges = getValueInt();
    }
    @Override
    public int getValueInt(){
        return (200 + super.getValueInt());
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if(state.jumpPressed() && mod.inAir && (charges > 0))
        {
            mod.acceleration.y = 0;
            mod.acceleration.y += 800;
            --charges;
        } if(!mod.inAir){
            charges = getValueInt();
        }
    }
}