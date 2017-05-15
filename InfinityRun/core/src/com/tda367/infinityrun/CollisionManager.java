package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.Math.Vec4;

import java.awt.geom.Point2D;
import java.util.List;

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

    // Complexity = O(log(n))
    public Vec4 getDistanceToCollission(WorldObject obj)
    {
        // This algo could be changed to use the 2 range searches instead, 1 for each axis.
        float cx, cy;
        cx = obj.position.x + obj.bounds.x / 2;
        cy = obj.position.y + obj.bounds.y / 2;
        // Initialize the intersection pts to never intersect
        Vec4 output = new Vec4(0,0,10000000,10000000);

        List<KdTreeNode<WorldObject>> nodes = kdTree.getKNN(new Point2D.Double(cx,cy), 100); // get the 100 closest points, this should be enough, We could update this to use the range search algo later.
        for(KdTreeNode<WorldObject> node : nodes){
            /*
                We just calculate the distance to the 4 directional planes on the center of the WorldObject
             */
            Vec2 a = new Vec2(0,-1); // dir down
            Vec2 b = new Vec2(-1,0); // dir left
            Vec2 c = new Vec2(0,1); // dir up
            Vec2 d = new Vec2(1,0); // dir right
            // I dont want to use the nearest point here, i want to use the found objects center point.

            // The 4 corners of our obj
            Vec2 oCornerA, oCornerB, oCornerC, oCornerD;
            // bl, tl, tr, br
            oCornerA = obj.position.clone();
            oCornerB = Vec2.add(obj.position, new Vec2(0, obj.bounds.y));
            oCornerC = Vec2.add(obj.position, obj.bounds);
            oCornerD = Vec2.add(obj.position, new Vec2(obj.bounds.x, 0));

            // The 4 corners of our target object
            Vec2 tCornerA, tCornerB, tCornerC, tCornerD;
            // bl, tl, tr, br
            tCornerA = node.data.position.clone();
            tCornerB = Vec2.add(node.data.position, new Vec2(0, node.data.bounds.y));
            tCornerC = Vec2.add(node.data.position, node.data.bounds);
            tCornerD = Vec2.add(node.data.position, new Vec2(node.data.bounds.x, 0));

            // For the float precition errors we have to make the character a little bit smaller.
            oCornerA.add(new Vec2(0.5f,0.5f));
            oCornerB.add(new Vec2(0.5f,-0.5f));
            oCornerC.add(new Vec2(-0.5f,-0.5f));
            oCornerD.add(new Vec2(-0.5f,0.5f));

            // We have to calculate the signed distance to every point around them.
            // We have to use the signed distance here, thats just that direction vectors "coordinate"
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
        }

        // down left up right
        return output;
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
