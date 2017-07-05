package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

/**
 * Created by kaffe on 5/16/17.
 */
public class SpikeObject extends Hazard {
    //Generic spike, 32x32 size.

    //this is an example of a  hazard, that deals damage to any LivingObject it comes in contact with.
    //the damage is based on the difficulty in the WorldGeneration.


    public SpikeObject(Vec2 position, float difficulty) {
        super(position, new Vec2(32, 32), 50 + difficulty);
        setTexture("WorldObjects/Spike.png");


    }
}
