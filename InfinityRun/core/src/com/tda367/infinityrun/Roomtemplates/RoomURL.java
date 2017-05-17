package com.tda367.infinityrun.Roomtemplates;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomURL extends RoomTemplate {
    public RoomURL() {
        u =true;
        r=true;
        d=false;
        l=true;
        roomExits = 3;
    }


    /*
            int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                addWorldObject(new BrickObject(new Vec2(x * block, (13 - y) * block)));
                addWorldObject(new BrickObject(new Vec2((24 - x) * block, (13 - y) * block)));
            }
        }

        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 5; y++) {
                addWorldObject(new BrickObject(new Vec2(x * block, y * block)));
            }
        }
     */
}
