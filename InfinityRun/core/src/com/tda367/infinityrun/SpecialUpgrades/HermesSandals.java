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
        perLevelMultiplier = getValueInt();
        basePrice = 1000;
    }
    @Override
    public int getValueInt(){
        return (level + 1);
    }

    @Override
    public void frame(float dt, InputState state, ObjectModifiers mod) {
        if(state.jumpPressed() && mod.inAir && (perLevelMultiplier > 0))
        {
            mod.acceleration.y = 0;
            mod.acceleration.y += 2000;
            --perLevelMultiplier;
        } if(!mod.inAir){
            perLevelMultiplier = getValueInt();
        }
    }
}