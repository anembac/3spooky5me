package com.tda367.infinityrun.Roomtemplates;


import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.SpikeObject;

/**
 * Created by kaffe on 5/12/17.
 */
public class RoomURL extends RoomTemplate {
    public RoomURL() {
        u = true;
        r = true;
        d = false;
        l = true;
        roomExits = 3;
    }

    public void addRoomObjects(int offsetX, int offsetY) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                roomObjects.add(new BrickObject(new Vec2(offsetX * 25 * block + x * block, offsetY * 14 * block + (13 - y) * block)));
                roomObjects.add(new BrickObject(new Vec2(offsetX * 25 * block + (24 - x) * block, offsetY * 14 * block + (13 - y) * block)));

                roomObjects.add(new SpikeObject(new Vec2(offsetX * 25 * block + (12)*block, offsetY* 14 * block + 7*block )));
            }
        }
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 5; y++) {
                roomObjects.add(new BrickObject(new Vec2(offsetX * 25 * block + x * block, offsetY * 14 * block + y * block)));
            }
        }
    }

    /*

            int block = 64;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                roomObjects.add(new BrickObject(new Vec2(x * block, (13 - y) * block)));
                roomObjects.add(new BrickObject(new Vec2((24 - x) * block, (13 - y) * block)));
            }
        }

        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 5; y++) {
                roomObjects.add(new BrickObject(new Vec2(x * block, y * block)));
            }
        }
     */
}
