package com.tda367.infinityrun;

/**
 * Created by miktor on 2017-04-03.
 */

import com.tda367.infinityrun.Controller.InputState;
import com.tda367.infinityrun.Math.Rect;
import com.tda367.infinityrun.Math.Vec2;


import java.util.ArrayList;
import java.util.List;

public abstract class WorldObject { //We should probably refactor this class at some point


    // position is for some reason a vector, deal w/it
    private Vec2 position = new Vec2(0, 0);
    private Vec2 bounds = new Vec2(0, 0);
    private WorldObject parent = null;
    private String texturename = "";
    private List<WorldObject> children = new ArrayList<WorldObject>();
    private boolean collidable = true;
    private boolean despawn = false;

    public WorldObject(Vec2 position, Vec2 bounds) {
        this.position = position;
        this.bounds = bounds;
    }

    public void despawn() {
        despawn = true;
    }

    public boolean getDespawn() {
        return despawn;
    }

    public boolean getCollidable() {
        return collidable;
    }

    public void setCollidable(boolean col) {
        collidable = col;
    }

    public WorldObject(Vec2 pos, Vec2 bound, WorldObject parent) {
        position = pos;
        bounds = bound;
        this.parent = parent;
    }

    public void frame(float dt, float heroX, float heroY, InputState state) {
    }

    public void setParent(WorldObject parent) {
        this.parent = parent;
    }

    protected void setTexture(String s) {
        texturename = s;
    }

    public String getTexturename() {
        return texturename;
    }

    public void addChildren(WorldObject object) {
        object.setParent(this);
        children.add(object);
    }

    public void removeChildren(WorldObject object) {
        if (children.contains(object)) {
            children.remove(object);
        }
    }


    public void setPosition(float x, float y) {
        this.position = new Vec2(x, y);
    }

    public void setPosition(Vec2 v) {
        this.position = v.clone();
    }

    public List<WorldObject> getChildren() {
        return children;
    }

    public Vec2 getNoneRelativePosition() {
        return position.clone();
    }

    public Vec2 getPosition() {
        if (parent == null) return position.clone();
        else {
            Vec2 parentPos = parent.getPosition();
            return new Vec2(position.x + parentPos.x, position.y + parentPos.y);
        }
    }

    public WorldObject getParent() {
        return parent;
    }

    public Rect getDrawingRect() {
        if (parent == null) return new Rect(position, bounds);
        else return new Rect(Vec2.add(position, parent.getDrawingRect().getBottomLeft()), bounds);
    }
}
