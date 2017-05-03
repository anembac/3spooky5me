package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 4/3/2017.
 */
public class World {

    private List<WorldObject> worldObjects;
    private KdTree<WorldObject> kdTree = new KdTree<WorldObject>();
    private double difficulty = 1.0;
    IInput input = null;

    public void increaseDifficulty (double difficulty){
        this.difficulty = difficulty + 0.05;
    }

    public World()
    {
        worldObjects = new ArrayList<WorldObject>();
        input = new Input();
    }

    public List<WorldObject> getWorldObjects()
    {
        return worldObjects;
    }

    public void generateWorld()
    {
        for(int i = 0; i < 200; i++)
        {
            addWorldObject(new Platform(new Vec2(10+i*128,10+i*16)));
        }
    }

    public float groundLevel(WorldObject obj)
    {

        return 0.0f;
    }


    public void frame(float dt)
    {
        input.collectInput();
        for(WorldObject obj : worldObjects)
        {
            obj.frame(dt, input.getInput());
        }
    }
    // to add the player etc to the world.
    public void addWorldObject(WorldObject obj)
    {
        worldObjects.add(obj);
        CollisionManager.getInstance().addWorldObject(obj);
    }

}
