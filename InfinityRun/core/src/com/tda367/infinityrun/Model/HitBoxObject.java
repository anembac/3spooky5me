package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

public class HitBoxObject extends WorldObject{

    public HitBoxObject(Vec2 position, Vec2 bounds) {
        super(position, bounds);
        setCollidable(false);
    }

}
