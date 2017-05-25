package com.tda367.infinityrun.WeaponTypes;

import com.tda367.infinityrun.MeleeWeapon;
import com.tda367.infinityrun.Upgrades.CriticalHitChance;

/**
 * Created by Jacob on 5/9/2017.
 */

public class Axe extends MeleeWeapon {
    public Axe(double meleeHandling, double criticalChance, double criticalDamage) {
        super(15,1.5,1);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;

    }
}
