package com.tda367.infinityrun.Roomtemplates;

public class RoomURD  extends RoomTemplate {
    public RoomURD() {
        u =true;
        r=true;
        d=true;
        l=false;
        roomExits = 3;
    }
    /*
            int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                addWorldObject(new BrickObject(new Vec2((24-x)*block,y*block)));
                addWorldObject(new BrickObject(new Vec2((24-x)*block,(13-y)*block)));
            }
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 14; y++) {
                addWorldObject(new BrickObject(new Vec2(x*block,y*block)));
            }
        }
     */

}