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

    protected boolean u;
    protected boolean d;
    protected boolean l;
    protected boolean r;
    protected int roomExits;
    protected ArrayList<WorldObject> roomObjects = new ArrayList<WorldObject>();
    protected int block = 64;

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

    public void addRoomObjects(int x, int y) {

    }
}

