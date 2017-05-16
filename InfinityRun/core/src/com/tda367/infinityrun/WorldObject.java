package com.tda367.infinityrun;

/**
 * Created by miktor on 2017-04-03.
 */
import com.badlogic.gdx.math.Vector2;
import com.tda367.infinityrun.Math.Rect;
import com.tda367.infinityrun.Math.Vec2;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class WorldObject { //We should probably refactor this class at some point



    // position is for some reason a vector, deal w/it
    protected Vec2 position = new Vec2(0,0);
    protected Vec2 bounds = new Vec2(0,0);
    private WorldObject parent = null;
    private String texturename = "";
    private List<WorldObject> children = new ArrayList<WorldObject>();


    public WorldObject(Vec2 position, Vec2 bounds)
    {
        this.position = position;
        this.bounds = bounds;
    }

    public WorldObject(Vec2 pos, Vec2 bound, WorldObject parent)
    {
        position = pos;
        bounds = bound;
        this.parent = parent;
    }

    public void frame(float dt, InputState state){}

    public void setParent(WorldObject parent)
    {
        this.parent = parent;
    }

    protected void setTexture(String s)
    {
        texturename = s;
    }

    public String getTexturename()
    {
        return texturename;
    }

    public void addChildren(WorldObject object)
    {
        object.setParent(object);
        children.add(object);
    }

    public void removeChildren(WorldObject object)
    {
        children.remove(object);
    }

    public Vec2 getPosition()
    {
        if(parent == null) return position.clone();
        else
        {
            Vec2 parentPos = parent.getPosition();
            return new Vec2(position.x + parentPos.x, position.y + parentPos.y);
        }
    }

    public Rect getDrawingRect()
    {
        if(parent == null) return new Rect(position, bounds);
        else return new Rect(Vec2.add(position, parent.getDrawingRect().getBottomLeft()), bounds);
    }
}
