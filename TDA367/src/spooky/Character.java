package spooky;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends WorldObject{

    protected int health = 100;
    protected int speed = 10;
    protected boolean damagable = true;


    private final int getHealth()
    {
        return health;
    }

    private final int getSpeed()
    {
        return speed;
    }

}
