package com.tda367.infinityrun;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import javax.swing.text.Position;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by kaffe on 4/26/17.
 */
public class BaseRoom  {

    private List<WorldObject> roomObjects;


    public void addWorldObject(WorldObject obj)
    {
        roomObjects.add(obj);
    }

    public void setup (){
        addWorldObject(new Platform(new Vector2(1,1)));
        addWorldObject(new Platform(new Vector2(31,1)));
        addWorldObject(new Platform(new Vector2(61,1)));
        addWorldObject(new Platform(new Vector2(91,1)));
        addWorldObject(new Platform(new Vector2(121,1)));
    }

}
