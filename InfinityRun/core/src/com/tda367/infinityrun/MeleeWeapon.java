package com.tda367.infinityrun;

/**
 * Created by Jacob on 5/9/2017.
 */

public class MeleeWeapon {
     protected final int damage;
     protected final double cd;
     protected final double range;

     public MeleeWeapon (int damage, double cd, double range) {
         this.damage = damage;
         this.cd = cd;
         this.range = range;
     }
}
