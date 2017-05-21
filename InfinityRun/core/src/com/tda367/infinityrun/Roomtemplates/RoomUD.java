package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomUD extends  RoomTemplate {
    public RoomUD() {
        u =true;
        r=false;
        d=true;
        l=false;
        roomExits = 2;
    }

    public void addRoomObjects(int offsetX, int offsetY){
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 14; y++) {
                roomObjects.add(new BrickObject(new Vec2(offsetX * 25 * block + x*block,offsetY * 14 * block + y*block)));
                roomObjects.add(new BrickObject(new Vec2(offsetX * 25 * block + (24-x)*block,offsetY * 14 * block + y*block)));
            }
        }
    }
    /*
            int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 14; y++) {
                roomObjects.add(new BrickObject(new Vec2(x*block,y*block)));
                roomObjects.add(new BrickObject(new Vec2((24-x)*block,y*block)));
            }
        }
     */
}
