package com.tda367.infinityrun.Math;

/**
 * Created by Mikael on 5/9/2017.
 */
public class Vec4 {
    public float x, y, z, w;

    public Vec4() {

    }

    public Vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void add(float ax, float ay, float az, float aw) {
        x += ax;
        y += ay;
        z += az;
        w += aw;
    }
}
