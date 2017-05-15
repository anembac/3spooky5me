package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class JumpH extends Upgrade {

    public JumpH(int cap, int basevalue) {
        super(cap, basevalue);
    }

    @Override
    public int getValueInt(){
        return 100 + super.getValueInt();
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if(state.jumpPressed() && !mod.inAir)
        {
            mod.acceleration.y += 800;
        }
    }
}
