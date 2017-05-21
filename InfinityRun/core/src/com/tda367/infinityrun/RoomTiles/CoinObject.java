package com.tda367.infinityrun.RoomTiles;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldObject;

/**
 * Created by kaffe on 5/17/17.
 */
public class CoinObject extends WorldObject {
    private  int damage = 10;
    //Generic coin,64x64 size.

    public CoinObject(Vec2 position) {
        super(position, new Vec2(64, 64));
        setTexture("WorldObjects/coin.png");
        setCollidable(false);

    }
}

