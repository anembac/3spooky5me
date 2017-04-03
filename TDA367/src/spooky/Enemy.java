package spooky;

/**
 * Created by miktor on 2017-04-03.
 */
public class Enemy extends Hazard {

    public Enemy(int damage, int health, boolean damagable)
    {
        super.damage = damage;
        this.damagable = damagable;
        this.health = health;
    }

    @Override
    public void frame() {
        super.frame();
    }

    @Override
    public void render() {
        super.render();
    }
}
