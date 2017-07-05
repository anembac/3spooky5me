package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

class WeaponDagger extends MeleeWeapon {
    public WeaponDagger(double meleeHandling, double criticalChance, double criticalDamage) {
        super(6, 0.5, 0.5);
        damage *= meleeHandling;
        criticalHitChance = criticalChance + 0.15;
        criticalHitDamage = criticalDamage * 1.4;
    }

}
