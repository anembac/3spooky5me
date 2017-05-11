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
    protected int health = 102;
    protected int speed = 360; //This speed does not equal 1 block per second, speed is arbitary
    protected int jumpHeight = 100;
    protected int numberOfJumps = 1;
    protected boolean damageable = true;
    protected boolean isJumping = false;


    public final int getHealth() {
        return health;
    }

    public final int getSpeed() {
        return speed;
    }

    public final boolean getDamageable(){
        return damageable;
    }



    public final Vec2 getPosition(){

        return position;
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
        if(state.forwardPressed()) acceleration.x += speed/6;
        if(state.backPressed()) acceleration.x -= speed/6;
        if(!state.backPressed() && !state.forwardPressed())
        {
            if(this.acceleration.x < -0.000001)
            {
                acceleration.x += speed/4;
                if(acceleration.x> 0) acceleration.x = 0;
            }
            else
            {
                acceleration.x -= speed/4;
                if(acceleration.x < 0) acceleration.x = 0;
            }
        }

        //float height = CollisionManager.getInstance().getWalkableHeight(this);
        if(state.jumpPressed())
        {
            if(position.y < height+0.001)
            {// not flying
                acceleration.y += 500;
            }
        }
        /////////////////////////////////////////////////////////


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
        acceleration.y = Utils.limit(-100000, acceleration.y, 500);

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