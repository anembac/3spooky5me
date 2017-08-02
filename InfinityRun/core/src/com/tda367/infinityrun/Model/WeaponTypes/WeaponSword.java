package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;/*

    Sword is a all-around good MeleeWeapon. Not too good in any spot, but no downsides.

 */

import static com.tda367.infinityrun.Utils.Constants.meter;

public class WeaponSword extends MeleeWeapon {
    public WeaponSword(double meleeHandling, double criticalChance, double criticalDamage) {
        super(12, 0.6, 1.3);
        name = "Sword";
        damage *= meleeHandling*1.1;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
        knockBack = 350;
        setWeaponThickness(meter/4);
    }
}