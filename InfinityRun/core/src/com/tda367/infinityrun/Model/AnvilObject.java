package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

import static com.tda367.infinityrun.Utils.Constants.meter;

/**
 * Created by kaffe on 7/20/17.
 */
public class AnvilObject extends WorldObject {
    //Generic coin,64x64 size.

    public AnvilObject(Vec2 position) {
        super(position, new Vec2(meter, meter));
        setTexture("WorldObjects/anvil.png");
        setCollidable(false);

    }
}
