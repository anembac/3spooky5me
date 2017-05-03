package com.tda367.infinityrun.Math;

/**
 * Created by Mikael on 5/3/2017.
 */
public class Vec2 {
    public float x = 0;
    public float y = 0;

    public Vec2()
    {

    }

    public Vec2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void normalize()
    {
        x/=len();
        y/=len();
    }

    public double len()
    {
        return Math.sqrt(x*x+y*y);
    }

    public void add(Vec2 v)
    {
        x += v.x;
        y += v.y;
    }

    public void sub(Vec2 v)
    {
        x -= v.x;
        y -= v.y;
    }

    public static Vec2 mul(Vec2 v, float factor)
    {
        return new Vec2(v.x*factor, v.y*factor);
    }

    public static Vec2 add(Vec2 a, Vec2 b)
    {
        return new Vec2(a.x+b.x,a.y+b.y);
    }
}
