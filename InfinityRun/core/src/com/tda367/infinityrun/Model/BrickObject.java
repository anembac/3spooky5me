package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

import static com.tda367.infinityrun.Utils.Constants.meter;


/*Generic structural brick object with collision that extends WorldObject. Used everywhere to make walls in the game
*/
public class BrickObject extends WorldObject {
    //Generic Brick, 64x64 size.

    public BrickObject(Vec2 position) {
        super(position, new Vec2(meter, meter));
        setTexture("WorldObjects/bricktexture.png");
    }
}
