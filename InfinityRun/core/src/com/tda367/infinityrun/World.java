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


    public void increaseDifficulty (double difficulty){
        this.difficulty = difficulty + 0.05;
    }

    public World()
    {
        worldObjects = new ArrayList<WorldObject>();
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
        for(WorldObject obj : worldObjects)
        {
            if(obj instanceof MovableObject)
            {
                MovableObject mo = (MovableObject)obj;
                mo.position.add(Vec2.mul(mo.acceleration, dt));
            }
        }
    }
    // to add the player etc to the world.
    public void addWorldObject(WorldObject obj)
    {
        worldObjects.add(obj);
        kdTree.insert(new Point2D.Double(obj.position.x, obj.position.y), obj);
        kdTree.insert(new Point2D.Double(obj.position.x, obj.position.y+obj.bounds.y), obj);
        kdTree.insert(new Point2D.Double(obj.position.x + obj.bounds.x, obj.position.y), obj);
        kdTree.insert(new Point2D.Double(obj.position.x+obj.bounds.x, obj.position.y+obj.bounds.y), obj);
    }
//
//    public void frame(double dt)
//    {
//        for(WorldObject obj : worldObjects)
//        {
//            obj.frame(dt);
//        }
//    }
//
//
//    public void render()
//    {
//        for(WorldObject obj : worldObjects)
//        {
//
//            obj.render();
//        }
//    }

}
