package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

import java.util.Timer;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Regeneration extends Upgrade {

    public Regeneration(int cap, int basevalue) {
        super(cap, basevalue);

    }

    @Override
    public void frame(float dt, InputState input, ObjectModifiers modify) {
        /*
        if(character.currentHealth < character.maxHealth) {
            float HealthReg = System.nanoTime() / 1000000000 * dt * getValueInt();
        }
        */
    }
}
