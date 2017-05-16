package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;
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
        int block = 64;
        addWorldObject(new BrickObject(new Vec2(0, block)));
        addWorldObject(new SpikeObject(new Vec2(block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (1.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(2 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (2.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(3 * block, block)));
        addWorldObject(new BrickObject(new Vec2((int) (3.5 * block), block)));
        addWorldObject(new BrickObject(new Vec2(4 * block, block)));
        addWorldObject(new BrickObject(new Vec2(5 * block, block)));
        addWorldObject(new SpikeObject(new Vec2(6 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (6.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(7 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (7.5 * block), block)));
        addWorldObject(new BrickObject(new Vec2(8 * block, block)));
        addWorldObject(new BrickObject(new Vec2(9 * block, block)));
        addWorldObject(new BrickObject(new Vec2(10 * block, block)));
        addWorldObject(new BrickObject(new Vec2(11 * block, block)));
        addWorldObject(new BrickObject(new Vec2(12 * block, block)));
        addWorldObject(new BrickObject(new Vec2(13 * block, block)));
        addWorldObject(new BrickObject(new Vec2(14 * block, block)));
        addWorldObject(new SpikeObject(new Vec2(15 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (15.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(16 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (16.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(17 * block, block)));
        addWorldObject(new BrickObject(new Vec2(17 * block, 3*block)));
        addWorldObject(new SpikeObject(new Vec2((int) (17.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(18 * block, block)));
        addWorldObject(new BrickObject(new Vec2(18 * block, 3* block)));
        addWorldObject(new SpikeObject(new Vec2((int) (18.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(19 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (19.5 * block), block)));
        addWorldObject(new SpikeObject(new Vec2(20 * block, block)));
        addWorldObject(new SpikeObject(new Vec2((int) (20.5 * block), block)));
        addWorldObject(new BrickObject(new Vec2(21 * block, block)));
        addWorldObject(new BrickObject(new Vec2(22 * block, block)));
        addWorldObject(new BrickObject(new Vec2(23 * block, block)));
        addWorldObject(new BrickObject(new Vec2(24 * block, block)));

        for (int i = 0; i < 50; i++) { //Should be 25 for a standard room - ordinarie room is 25*20
            for (int j = 0; j < 8; j++) {
                addWorldObject(new BrickObject(new Vec2(block * i, 0 - block * j))); //Creates Floor
                addWorldObject(new BrickObject(new Vec2(block * i, 14*block + block * j))); //Creates Roof
            }
        }
        /*

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
    }*/
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

    public void addHero(WorldObject obj) {
        worldObjects.add(obj);
    }

}
