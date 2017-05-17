package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomL extends  RoomTemplate {
    public RoomL() {
        u =false;
        r=false;
        d=false;
        l=true;
        roomExits = 1;
    }

}
/*
    public void generateWorld() {
        int block = 64;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                //floor
                addWorldObject(new BrickObject(new Vec2(0 * block + i * block, 0 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(5 * block + i * block, 0 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(10 * block + i * block, 0 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(15 * block + i * block, 0 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 0 * block + j * block)));

                // roof
                addWorldObject(new BrickObject(new Vec2(0 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(5 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(10 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(15 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 10 * block + j * block)));
                //extra roof shit
                addWorldObject(new BrickObject(new Vec2(0 * block + i * block, 9 * block )));
                addWorldObject(new BrickObject(new Vec2(5 * block + i * block, 9 * block )));
                addWorldObject(new BrickObject(new Vec2(10 * block + i * block, 9 * block )));
                addWorldObject(new BrickObject(new Vec2(15 * block + i * block, 9 * block )));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 9 * block )));

                */