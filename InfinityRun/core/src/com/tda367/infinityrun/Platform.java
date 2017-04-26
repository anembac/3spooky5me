package com.tda367.infinityrun;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import java.awt.geom.Point2D;

/**
 * Created by miktor on 2017-04-03.
 */
public class Platform extends WorldObject {


    //generic platform is 30 units wide and 10 high
    public Platform(Vector2 position)
    {
        this.position = position;
        new Rectangle().setPosition(position).setWidth(128).setHeight(16);


    }


    @Override
    public void frame(double dt) {
        super.frame(dt);
    }

    @Override
    public void render() {
        super.render();
    }
}
