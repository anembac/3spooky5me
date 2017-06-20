package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Controller.InputState;
import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;

/**
 * Created by miktor on 2017-04-03.
 */
public class Enemy extends LivingObject {


    public Enemy(Vec2 position, Vec2 bounds, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl,
                 int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl) {
        super(position, bounds, speedLvl, jumpLvl, hermesLvl, healthLvl, meleeHandlingLvl, ChcLvl, Chdlvl, regLvl);
        setTexture("WorldObjects/enemy.png");
    }


    @Override
    public void frame(float dt, float heroX, float heroY, InputState input) {
        float cx = Utils.getCenter(getPosition(), getDrawingRect().bounds).x;
        float cy = Utils.getCenter(getPosition(), getDrawingRect().bounds).y;
        boolean r = false, l = false, jump = false, attack = false;
        if (cx + 90 < heroX) r = true;
        else if (cx - 90 > heroX) l = true;
        if (heroY - 90 > cy) jump = true;
        //if(acceleration.x > -1 && acceleration.y < 1) jump = true;
        if (Math.sqrt((cx - heroX) * (cx - heroX) + (cy - heroY) * (cy - heroY)) < 100) attack = true;

        InputState state = new InputState(r, l, jump, attack);

        super.frame(dt, heroX, heroY, state);
    }
}
