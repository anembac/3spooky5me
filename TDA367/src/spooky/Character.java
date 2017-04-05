package spooky;

import java.awt.geom.Point2D;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends WorldObject {

    protected int health = 100;
    protected int speed = 100; //This speed equals 1 block per second.
    protected boolean damageable = true;


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
}
