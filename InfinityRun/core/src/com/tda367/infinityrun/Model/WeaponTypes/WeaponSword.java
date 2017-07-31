package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;/*

    Sword is a all-around good MeleeWeapon. Not too good in any spot, but no downsides.

 */

import static com.tda367.infinityrun.Utils.Constants.meter;

public class WeaponSword extends MeleeWeapon {
    public WeaponSword(double meleeHandling, double criticalChance, double criticalDamage) {
        super(12, 0.9, 1);
        name = "Sword";
        damage *= meleeHandling*1.1;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
        setWeaponThickness(meter/4);
    }
}