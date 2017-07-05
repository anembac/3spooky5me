package com.tda367.infinityrun.Model.Upgrades;

import com.tda367.infinityrun.Utils.Math.Vec2;

/**
 * helper class for jumping.
 */
public class ObjectModifiers {
    public final boolean inAir;
    // getters and setters later maybe
    public Vec2 acceleration;

    public ObjectModifiers(boolean inair, Vec2 acc) {
        acceleration = new Vec2(0, 0);
        acceleration = acc.clone();
        inAir = inair;
    }
}
