package com.tda367.infinityrun.Roomtemplates;

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

    /*
            int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 14; y++) {
                addWorldObject(new BrickObject(new Vec2(x*block,y*block)));
                addWorldObject(new BrickObject(new Vec2((24-x)*block,y*block)));
            }
        }
     */
}
