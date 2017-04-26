package com.tda367.infinityrun;

/**
 * Created by miktor on 2017-04-03.
 */
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;
public abstract class WorldObject {

    protected boolean collidable = true;
    protected Vector2 position = new Vector2(0,0);


    public WorldObject(Vector2 position){
        this.position = position;

    }
}
