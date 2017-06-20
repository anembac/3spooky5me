package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

class WeaponMace extends MeleeWeapon {
    public WeaponMace(double meleeHandling, double criticalChance, double criticalDamage) {
        super(10, 1.5, 1);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
    }
}
