package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Speed extends Upgrade {

    public Speed(int level) {
        super(-1, level);
        upgradeValue = 5;
        basePrice = 20;
    }

    @Override
    public int getValueInt(){
        return 500 + super.getValueInt();
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers modify) {
        if(state.forwardPressed()) modify.acceleration.x += 100;
        if(state.backPressed()) modify.acceleration.x -= 100;
        if(!state.backPressed() && !state.forwardPressed())
        {
            if(modify.acceleration.x < -0.000001)
            {
                modify.acceleration.x += 100;
                if(modify.acceleration.x> 0) modify.acceleration.x = 0;
            }
            else
            {
                modify.acceleration.x -= 100;
                if(modify.acceleration.x < 0) modify.acceleration.x = 0;
            }
        }
    }
}
