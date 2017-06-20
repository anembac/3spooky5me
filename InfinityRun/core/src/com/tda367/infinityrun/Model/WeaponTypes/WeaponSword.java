package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

public class WeaponSword extends MeleeWeapon {
    public WeaponSword(double meleeHandling, double criticalChance, double criticalDamage) {
        super(10, 1, 2);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
    }
}
