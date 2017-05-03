package com.tda367.infinityrun;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;

/**
 * Created by kaffe on 4/3/17.
 */
public class    Character extends WorldObject {

    public Character(Vector2 position){
        super(position);
        new Rectangle(position.x, position.y, size, size);
    }
    private int size = 64;
    protected int health = 102;
    protected int speed = 10; //This speed does not equal 1 block per second, speed is arbitary
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



    public final Vector2 getPosition(){

        return position;
    }


    public final void moveXPosition(Direction dir){
        switch(dir){
            case LEFT:
                this.position.x-=speed;
                break;
            case RIGHT:
                this.position.x +=speed;
                break;
        }
    }

    public final void jump(){
        if(!isJumping){
            this.position.y=+jumpHeight;
            setJumping(true);
        }

    }

    public final boolean getJumping(){
        return isJumping;
    }

    public final void setJumping(boolean b){
        isJumping = b;
    }
}
// reworked into libgdx positions with vectors