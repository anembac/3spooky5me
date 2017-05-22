package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */

public class HermesSandals extends Upgrade {

    public HermesSandals(int level) {
        super(100, level);
        upgradeValue = getValueInt();
        basePrice = 1000;
    }
    @Override
    public int getValueInt(){
        return (level);
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if(state.jumpPressed() && mod.inAir && (upgradeValue > 0))
        {
            mod.acceleration.y = 0;
            mod.acceleration.y += 2000;
            --upgradeValue;
        } if(!mod.inAir){
            upgradeValue = getValueInt();
        }
    }
}