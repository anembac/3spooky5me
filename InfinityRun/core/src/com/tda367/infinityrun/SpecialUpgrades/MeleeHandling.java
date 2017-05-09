package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class MeleeHandling extends Upgrade{
    public MeleeHandling(int cap, int basevalue) {
        super(cap, basevalue);
    }

    @Override
    public void frame(float dt) {
        //double characterDamage = weapon.getDamage*getValueDouble(); not sure if it should be like this, might be a little different like a setter or something.
    }
}
