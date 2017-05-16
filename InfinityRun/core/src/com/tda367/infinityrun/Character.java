package com.tda367.infinityrun;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.Math.Vec4;

import javax.rmi.CORBA.Util;
import java.awt.geom.Point2D;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends MovableObject {

    public Character(Vec2 position, Vec2 bounds, String texture){
        super(position, bounds);
        setTexture(texture);
        //new Rectangle(position.x, position.y, size, size);
    }
    private int size = 64;
    protected int maxHelth;
    protected int currentHealth;
    protected int speed; //This speed does not equal 1 block per second, speed is arbitary
    protected int jumpH;
    protected int coins = 0;
    protected boolean damageable = true;
    protected boolean isJumping = false;


    public int getHealth() {
        return currentHealth;
    }

    public int getMaxHelth() {return maxHelth; }

    public int getSpeed() {
        return speed;
    }

    public boolean getDamageable(){
        return damageable;
    }

    public int getCoins(){
        return coins;
    }

    public final Vec2 getPosition(){

        return position;
    }

    public void initializeHero(){
        maxHelth  = upgrades.get("Health").getValueInt();
        currentHealth = maxHelth;
        speed   = upgrades.get("Speed").getValueInt();
        jumpH   = upgrades.get("JumpH").getValueInt();
    }

    @Override
    public void frame(float dt, InputState state)
    {

        Vec4 collisionVariables = CollisionManager.getInstance().getDistanceToCollission(this);
        float height = collisionVariables.x;
        float roof = collisionVariables.z;
        float rightIntersection = collisionVariables.w;
        float leftIntersection = collisionVariables.y;

        // The piece below might be better applied in "Upgrades" as a command pattern ish that is frame based. Since this code will change depending on what upgrades we have.
        /////////////////////////////////////////////////////////
        ObjectModifiers modifier = new ObjectModifiers(this.position.y > height, acceleration);
        for(Upgrade u :upgrades.values())
        {
            u.frame(dt, state, modifier);
        }
        acceleration = modifier.acceleration.clone();

        // add acceleration down if we are in the air.
        if(this.position.y > height)
        {
            //acceleration.y -= 9.82*dt;
            // so 1 px is 1 unit here, we need to guess the pixel height of the character in meters, etc 150?!
            acceleration.y -= 9.82*dt*150;
        }
        // limit the forward acceleration
        acceleration.x = Utils.limit(-speed, acceleration.x, speed);
        // limit the "jump/gravity" acceleration.
        acceleration.y = Utils.limit(-100000, acceleration.y, jumpH);

        // move the character according to the acceleration vectors.
        position.add(Vec2.mul(acceleration, dt));

        // if we accelerated right into a "block"
        if((position.x + bounds.x) > rightIntersection)
        {
            position.x = rightIntersection - bounds.x;
            acceleration.x = 0;
        }
        // if we accelerated left into a "blobk"
        if(position.x < leftIntersection)
        {
            position.x = leftIntersection;
            acceleration.x = 0;
        }
        // if we accelerated below the ground:
        if(position.y < height)
        {
            position.y = height;
            acceleration.y = 0;
        }
        // if we jumped and hit a roof.
        if((position.y + bounds.y) > roof)
        {
            position.y = roof - bounds.y;
            acceleration.y = 0;
        }

    }






    public final boolean getJumping(){
        return isJumping;
    }

    public final void setJumping(boolean b){
        isJumping = b;
    }
}