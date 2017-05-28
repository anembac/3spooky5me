package com.tda367.infinityrun.WeaponTypes;
import com.tda367.infinityrun.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

class Mace extends MeleeWeapon {
    public Mace(double meleeHandling, double criticalChance, double criticalDamage) {
        super(10, 1.5, 1);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
    }
}
