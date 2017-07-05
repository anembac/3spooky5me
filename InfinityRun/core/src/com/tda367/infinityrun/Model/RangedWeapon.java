package com.tda367.infinityrun.Model;

/*
todo
 */
class RangedWeapon {
    private final double damage;
    private final double CD;
    private final double range;

    public RangedWeapon(int damage, double CD, double range) {
        this.damage = damage;
        this.CD = CD;
        this.range = range;
    }

    public double getDamage() {
        return damage;
    }

    public double getCD() {
        return CD;
    }

    public double getRange() {
        return range;
    }
}
