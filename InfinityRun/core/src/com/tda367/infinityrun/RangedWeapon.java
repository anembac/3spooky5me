package com.tda367.infinityrun;

/**
 * Created by Mikael on 5/22/2017.
 */
public class RangedWeapon {
    protected final double damage;
    protected final double CD;
    protected final double range;

    public RangedWeapon (int damage, double CD, double range) {
        this.damage = damage;
        this.CD = CD;
        this.range = range;
    }

    public double getDamage(){
        return damage;
    }

    public double getCD(){
        return CD;
    }

    public double getRange(){
        return range;
    }
}
