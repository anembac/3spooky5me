package com.tda367.infinityrun;

/**
 * Created by miktor on 2017-04-03.
 */
public class Enemy extends Hazard {

    public Enemy(int damage, int health, boolean damageable)
    {
        super.damage = damage;
        this.damageable = damageable;
        this.health = health;
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
