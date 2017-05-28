package com.tda367.infinityrun.WeaponTypes;

import com.tda367.infinityrun.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

class WeaponSpear extends MeleeWeapon {
    public WeaponSpear(double meleeHandling, double criticalChance, double criticalDamage) {
        super(15, 1.5, 1.5);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage * 1.2;
    }

}
