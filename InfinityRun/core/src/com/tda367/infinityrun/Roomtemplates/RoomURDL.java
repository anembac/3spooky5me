package com.tda367.infinityrun.Roomtemplates;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomURDL extends RoomTemplate {
    public RoomURDL() {
        u =true;
        r=true;
        d=true;
        l=true;
        roomExits = 4;
    }
    /*
    int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
            addWorldObject(new BrickObject(new Vec2(x*block,y*block)));
            addWorldObject(new BrickObject(new Vec2(x*block,(13-y)*block)));
            addWorldObject(new BrickObject(new Vec2((24-x)*block,y*block)));
            addWorldObject(new BrickObject(new Vec2((24-x)*block,(13-y)*block)));
        }
    }
    */

}
