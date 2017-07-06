package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;

/*

    Axe is a meleeWeapon with increased critical hits, and higher base damage.

 */

public class WeaponAxe extends MeleeWeapon {
    public WeaponAxe(double meleeHandling, double criticalChance, double criticalDamage) {
        super(15, 1.5, 1);
        name = "Axe";
        damage *= meleeHandling;
        criticalHitChance = criticalChance + 0.05;
        criticalHitDamage = criticalDamage * 1.15;

    }
}
