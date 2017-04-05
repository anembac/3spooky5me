package spooky;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends WorldObject{

    protected int health = 1;
    protected int speed = 1;
    protected boolean damagable = false;


    private final int getHealth()
    {
        return health;
    }

    private final int getSpeed()
    {
        return speed;
    }

}
