package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.Platform;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomU extends RoomTemplate {

    public RoomU() {
        u = true;
        r = false;
        d = true;
        l = false;
        roomExits = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {


                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(10 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 0 * block + j * block)));


                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 5 * block + j * block)));


                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 10 * block + j * block)));

                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 5 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 10 * block + j * block)));


                roomObjects.add(new Platform(new Vec2(12 * block, 12 * block)));
                roomObjects.add(new Platform(new Vec2(13 * block, 10 * block)));


            }

        }
    }

}
/*
    public void generateWorld() {
        int block = 64;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {


                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(10 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 0 * block + j * block)));


                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 5 * block + j * block)));


                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 10 * block + j * block)));

                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 5 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 10 * block + j * block)));


                roomObjects.add(new Platform(new Vec2(12 * block, 12 * block)));
                roomObjects.add(new Platform(new Vec2(13 * block, 10 * block)));


            }

        }      }
    }

*/


