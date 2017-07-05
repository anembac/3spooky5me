package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Controller.InputState;
import com.tda367.infinityrun.Utils.Utils;
import com.tda367.infinityrun.Utils.Math.Vec2;

/**
 * Created by miktor on 2017-04-03.
 */
public class Enemy extends LivingObject {
        //An Enemy is a LivingObject with a simple ai features that attempt to attack the player. Their stats are dependent upon the worldGeneration, as their difficulty increases with
        //Progress in the game. Despite being similar to a player, they are barred from things like menu/shop/pausing.

    public Enemy(Vec2 position, Vec2 bounds, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl,
                 int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl) {
        super(position, bounds, speedLvl, jumpLvl, hermesLvl, healthLvl, meleeHandlingLvl, ChcLvl, Chdlvl, regLvl);
        setTexture("WorldObjects/enemy.png");
    }



    //simple commands to make the Enemy always path towards the player.
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

        InputState state = new InputState(r, l, jump, attack, false, false);

        super.frame(dt, heroX, heroY, state);
    }
}
