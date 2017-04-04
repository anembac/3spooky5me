package spooky;

/**
 * Created by miktor on 2017-04-03.
 */
public class Hazard extends WorldObject{

    protected int damage = 0;
    protected int health = 0;
    protected boolean damagable = false;

    public Hazard(){}

    public Hazard(int damage, int health, boolean damagable)
    {
        this.damage = damage;
        this.damagable = damagable;
        this.health = health;
    }

    private final boolean getDamagable()
    {
        return damagable;
    }

    private final int getHealth()
    {
        return health;
    }

    private final int getDamage()
    {
        return damage;
    }

    @Override
    public void frame(double dt) {
        super.frame(dt);
    }

    @Override
    public void render() {
        super.render();
    }
}
