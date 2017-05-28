package com.tda367.infinityrun.WeaponTypes;
import com.tda367.infinityrun.MeleeWeapon;

/**
 * Created by Jacob on 5/9/2017.
 */

public class Sword extends MeleeWeapon {
    public Sword(double meleeHandling, double criticalChance, double criticalDamage) {
        super(10,1,2);
        damage *= meleeHandling;
        criticalHitChance = criticalChance;
        criticalHitDamage = criticalDamage;
    }
}
