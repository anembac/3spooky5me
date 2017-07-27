package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;/*

    Mace is a melee weapon with slow swing speed but high damage and good early critical hits.

 */

import static com.tda367.infinityrun.Utils.Constants.meter;

public class WeaponMace extends MeleeWeapon {
    public WeaponMace(double meleeHandling, double criticalChance, double criticalDamage) {
        super(14, 1.8, 1);
        name = "Mace";
        damage *= meleeHandling*1.2;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage+0.4;
        weaponThickness = meter/3;
    }
}
