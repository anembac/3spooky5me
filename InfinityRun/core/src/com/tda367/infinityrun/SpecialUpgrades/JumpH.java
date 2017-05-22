package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class JumpH extends Upgrade {



    public JumpH(int level) {
        super(100, level);
        upgradeValue = 20;
        basePrice = 20;
    }

    @Override
    public int getValueInt(){
        return 800 + super.getValueInt();
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if(state.jumpPressed() && !mod.inAir)
        {
            mod.acceleration.y += getValueInt();
        }
    }
}
