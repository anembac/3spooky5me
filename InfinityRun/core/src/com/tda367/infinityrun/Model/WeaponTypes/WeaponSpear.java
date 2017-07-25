package com.tda367.infinityrun.Model.WeaponTypes;

import com.tda367.infinityrun.Model.MeleeWeapon;/*

        Spear is a MeleeWeapon with high range but somewhat slow attacks, and higher critical rate.

 */

public class WeaponSpear extends MeleeWeapon {
    public WeaponSpear(double meleeHandling, double criticalChance, double criticalDamage) {
        super(15, 1.4, 4);
        name = "Spear";
        damage *= meleeHandling*1.1;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage * 1.25;
    }

}
