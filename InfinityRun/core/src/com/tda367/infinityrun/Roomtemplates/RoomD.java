package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomD extends  RoomTemplate {


    public RoomD() {
        u =false;
        r=false;
        d=true;
        l=false;
        roomExits = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                //floor
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 0 * block + j * block)));

                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 0 * block + j * block)));

                // roof
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(10 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 10 * block + j * block)));
                //extra roof shit
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(10 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 9 * block)));


                //right wall
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 5 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 5 * block + j * block)));
            }
        }
    }

}
/*

    public void generateWorld() {
        int block = 64;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                //floor
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 0 * block + j * block)));

                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 0 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 0 * block + j * block)));

                // roof
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(10 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 10 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 10 * block + j * block)));
                //extra roof shit
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(10 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * block, 9 * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 9 * block)));


                //right wall
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * block, 5 * block + j * block)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * block, 5 * block + j * block)));
            }
            }
        }
 */




