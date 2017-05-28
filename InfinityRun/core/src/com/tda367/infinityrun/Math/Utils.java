package com.tda367.infinityrun.Math;

/**
 * Created by Mikael on 5/3/2017.
 */
public class Utils {
    public static float limit(float min, float val, float max) {
        return Math.max(Math.min(max, val), min);
    }

    public static Vec2 getCenter(Vec2 A, Vec2 B) {
        float x = A.x + B.x / 2;
        float y = A.y + B.y / 2;
        return new Vec2(x, y);
    }

    public static float distance(Vec2 centerA, Vec2 centerB) {
        return Vec2.distance(centerA, centerB);
    }
}
