package com.tda367.infinityrun.RoomTiles;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldObject;

/**
 * Created by kaffe on 5/16/17.
 */

    public class Verticalplatform extends WorldObject {
        private int width = 128;
        private int height = 16;

        // generic platform is 128 units high and 16 wide
        // we should rewrite this together with worldobject once we get a
        // better picture of how we want worldobjects to work

        public Verticalplatform(Vec2 position) {
            super(position, new Vec2(16, 128));
            setTexture("WorldObjects/platform.png");

        }
    }
