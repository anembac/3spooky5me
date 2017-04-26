package com.tda367.infinityrun;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import java.awt.geom.Point2D;

/**
 * Created by miktor on 2017-04-03.
 */
public class Platform extends WorldObject {
private int width = 128;
private int height = 16;

    //generic platform is 128 units wide and 16 high
    public Platform(Vector2 position){
        super(position);
        new Rectangle(position.x, position.y, width, height);
    }




}
