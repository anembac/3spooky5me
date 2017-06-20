package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Math.Vec2;

/**
 * Created by Jacob on 5/16/2017.
 */
public class BrickObject extends WorldObject {
    //Generic Brick, 64x64 size.

    public BrickObject(Vec2 position) {
        super(position, new Vec2(64, 64));
        setTexture("WorldObjects/bricktexture.png");
    }
}
