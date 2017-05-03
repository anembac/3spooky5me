package com.tda367.infinityrun.Math;

/**
 * Created by Mikael on 5/3/2017.
 */
public class Utils {
    public static float limit(float min, float val, float max)
    {
        return Math.max(Math.min(max,val),min);
    }
}
