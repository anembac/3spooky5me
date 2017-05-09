package com.tda367.infinityrun;

/**
 * Created by Jacob on 5/9/2017.
 */

public class MeleeWeapon {
     protected final double damage;
     protected final double CD;
     protected final double range;

     public MeleeWeapon (int damage, double CD, double range) {
         this.damage = damage;
         this.CD = CD;
         this.range = range;
     }

     public double getDamage(){
         return damage;
     }

     public double getCD(){
         return CD;
     }

     public double getRange(){
         return range;
     }
}
