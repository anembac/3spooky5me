package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Rect;
import com.tda367.infinityrun.Math.Vec2;


import java.util.ArrayList;
import java.util.List;

public abstract class WorldObject { //We should probably refactor this class at some point



    // position is for some reason a vector, deal w/it
    Vec2 position = new Vec2(0,0);
    Vec2 bounds = new Vec2(0,0);
    private WorldObject parent = null;
    private String texturename = "";
    private final List<WorldObject> children = new ArrayList<WorldObject>();
    private boolean collidable = true;
    private boolean despawn = false;

    protected WorldObject(Vec2 position, Vec2 bounds)
    {
        this.position = position;
        this.bounds = bounds;
    }

    void despawn()
    {
        despawn = true;
    }

    public boolean getDespawn()
    {
        return despawn;
    }

    public boolean getCollidable()
    {
        return collidable;
    }

    protected void setCollidable(boolean col)
    {
        collidable = col;
    }

    WorldObject(Vec2 pos, Vec2 bound, WorldObject parent)
    {
        position = pos;
        bounds = bound;
        this.parent = parent;
    }

    public void frame(float dt, float heroX, float heroY, InputState state){}

    private void setParent(WorldObject parent)
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

    void addChildren(WorldObject object)
    {
        object.setParent(this);
        children.add(object);
    }

    void removeChildren(WorldObject object)
    {
        if(children.contains(object))
        {
            children.remove(object);
        }
    }

    public List<WorldObject> getChildren()
    {
        return children;
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

    public WorldObject getParent()
    {
        return parent;
    }

    public Rect getDrawingRect()
    {
        if(parent == null) return new Rect(position, bounds);
        else return new Rect(Vec2.add(position, parent.getDrawingRect().getBottomLeft()), bounds);
    }
}
