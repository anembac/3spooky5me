package com.tda367.infinityrun;

import java.awt.geom.Point2D;

/**
 * Created by Mikael on 5/3/2017.
 */
public class CollisionManager {
    private static CollisionManager manager = null;
    KdTree<WorldObject> kdTree = new KdTree<WorldObject>();

    public static CollisionManager getInstance()
    {
        if(manager == null) manager=new CollisionManager();
        return manager;
    }

    public CollisionManager forceNewInstance()
    {
        manager = new CollisionManager();
        return manager;
    }

    public CollisionManager()
    {

    }

    public void addWorldObject(WorldObject obj)
    {
        kdTree.insert(new Point2D.Double(obj.position.x, obj.position.y), obj);
        kdTree.insert(new Point2D.Double(obj.position.x, obj.position.y+obj.bounds.y), obj);
        kdTree.insert(new Point2D.Double(obj.position.x + obj.bounds.x, obj.position.y), obj);
        kdTree.insert(new Point2D.Double(obj.position.x+obj.bounds.x, obj.position.y+obj.bounds.y), obj);
    }

    public float getWalkableHeight(WorldObject obj)
    {
        return 10;
    }
}
