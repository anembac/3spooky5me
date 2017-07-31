package com.tda367.infinityrun.Utils.Math;

/*
abstraction of a rectangle from our Vec2 points.
 */
public class Rect {
    public final Vec2 position;
    public final Vec2 bounds;
    public final Vec2 upperLeft;
    public final Vec2 lowerRight;

    public Rect(Vec2 pos, Vec2 bounds) {
        position = pos;
        this.bounds = bounds;

        /*Needed but not used*/
        upperLeft = new Vec2(0,0);
        lowerRight = new Vec2(0,0);
    }

    public Rect(Vec2 lowerLeft, Vec2 upperLeft, Vec2 lowerRight, Vec2 upperRight){
        position = lowerLeft;
        bounds = upperRight;
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }

    public Vec2 getBottomLeft() {
        return position;
    }
}
