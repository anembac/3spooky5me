package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.Upgrade;

import java.util.Timer;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Regeneration extends Upgrade {

    public Regeneration(String upgradeName, int cap, boolean multiply, int basevalue) {
        super(upgradeName, cap, multiply, basevalue);

    }

    @Override
    public void frame(float dt) {
        float hpreg = System.nanoTime() / 1000000000 * dt * getValue();
    }
}
