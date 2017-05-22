package com.tda367.infinityrun.RoomTiles;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldObject;

import java.awt.geom.Point2D;

/**
 * Created by miktor on 2017-04-03.
 */
public class Platform extends WorldObject {
    // generic platform is 128 units wide and 16 high
    // we should rewrite this together with worldobject once we get a
    // better picture of how we want worldobjects to work

    public Platform(Vec2 position) {
        super(position, new Vec2(128, 16));
        setTexture("WorldObjects/platform.png");

    }
}