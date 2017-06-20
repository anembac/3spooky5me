package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

class WeaponAxe extends MeleeWeapon {
    public WeaponAxe(double meleeHandling, double criticalChance, double criticalDamage) {
        super(15, 1.5, 1);
        damage *= meleeHandling;
        criticalHitChance = criticalChance + 0.05;
        criticalHitDamage = criticalDamage * 1.05;

    }
}
