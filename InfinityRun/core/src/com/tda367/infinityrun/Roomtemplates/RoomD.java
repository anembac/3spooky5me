package com.tda367.infinityrun.Roomtemplates;

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

                addWorldObject(new BrickObject(new Vec2(15 * block + i * block, 0 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 0 * block + j * block)));

                // roof
                addWorldObject(new BrickObject(new Vec2(0 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(5 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(10 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(15 * block + i * block, 10 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 10 * block + j * block)));
                //extra roof shit
                addWorldObject(new BrickObject(new Vec2(0 * block + i * block, 9 * block)));
                addWorldObject(new BrickObject(new Vec2(5 * block + i * block, 9 * block)));
                addWorldObject(new BrickObject(new Vec2(10 * block + i * block, 9 * block)));
                addWorldObject(new BrickObject(new Vec2(15 * block + i * block, 9 * block)));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 9 * block)));


                //right wall
                addWorldObject(new BrickObject(new Vec2(0 * block + i * block, 5 * block + j * block)));
                addWorldObject(new BrickObject(new Vec2(20 * block + i * block, 5 * block + j * block)));
            }
            }
        }
 */




