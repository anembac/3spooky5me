package com.tda367.infinityrun.RoomTiles;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldObject;

/**
 * Created by kaffe on 5/16/17.
 */
public class SpikeObject extends WorldObject {
    private int width = 128;
    private int height = 16;
    private  int damage = 10;

    //Generic spike, 32x32 size.

    public SpikeObject(Vec2 position) {
        super(position, new Vec2(32, 32));
        setTexture("WorldObjects/Spike.png");

    }
}
