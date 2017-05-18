package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.Platform;
import com.tda367.infinityrun.RoomTiles.SpikeObject;
import com.tda367.infinityrun.Roomtemplates.LogicalMapper;
import com.tda367.infinityrun.Roomtemplates.RoomTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 4/3/2017.
 */
public class World {

    private List<WorldObject> worldObjects;
    private KdTree<WorldObject> kdTree = new KdTree<WorldObject>();
    private double difficulty = 1.0;
    IInput input = null;
    private LogicalMapper logicalMapper = new LogicalMapper();

    public void increaseDifficulty(double difficulty) {
        this.difficulty = difficulty + 0.05;
    }

    public World() {
        worldObjects = new ArrayList<WorldObject>();
        input = new Input();
    }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }

    public void generateWorld() {
        for(WorldObject w:logicalMapper.mapper()){
            addWorldObject(w);
        }
        }



    public void URDL() {
        for (int i = 0; i < 5; i++) {
            addWorldObject(new Platform(new Vec2(i * 128, 1024)));
        }
    }


    public void frame(float dt) {
        input.collectInput();
        for (WorldObject obj : worldObjects) {
            obj.frame(dt, input.getInput());
        }
    }

    // to add the player etc to the world.
    public void addWorldObject(WorldObject obj) {
        //for(WorldObject obj:roomObjects) {
        worldObjects.add(obj);
        CollisionManager.getInstance().addWorldObject(obj);
        //}
    }

    public void addRoom (ArrayList<WorldObject> objects) {
        for(WorldObject w:objects){
            addWorldObject(w);
        }
    }

    public void addHero(WorldObject obj) {
        worldObjects.add(obj);
    }

}
