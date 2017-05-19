package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;

/**
 * Created by miktor on 2017-04-03.
 */
public class Enemy extends MovableObject {


    public Enemy(Vec2 position, Vec2 bounds, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl, int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl) {
        super(position, bounds, speedLvl, jumpLvl, hermesLvl, healthLvl, meleeHandlingLvl, ChcLvl, Chdlvl, regLvl);
        setTexture("WorldObjects/enemy.png");
    }

    @Override
    public void frame(float dt, InputState input) {
        InputState state = new InputState(true, false,false,false);
        super.frame(dt, state);
    }
}
