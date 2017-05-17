package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.Platform;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomUDL  extends RoomTemplate {
    public RoomUDL() {
        u = true;
        r = false;
        d = true;
        l = true;
        roomExits = 3;
        for (int i = 0; i < 5; i++)

        {
            for (int j = 0; j < 5; j++) {

                //bottom left bricks
                roomObjects.add(new BrickObject(new Vec2(0 + i * 64, 0 + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * block, 0 * block + j * block)));
                // top left bricks
                roomObjects.add(new BrickObject(new Vec2(0 * block + i * 64, 10 * block + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(5 * block + i * 64, 10 * block + j * 64)));


                //right wall of bricks
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * 64, 0 + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * 64, 5 * block + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * 64, 0 + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * 64, 5 * block + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(20 * block + i * 64, 10 * block + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * 64, 10 * block + j * 64)));
                roomObjects.add(new BrickObject(new Vec2(15 * block + i * 64, 10 + j * 64)));


                //central platforms
                roomObjects.add(new Platform(new Vec2(13 * block, 7 * block + 16)));
                roomObjects.add(new Platform(new Vec2(10 * block, 10 * block)));
                //todo fall through platforms
                roomObjects.add(new Platform(new Vec2(10 * block, 13 * block + 16)));
                roomObjects.add(new Platform(new Vec2(12 * block, 13 * block + 16)));
                roomObjects.add(new Platform(new Vec2(12 * block, 13 * block + 16)));

                //fall tghough again
                roomObjects.add(new Platform(new Vec2(13 * block, 3 * block + 16)));
                roomObjects.add(new Platform(new Vec2(10 * block, 1 * block + 16)));
                roomObjects.add(new Platform(new Vec2(12 * block, 1 * block + 16)));


            }
        }
    }
}


/*
    int block = 64;
        for (int i = 0; i < 5; i++)

        {
        for (int j = 0; j < 5; j++){

        //bottom left bricks
        roomObjects.add(new BrickObject(new Vec2(0+i*64, 0+j*64)));
        roomObjects.add(new BrickObject(new Vec2(5*block+i*block,0*block+j*block )));
        // top left bricks
        roomObjects.add(new BrickObject(new Vec2(0*block+i*64, 10*block+j*64)));
        roomObjects.add(new BrickObject(new Vec2(5*block+i*64, 10*block+j*64)));


        //right wall of bricks
        roomObjects.add(new BrickObject(new Vec2(15*block+i*64, 0+j*64)));
        roomObjects.add(new BrickObject(new Vec2(15*block+i*64, 5*block+j*64)));
        roomObjects.add(new BrickObject(new Vec2(20*block+i*64, 0+j*64)));
        roomObjects.add(new BrickObject(new Vec2(20*block+i*64, 5*block+j*64)));
        roomObjects.add(new BrickObject(new Vec2(20*block+i*64, 10*block+j*64)));
        roomObjects.add(new BrickObject(new Vec2(15*block+i*64, 10*block+j*64)));
        roomObjects.add(new BrickObject(new Vec2(15*block+i*64, 10+j*64)));


        //central platforms
        roomObjects.add(new Platform(new Vec2(13*block, 7*block+16)));
        roomObjects.add(new Platform(new Vec2(10*block, 10*block)));
        //todo fall through platforms
        roomObjects.add(new Platform(new Vec2(10*block, 13*block+16)));
        roomObjects.add(new Platform(new Vec2(12*block, 13*block+16)));
        roomObjects.add(new Platform(new Vec2(12*block, 13*block+16)));

        //fall tghough again
        roomObjects.add(new Platform(new Vec2(13*block, 3*block+16)));
        roomObjects.add(new Platform(new Vec2(10*block, 1*block+16)));
        roomObjects.add(new Platform(new Vec2(12*block, 1*block+16)));


        }
        }*/