package com.tda367.infinityrun.Roomtemplates;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomUDL  extends RoomTemplate {
    public RoomUDL() {
        u =true;
        r=false;
        d=true;
        l=true;
        roomExits = 3;
    }

}

/*
    int block = 64;
        for (int i = 0; i < 5; i++)

        {
        for (int j = 0; j < 5; j++){

        //bottom left bricks
        addWorldObject(new BrickObject(new Vec2(0+i*64, 0+j*64)));
        addWorldObject(new BrickObject(new Vec2(5*block+i*block,0*block+j*block )));
        // top left bricks
        addWorldObject(new BrickObject(new Vec2(0*block+i*64, 10*block+j*64)));
        addWorldObject(new BrickObject(new Vec2(5*block+i*64, 10*block+j*64)));


        //right wall of bricks
        addWorldObject(new BrickObject(new Vec2(15*block+i*64, 0+j*64)));
        addWorldObject(new BrickObject(new Vec2(15*block+i*64, 5*block+j*64)));
        addWorldObject(new BrickObject(new Vec2(20*block+i*64, 0+j*64)));
        addWorldObject(new BrickObject(new Vec2(20*block+i*64, 5*block+j*64)));
        addWorldObject(new BrickObject(new Vec2(20*block+i*64, 10*block+j*64)));
        addWorldObject(new BrickObject(new Vec2(15*block+i*64, 10*block+j*64)));
        addWorldObject(new BrickObject(new Vec2(15*block+i*64, 10+j*64)));


        //central platforms
        addWorldObject(new Platform(new Vec2(13*block, 7*block+16)));
        addWorldObject(new Platform(new Vec2(10*block, 10*block)));
        //todo fall through platforms
        addWorldObject(new Platform(new Vec2(10*block, 13*block+16)));
        addWorldObject(new Platform(new Vec2(12*block, 13*block+16)));
        addWorldObject(new Platform(new Vec2(12*block, 13*block+16)));

        //fall tghough again
        addWorldObject(new Platform(new Vec2(13*block, 3*block+16)));
        addWorldObject(new Platform(new Vec2(10*block, 1*block+16)));
        addWorldObject(new Platform(new Vec2(12*block, 1*block+16)));


        }
        }*/