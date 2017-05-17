package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;

public class RoomURD  extends RoomTemplate {
    public RoomURD() {
        u =true;
        r=true;
        d=true;
        l=false;
        roomExits = 3;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                roomObjects.add(new BrickObject(new Vec2((24-x)*block,y*block)));
                roomObjects.add(new BrickObject(new Vec2((24-x)*block,(13-y)*block)));
            }
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 14; y++) {
                roomObjects.add(new BrickObject(new Vec2(x*block,y*block)));
            }
        }
    }
    /*
            int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                roomObjects.add(new BrickObject(new Vec2((24-x)*block,y*block)));
                roomObjects.add(new BrickObject(new Vec2((24-x)*block,(13-y)*block)));
            }
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 14; y++) {
                roomObjects.add(new BrickObject(new Vec2(x*block,y*block)));
            }
        }
     */

}