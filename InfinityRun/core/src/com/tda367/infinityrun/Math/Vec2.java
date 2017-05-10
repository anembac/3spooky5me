package com.tda367.infinityrun.Math;

import com.sun.javafx.geom.Vec2f;

import java.awt.geom.Point2D;

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

    public Vec2 clone()
    {
        return new Vec2(x,y);
    }

    public Vec2(Point2D.Double pt)
    {
        x = (float)pt.x;
        y = (float)pt.y;
    }

    public static Vec2 dotProduct(Vec2 a, Vec2 b)
    {
        return new Vec2(a.x*b.x,a.y*b.y);
    }

    public void normalize()
    {
        x/=len();
        y/=len();
    }

    public float len()
    {
        return (float)Math.sqrt(x*x+y*y);
    }

    public void add(Vec2 v)
    {
        x += v.x;
        y += v.y;
    }

    public static Vec2 add(Vec2 a, Vec2 b)
    {
        return new Vec2(a.x+b.x,a.y+b.y);
    }

    public Vec2 sub(Vec2 v)
    {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public static Vec2 mul(Vec2 v, float factor)
    {
        return new Vec2(v.x*factor, v.y*factor);
    }

}
