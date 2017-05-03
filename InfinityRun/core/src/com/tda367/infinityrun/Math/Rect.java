package com.tda367.infinityrun.Math;

/**
 * Created by Mikael on 5/3/2017.
 */
public class Rect {
    Vec2 position;
    Vec2 bounds;

    public Rect(Vec2 pos, Vec2 bounds)
    {
        position = pos;
        this.bounds = bounds;
    }

    public Vec2 getBottomLeft()
    {
        return position;
    }

}
