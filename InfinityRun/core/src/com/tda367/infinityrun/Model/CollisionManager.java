package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.KdTree;
import com.tda367.infinityrun.Utils.Math.KdTreeNode;
import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Utils.Math.Vec4;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
This class handles collisions between WorldObjects using Kd-Tree.
 */
public class CollisionManager {
    private static CollisionManager manager = null;
    private final KdTree<WorldObject> kdTree = new KdTree<WorldObject>();
    // This is kind of double stored data but it is neccecary for good complexity,
    // it allows us to map some things in both ways with O(1) and O(logn) complexity.
    private final HashMap<WorldObject, KdTreeNode<WorldObject>> worldObjectToNodes;


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

    private CollisionManager()
    {
        worldObjectToNodes = new HashMap<WorldObject, KdTreeNode<WorldObject>>();
    }


    //adds worldobjects
    public void addWorldObject(WorldObject obj)
    {
        Vec2 a = WOWrapper.worldObjectCenter(obj);
        worldObjectToNodes.put(obj, kdTree.insert(new Point2D.Double(a.x, a.y), obj));
    }

    //positions are updated by removing and reading objects.
    public void updatePosition(WorldObject obj)
    {
        removeObject(obj);
        addWorldObject(obj);
    }


    //when removing WorldObjects, they are also removed from the Kd-Tree
    public void removeObject(WorldObject obj)
    {
        if(!worldObjectToNodes.containsKey(obj))
        {
            return;
        }
        kdTree.removePoint(worldObjectToNodes.get(obj));
        worldObjectToNodes.remove(obj);
    }

    //rangeSearch is a little shifty still, and usage should be replaced.
    public List<WorldObject> rangeSearch(float left, float right, float top, float bot, WorldObject requestor)
    {
         List<WorldObject> output = nodeListToWOList(kdTree.rangeSearch2D(left, right, top, bot));
         if(output.contains(requestor)) output.remove(requestor);
         return output;
    }


    //this get's the K nearest WorldObjects using the kd-tree
    public List<WorldObject> getKNearest(WorldObject requestor, int k)
    {
        Vec2 center = WOWrapper.worldObjectCenter(requestor);
        List<KdTreeNode<WorldObject>> nodes = kdTree.getKNN(new Point2D.Double(center.x,center.y), k);
        List<WorldObject> output= nodeListToWOList(nodes);
        if(output.contains(requestor)) output.remove(requestor);
        return output;
    }


    //this asigns nodes in the kd-tree to WorldObjects.
    private List<WorldObject> nodeListToWOList(List<KdTreeNode<WorldObject>> list)
    {
        List<WorldObject> output = new ArrayList<WorldObject>();
        for(KdTreeNode<WorldObject> o : list)
        {
            output.add(o.data);
        }
        return output;
    }

    // Complexity = O(log(n))
    public Vec4 getDistanceToCollission(WorldObject obj)
    {
        // This algorithm could be changed to use the 2 range searches instead, 1 for each axis.
        float cx, cy;
        cx = obj.getPosition().x + obj.getDrawingRect().bounds.x / 2;
        cy = obj.getPosition().y + obj.getDrawingRect().bounds.y / 2;
        // Initialize the intersection pts to never intersect
        Vec4 output = new Vec4(-1000000000,-100000000,10000000,10000000);

        List<KdTreeNode<WorldObject>> nodes = kdTree.getKNN(new Point2D.Double(cx,cy), 30); // get the 100 closest points, this should be enough, We could update this to use the range search algo later.
        for(KdTreeNode<WorldObject> node : nodes){
            if(node.data == obj || !node.data.getCollidable()) continue;
            /*
                We just calculate the distance to the 4 directional planes on the center of the WorldObject
             */
            Vec2 a = new Vec2(0,-1); // dir down
            Vec2 b = new Vec2(-1,0); // dir left
            Vec2 c = new Vec2(0,1); // dir up
            Vec2 d = new Vec2(1,0); // dir right
            // the centerpoint is used.

            // The 4 corners of our obj
            Vec2 oCornerA, oCornerB, oCornerC, oCornerD;
            // bl, tl, tr, br
            oCornerA = obj.getPosition().clone();
            oCornerB = Vec2.add(obj.getPosition(), new Vec2(0, obj.getDrawingRect().bounds.y));
            oCornerC = Vec2.add(obj.getPosition(), obj.getDrawingRect().bounds);
            oCornerD = Vec2.add(obj.getPosition(), new Vec2(obj.getDrawingRect().bounds.x, 0));

            // The 4 corners of our target object
            Vec2 tCornerA, tCornerB, tCornerC, tCornerD;
            // bl, tl, tr, br
            tCornerA = node.data.getPosition().clone();
            tCornerB = Vec2.add(node.data.getPosition(), new Vec2(0, node.data.getDrawingRect().bounds.y));
            tCornerC = Vec2.add(node.data.getPosition(), node.data.getDrawingRect().bounds);
            tCornerD = Vec2.add(node.data.getPosition(), new Vec2(node.data.getDrawingRect().bounds.x, 0));

            // For the float precision errors we have to make the character a little bit smaller.
            oCornerA.add(new Vec2(0.5f,0.5f));
            oCornerB.add(new Vec2(0.5f,-0.5f));
            oCornerC.add(new Vec2(-0.5f,-0.5f));
            oCornerD.add(new Vec2(-0.5f,0.5f));

            // We have to calculate the signed distance to every point around them.
            // We have to use the signed distance here, that's just that direction vectors "coordinate"
            float aLen = Vec2.dotProduct(a,tCornerC).sub(Vec2.dotProduct(a,oCornerA)).y;
            float bLen = Vec2.dotProduct(b,tCornerC).sub(Vec2.dotProduct(b,oCornerA)).x;
            float cLen = Vec2.dotProduct(c,tCornerA).sub(Vec2.dotProduct(c,oCornerC)).y;
            float dLen = Vec2.dotProduct(d,tCornerA).sub(Vec2.dotProduct(d,oCornerC)).x;

            // Horizontal (Vertical intersection)
            if(pointBetweenHorizontal(oCornerA, tCornerA, oCornerD) || pointBetweenHorizontal(oCornerA, tCornerD, oCornerC) ||
                    pointBetweenHorizontal(tCornerA, oCornerA, tCornerD) || pointBetweenHorizontal(tCornerA, oCornerD, tCornerD))
            {
                // floor intersection
                if(aLen > 0) output.x = Math.max(output.x, tCornerC.y);
                // roof intersection
                if(cLen > 0) output.z = Math.min(output.z, tCornerA.y);
            }

            // Vertical (Horizontal intersection)
            if(pointBetweenVertical(oCornerA, tCornerA, oCornerB) || pointBetweenVertical(oCornerA, tCornerB, oCornerB) ||
                    pointBetweenVertical(tCornerA, oCornerA, tCornerB) || pointBetweenVertical(tCornerA, oCornerB, tCornerB))
            {
                // left intersection
                if(bLen > 0) output.y = Math.max(output.y, tCornerC.x);
                // right intersection
                if(dLen > 0) output.w = Math.min(output.w, tCornerA.x);
            }

            if(node.point.x > oCornerA.x && node.point.x < (oCornerC.x) && node.point.y > oCornerA.y && node.point.y < oCornerC.y)
            {
                // Bug here, this intersection doesn't cover angled velocity intersections so we have to
                // roll back the position. This doesn't occur very often.

                // Check what side are the closest
                float e,f,g,h;
                //down
                e = (float)node.point.y - oCornerA.y;
                f = (float)node.point.x - oCornerA.x;
                g = oCornerC.y - (float)node.point.y;
                h = oCornerC.x - (float)node.point.x;
                if(e < Math.min(Math.min(f,g),h)) output.x = (float)node.point.y;
                if(f < Math.min(Math.min(e,g),h)) output.y = (float)node.point.x;
                if(g < Math.min(Math.min(f,e),h)) output.z = (float)node.point.y;
                if(h < Math.min(Math.min(e,g),f)) output.w = (float)node.point.x;
            }
        }

        // down left up right
        return output;
    }


    //Returns a WorldObject that the given WorldObject collides with.
    public WorldObject getCollidedObject(WorldObject obj)
    {
        // This algorithm could be changed to use the 2 range searches instead, 1 for each axis.
        float cx, cy;
        cx = obj.getPosition().x + obj.getDrawingRect().bounds.x / 2;
        cy = obj.getPosition().y + obj.getDrawingRect().bounds.y / 2;
        // Initialize the intersection pts to never intersect
        Vec4 output = new Vec4(-1000000000,-100000000,10000000,10000000);

        List<KdTreeNode<WorldObject>> nodes = kdTree.getKNN(new Point2D.Double(cx,cy), 30); // get the 100 closest points, this should be enough, We could update this to use the range search algo later.
        for(KdTreeNode<WorldObject> node : nodes){
            if(node.data == obj || !node.data.getCollidable()) continue;
            /*
                We just calculate the distance to the 4 directional planes on the center of the WorldObject
             */
            Vec2 a = new Vec2(0,-1); // dir down
            Vec2 b = new Vec2(-1,0); // dir left
            Vec2 c = new Vec2(0,1); // dir up
            Vec2 d = new Vec2(1,0); // dir right
            // the centerpoint is used.

            // The 4 corners of our obj
            Vec2 oCornerA, oCornerB, oCornerC, oCornerD;
            // bl, tl, tr, br
            oCornerA = obj.getPosition().clone();
            oCornerB = Vec2.add(obj.getPosition(), new Vec2(0, obj.getDrawingRect().bounds.y));
            oCornerC = Vec2.add(obj.getPosition(), obj.getDrawingRect().bounds);
            oCornerD = Vec2.add(obj.getPosition(), new Vec2(obj.getDrawingRect().bounds.x, 0));

            // The 4 corners of our target object
            Vec2 tCornerA, tCornerB, tCornerC, tCornerD;
            // bl, tl, tr, br
            tCornerA = node.data.getPosition().clone();
            tCornerB = Vec2.add(node.data.getPosition(), new Vec2(0, node.data.getDrawingRect().bounds.y));
            tCornerC = Vec2.add(node.data.getPosition(), node.data.getDrawingRect().bounds);
            tCornerD = Vec2.add(node.data.getPosition(), new Vec2(node.data.getDrawingRect().bounds.x, 0));

            // For the float precision errors we have to make the character a little bit smaller.
            oCornerA.add(new Vec2(0.5f,0.5f));
            oCornerB.add(new Vec2(0.5f,-0.5f));
            oCornerC.add(new Vec2(-0.5f,-0.5f));
            oCornerD.add(new Vec2(-0.5f,0.5f));

            // We have to calculate the signed distance to every point around them.
            // We have to use the signed distance here, that's just that direction vectors "coordinate"
            float aLen = Vec2.dotProduct(a,tCornerC).sub(Vec2.dotProduct(a,oCornerA)).y;
            float bLen = Vec2.dotProduct(b,tCornerC).sub(Vec2.dotProduct(b,oCornerA)).x;
            float cLen = Vec2.dotProduct(c,tCornerA).sub(Vec2.dotProduct(c,oCornerC)).y;
            float dLen = Vec2.dotProduct(d,tCornerA).sub(Vec2.dotProduct(d,oCornerC)).x;

            // Horizontal (Vertical intersection)
            if(pointBetweenHorizontal(oCornerA, tCornerA, oCornerD) || pointBetweenHorizontal(oCornerA, tCornerD, oCornerC) ||
                    pointBetweenHorizontal(tCornerA, oCornerA, tCornerD) || pointBetweenHorizontal(tCornerA, oCornerD, tCornerD))
            {
                // floor intersection
                if(aLen > 0) output.x = Math.max(output.x, tCornerC.y);
                // roof intersection
                if(cLen > 0) output.z = Math.min(output.z, tCornerA.y);

                return node.data;
            }

            // Vertical (Horizontal intersection)
            if(pointBetweenVertical(oCornerA, tCornerA, oCornerB) || pointBetweenVertical(oCornerA, tCornerB, oCornerB) ||
                    pointBetweenVertical(tCornerA, oCornerA, tCornerB) || pointBetweenVertical(tCornerA, oCornerB, tCornerB))
            {
                // left intersection
                if(bLen > 0) output.y = Math.max(output.y, tCornerC.x);
                // right intersection
                if(dLen > 0) output.w = Math.min(output.w, tCornerA.x);


                return node.data;
            }

            if(node.point.x > oCornerA.x && node.point.x < (oCornerC.x) && node.point.y > oCornerA.y && node.point.y < oCornerC.y)
            {
                // Bug here, this intersection doesn't cover angled velocity intersections so we have to
                // roll back the position. This doesn't occur very often.

                // Check what side are the closest
                float e,f,g,h;
                //down
                e = (float)node.point.y - oCornerA.y;
                f = (float)node.point.x - oCornerA.x;
                g = oCornerC.y - (float)node.point.y;
                h = oCornerC.x - (float)node.point.x;
                if(e < Math.min(Math.min(f,g),h)) output.x = (float)node.point.y;
                if(f < Math.min(Math.min(e,g),h)) output.y = (float)node.point.x;
                if(g < Math.min(Math.min(f,e),h)) output.z = (float)node.point.y;
                if(h < Math.min(Math.min(e,g),f)) output.w = (float)node.point.x;
            }

        }
        //No collision with worldobject found
        return null;
    }

    private boolean pointBetweenHorizontal(Vec2 start, Vec2 point, Vec2 end)
    {
        return start.x <= point.x && end.x >= point.x;
    }

    private boolean pointBetweenVertical(Vec2 start, Vec2 point, Vec2 end)
    {
        return start.y <= point.y && end.y >= point.y;
    }
}
