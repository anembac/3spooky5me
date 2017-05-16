package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.Platform;
import com.tda367.infinityrun.RoomTiles.SpikeObject;

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

    public void increaseDifficulty (double difficulty){
        this.difficulty = difficulty + 0.05;
    }

    public World()
    {
        worldObjects = new ArrayList<WorldObject>();
        input = new Input();
    }

    public List<WorldObject> getWorldObjects()
    {
        return worldObjects;
    }

    public void generateWorld() {
        for (int i = 0; i < 5; i++) {
            addWorldObject(new Platform(new Vec2(1 + i * 128, 512)));
            addWorldObject(new Platform(new Vec2(512, 512 + 64 * i)));
            addWorldObject(new Platform(new Vec2(512, 528 + 64 * i)));
            addWorldObject(new Platform(new Vec2(512, 544 + 64 * i)));
            addWorldObject(new Platform(new Vec2(512, 560 + 64 * i)));
            addWorldObject(new SpikeObject(new Vec2(128, 256)));




            addWorldObject(new Platform(new Vec2(1 + i * 128, 128)));


        }/*

        for(int i = 0; i < 200; i++)
        {
            addWorldObject(new Platform(new Vec2(10+i*128,10+i*16)));
        }
        addWorldObject(new Platform(new Vec2(138,10+4*16)));
        addWorldObject(new Platform(new Vec2(10+5*128,10+15*16)));
        addWorldObject(new Platform(new Vec2(10+4*128,10+15*16)));
        addWorldObject(new Platform(new Vec2(10+6*128,10+15*16)));
        addWorldObject(new Platform(new Vec2(10+5*128,10+17*16)));
        addWorldObject(new Platform(new Vec2(10+8*128,10+13*16)));
    }*/}




    public void URDL(){
        for(int i = 0; i < 5; i++){
            addWorldObject(new Platform(new Vec2(i*128, 1024)));
        }
    }


    public void frame(float dt)
    {
        input.collectInput();
        for(WorldObject obj : worldObjects)
        {
            obj.frame(dt, input.getInput());
        }
    }
    // to add the player etc to the world.
    public void addWorldObject(WorldObject obj)
    {
        //for(WorldObject obj:roomObjects) {
            worldObjects.add(obj);
            CollisionManager.getInstance().addWorldObject(obj);
        //}
    }

    public void addHero(WorldObject obj)
    {
        worldObjects.add(obj);
    }

}
