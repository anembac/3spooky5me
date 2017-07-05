package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

import static com.tda367.infinityrun.Utils.Constants.meter;


// Generic coin object for earning money. Since it will be collected upon collision, it does not act as a wall.

public class CoinObject extends WorldObject {
    //Generic coin,64x64 size.

    public CoinObject(Vec2 position) {
        super(position, new Vec2(meter, meter));
        setTexture("WorldObjects/coin.png");
        setCollidable(false);

    }
}

