package com.tda367.infinityrun;


import com.tda367.infinityrun.Math.Vec2;

/**
 * Created by Mikael on 5/15/2017.
 */
public class ObjectModifiers {
    public final boolean inAir;
    // getters and setters later maybe
    public Vec2 acceleration;
    ObjectModifiers(boolean inair, Vec2 acc)
    {
        acceleration = new Vec2(0,0);
        acceleration = acc.clone();
        inAir = inair;
    }
}
