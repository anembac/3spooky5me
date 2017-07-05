package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;

/*

        Dagger is a melee weapon with high attack rate, and critical hits, but low damage.

 */

public class WeaponDagger extends MeleeWeapon {
    public WeaponDagger(double meleeHandling, double criticalChance, double criticalDamage) {
        super(6, 0.5, 0.5);
        damage *= meleeHandling * 0.95;
        criticalHitChance = criticalChance + 0.15;
        criticalHitDamage = criticalDamage * 1.4;
    }

}
