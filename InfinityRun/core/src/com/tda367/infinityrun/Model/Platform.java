package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

/**
 * Created by miktor on 2017-04-03.
 */
public class Platform extends WorldObject {
    // generic platform is 128 units wide and 16 high

    //this is just a variation of the standard "brickObject" WorldObject. However, this is an example how easy it is to create new elements using the WorldObject template.

    public Platform(Vec2 position) {
        super(position, new Vec2(128, 16));
        setTexture("WorldObjects/platform.png");

    }
}