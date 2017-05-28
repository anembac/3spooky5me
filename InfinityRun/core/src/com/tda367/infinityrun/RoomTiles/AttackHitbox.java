package com.tda367.infinityrun.RoomTiles;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldObject;

/**
 * Created by kaffe on 5/21/17.
 */
class AttackHitbox extends WorldObject {

    public AttackHitbox(Vec2 position) {
            super(position, new Vec2(64, 64));
        boolean directionRight = true;
        if (directionRight) {
                setTexture("WorldObjects/SwordRight.png");
            }
            else{
                setTexture("worldObjects/SwordLeft.png");
            }
        }

    }

