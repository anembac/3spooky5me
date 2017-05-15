package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mikael on 5/3/2017.
 */
// I guess this class will be some kind of base for "Upgradable" objects, moving objects will probably use some kind
    // of upgrade to allow them to use the command pattern to move. RENAME to upgradableObject?
public class MovableObject extends WorldObject {
    protected HashMap<String, Upgrade> upgrades = new HashMap<String, Upgrade>(); //Holds all of the upgrades, gives them a name as index.

    protected Vec2 acceleration = new Vec2(0,0);

    public MovableObject(Vec2 position, Vec2 bounds) {
        super(position, bounds);
    }

    public MovableObject(Vec2 pos, Vec2 bound, WorldObject parent) {
        super(pos, bound, parent);
    }

    @Override
    public void frame(float dt, InputState state){


    }

    public void addUpgrade(String name, Upgrade upg)
    {
        upgrades.put(name, upg);
    }
}
