package com.tda367.infinityrun.Model;

/*


This is the basic abstract class WorldObject that every other object in the game inherits from. Since everything has a position, bounds renders, and frame functions.
This was done for the ease of extending WorldObjects for new purposes: new blocks with different properties, enemies, weapons etc.
 */

import com.tda367.infinityrun.Utils.Math.Rect;
import com.tda367.infinityrun.Utils.Math.Vec2;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class WorldObject extends Observable {


    // position and bounds are vectors.

    //These elements are all necessary for all WorldObjects
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


    //when set to true, it will be removed in the frame function, as a "cleanup"
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

    public Vec2 getNonRelativePosition() {
        return position.clone();
    }

    public Vec2 getPosition() {
        if (parent == null) return position.clone();
        else {
            Vec2 parentPos = parent.getPosition();
            return new Vec2(position.x + parentPos.x, position.y + parentPos.y);
        }
    }

    public Vec2 getBounds(){
        return bounds;
    }

    public void setBounds(Vec2 newBounds){
        bounds = newBounds;
    }

    public WorldObject getParent() {
        return parent;
    }

    public Rect getDrawingRect() {
        if (parent == null) return new Rect(position, bounds);
        else return new Rect(Vec2.add(position, parent.getDrawingRect().getBottomLeft()), bounds);
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
