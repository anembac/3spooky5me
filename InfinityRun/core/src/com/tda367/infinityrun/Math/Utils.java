package com.tda367.infinityrun.Math;

import com.tda367.infinityrun.WorldObject;

/**
 * Created by Mikael on 5/3/2017.
 */
public class Utils {
    public static float limit(float min, float val, float max)
    {
        return Math.max(Math.min(max,val),min);
    }
    public static Vec2 getCenter(WorldObject obj)
    {
        float x = obj.getDrawingRect().position.x + obj.getDrawingRect().bounds.x / 2;
        float y = obj.getDrawingRect().position.y + obj.getDrawingRect().bounds.y / 2;
        return new Vec2(x,y);
    }
    public static float distance(WorldObject a, WorldObject b)
    {
        return Vec2.distance(getCenter(a), getCenter(b));
    }
}
