package com.tda367.infinityrun;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;

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
    protected int speed = 60; //This speed does not equal 1 block per second, speed is arbitary
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

        float height = CollisionManager.getInstance().getWalkableHeight(this);
        if(state.jumpPressed())
        {
            if(position.y < height+0.001)
            {// not flying
                acceleration.y += 500;
            }
        }

        if(this.position.y > height)
        {
            //acceleration.y -= 9.82*dt;
            // so 1 px is 1 unit here, we need to guess the pixel height of the character in meters, etc 150?!
            acceleration.y -= 9.82*dt*150;
        }

        acceleration.x = Utils.limit(-speed, acceleration.x, speed);
        position.add(Vec2.mul(acceleration, dt));

        // if we accelerated below the ground:
        if(position.y < height)
        {
            position.y = height;
            acceleration.y = 0;
        }

        //System.out.println(acceleration.y);
    }
   /* public final void moveXPosition(Direction dir){
        // I made acceleration from speed, acc = speed/4 atm as an example, i think we would like to have some kind of acceleration right?
        switch(dir){
            case LEFT:
                //this.position.x-=speed;
                this.acceleration.x -= speed/4;
                if(this.acceleration.x < -speed) this.acceleration.x = -speed;
                break;
            case RIGHT:
                //this.position.x +=speed;
                this.acceleration.x += speed/4;
                if(this.acceleration.x > speed) this.acceleration.x = speed;
                break;
            case NONE:
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
        System.out.println(acceleration.x);
    }

    public final void jump(){
        if(!isJumping){
            this.position.y=+jumpHeight;
            setJumping(true);
        }

    }*/

    public final boolean getJumping(){
        return isJumping;
    }

    public final void setJumping(boolean b){
        isJumping = b;
    }
}
// reworked into libgdx positions with vectors