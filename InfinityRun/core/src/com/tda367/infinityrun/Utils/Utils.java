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


    //rotates a Vec2 with linear algebra
    public static Vec2 rotateVec2(Vec2 v, float theta) {
        float xprim = (float) ((v.clone().x * Math.cos(theta)) - (v.clone().y * Math.sin(theta)));
        float yprim = (float) ((v.clone().x * Math.sin(theta)) + (v.clone().y * Math.cos(theta)));

        return new Vec2(xprim, yprim);
    }
    //reflects a vec2 in Y axis with linear algebra
    public static Vec2 yreflection(Vec2 v) {
        float xprim = v.clone().x * 1 + v.clone().y * 0;
        float yprim = v.clone().x * 0 + v.clone().y * -1;
        return new Vec2(xprim, yprim);
    }
    //reflects a Vec2 in X axis with linear algebra
    public static Vec2 xreflection(Vec2 v) {
        float xprim = v.clone().x * -1 + v.clone().y * 0;
        float yprim = v.clone().x * 0 + v.clone().y * 1;
        return new Vec2(xprim, yprim);

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
