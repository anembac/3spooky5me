package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class MeleeHandling extends Upgrade{
    public MeleeHandling(int baseValue) {
        super(-1, baseValue);
    }

    @Override
    public void frame(float dt, InputState input, ObjectModifiers modify) {
        //double characterDamage = weapon.getDamage*getValueDouble(); not sure if it should be like this, might be a little different like a setter or something.
    }
}
