package com.tda367.infinityrun.Utils;

import com.tda367.infinityrun.Utils.Math.Vec2;

/**
* Util functions for Shapes. This is generalized to work for WorldObjects.
 */
public class Utils {
    //ensures that things are not broken; and returns the "middle" of the three values. 
    public static float limit(float min, float val, float max) {
        return Math.max(Math.min(max, val), min);
    }


    //gets center of two Vec2
    public static Vec2 getCenter(Vec2 A, Vec2 B) {
        float x = A.x + B.x / 2;
        float y = A.y + B.y / 2;
        return new Vec2(x, y);
    }


    //returns the distance between two centers.
    public static float distance(Vec2 centerA, Vec2 centerB) {
        return Vec2.distance(centerA, centerB);
    }
}
