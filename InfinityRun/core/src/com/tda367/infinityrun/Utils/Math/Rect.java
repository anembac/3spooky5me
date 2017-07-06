package com.tda367.infinityrun.Utils.Math;

/*
abstraction of a rectangle from our Vec2 points.
 */
public class Rect {
    public final Vec2 position;
    public final Vec2 bounds;

    public Rect(Vec2 pos, Vec2 bounds) {
        position = pos;
        this.bounds = bounds;
    }

    public Vec2 getBottomLeft() {
        return position;
    }

}
