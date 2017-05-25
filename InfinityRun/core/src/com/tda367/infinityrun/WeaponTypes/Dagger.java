package com.tda367.infinityrun.WeaponTypes;

import com.tda367.infinityrun.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

public class Dagger extends MeleeWeapon {
    public Dagger(double meleeHandling, double criticalChance, double criticalDamage) {
        super(6, 0.5, 0.5);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
    }

}
