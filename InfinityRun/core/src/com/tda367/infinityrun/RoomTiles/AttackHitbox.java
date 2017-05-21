package com.tda367.infinityrun.RoomTiles;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldObject;

/**
 * Created by kaffe on 5/21/17.
 */
public class AttackHitbox extends WorldObject {
        //Generic Brick, 64x64 size.
    boolean directionRight = true;

        public AttackHitbox(Vec2 position) {
            super(position, new Vec2(64, 64));
            if (directionRight) {
                setTexture("WorldObjects/SwordRight.png");
            }
            else{
                setTexture("worldObjects/SwordLeft.png");
            }
        }

    }

