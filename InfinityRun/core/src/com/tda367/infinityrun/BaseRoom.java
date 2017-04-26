package com.tda367.infinityrun;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import javax.swing.text.Position;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by kaffe on 4/26/17.
 */
public class BaseRoom  {

    private List<WorldObject> roomObjects = new ArrayList<WorldObject>();


    public void addWorldObject(WorldObject obj)
    {
        roomObjects.add(obj);
    }

    public void setup (){
        addWorldObject(new Platform(new Vector2(0,1)));
        addWorldObject(new Platform(new Vector2(128,1)));
        addWorldObject(new Platform(new Vector2(256,1)));
        addWorldObject(new Platform(new Vector2(384,1)));
        addWorldObject(new Platform(new Vector2(512,1)));
    }

    public List<WorldObject> getRoomObjects(){
        return roomObjects;
    }

}
