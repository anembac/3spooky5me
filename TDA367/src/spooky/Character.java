package spooky;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends WorldObject {

    protected int health = 100;
    protected int speed = 100; //This speed equals 1 block per second.
    protected boolean damageable = true;


    private final int getHealth() {
        return health;
    }

    private final int getSpeed() {
        return speed;
    }

    private final boolean getDamageable(){
        return damageable;
    }


}
