package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;/*

        Dagger is a melee weapon with high attack rate, and critical hits, but low damage.

 */

import static com.tda367.infinityrun.Utils.Constants.meter;

public class WeaponDagger extends MeleeWeapon {
    public WeaponDagger(double meleeHandling, double criticalChance, double criticalDamage) {
        super(6, 0.3, 0.5);
        name = "Dagger";
        damage *= meleeHandling * 0.95;
        criticalHitChance = criticalChance + 0.15;
        criticalHitDamage = criticalDamage * 1.4;
        setWeaponThickness(meter/5);
    }

}
