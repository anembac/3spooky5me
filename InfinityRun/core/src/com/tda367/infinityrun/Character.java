package com.tda367.infinityrun;

import java.awt.geom.Point2D;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends WorldObject {

    public Character(){
    }

    protected int health = 101;
    protected int speed = 100; //This speed equals 1 block per second.
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

    public final Point2D.Double characterMove(){
        position.x=position.getX()+1*(speed/100);
        return position;
    }

    public final Point2D.Double getPosition(){

        return position;
    }


    public final void moveXPosition(Direction dir){
        switch(dir){
            case LEFT:
                this.position = new Point2D.Double(getPosition().getX()-speed, getPosition().getY());
                break;
            case RIGHT:
                this.position = new Point2D.Double(getPosition().getX()+speed, getPosition().getY());
                break;
        }
    }

    public final void jump(){
        if(!isJumping){
            this.position = new Point2D.Double(getPosition().getX(), getPosition().getY()+100);
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
