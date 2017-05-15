package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.CollisionManager;
import com.tda367.infinityrun.WorldObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaffe on 5/3/17.
 */
public class RoomTemplate {

    private List<WorldObject> roomObjects = new ArrayList<WorldObject>();
    public boolean u;
    public boolean d;
    public boolean l;
    public boolean r;
    public int roomExits;

    public boolean isExitup() {
        return u;
    }

    public boolean isExitdown() {
        return d;
    }

    public boolean isExitleft() {
        return l;
    }

    public boolean isExitright() {
        return r;
    }

    public List<WorldObject> getRoomObjects(){
        return roomObjects;
    }

}

