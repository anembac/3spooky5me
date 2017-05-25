package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;

import java.util.List;
import java.util.Random;

/**
 * Created by Jacob on 5/9/2017.
 */

public class MeleeWeapon extends WorldObject {
     protected  double damage;
     protected double criticalHitChance;  /// tod be fixed into final when shop is fully working
     protected double criticalHitDamage;
     protected final double CD;
     protected final double range;
     private double currentCD = 0;

     public MeleeWeapon (double damage, double CD, double range) {
         super(new Vec2(/*offset from character*/32,26), new Vec2(/*size*/164,12));
         setTexture("WorldObjects/weapon.png");
         setCollidable(false);
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

     public void setDirRight()
     {
         position.x = 32;
     }

     public  void setDirLeft()
     {
         position.x = -32;
     }

     private boolean isCritical(){
         int rnd = new Random().nextInt(100);
         return (criticalHitChance * 100) > rnd;
     }

     private double getCritDamage(){
         return damage * criticalHitDamage;
     }

    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        super.frame(dt, heroX, heroY, state);
        currentCD = Math.max(0, currentCD-dt);

        if(state.attackPressed())
        {
            // temp animation to see when we are auctually attacking
            position.y = (position.y-1) % 30 +21;
        }
        else position.y = 16.0f;

        if(state.attackPressed() && currentCD < 0.001)
        {

            List<WorldObject> output= CollisionManager.getInstance().getKNearest(this, 10); // get the 10 nearest

            for(WorldObject wo : output)
            {
                if(wo != this.getParent() && Utils.distance(this.getParent(), wo) < (range * Constants.meter) && wo instanceof LivingObject)
                {
                    if(isCritical()) {
                        ((LivingObject)wo).damage(getCritDamage());
                        System.out.println(getCritDamage() + " CRIT");
                    } else {
                        ((LivingObject) wo).damage(damage);
                    }
                    ((LivingObject)wo).acceleration.y = 400;
                    if (position.x >  0){
                    ((LivingObject)wo).acceleration.x = 700;}
                    if (position.x <  0){
                        ((LivingObject)wo).acceleration.x = -700;
                    }

                    currentCD = CD;
                }
            }
        }
    }
}
