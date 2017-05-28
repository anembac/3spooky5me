package com.tda367.infinityrun;

import com.tda367.infinityrun.Controller.InputState;
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
         setPosition(32,getNoneRelativePosition().y);
     }

     public  void setDirLeft()
     {
         setPosition(-32,getNoneRelativePosition().y);
     }


     //Checks if you will get a critical hit based on your current chance of a critical hit.
     private boolean isCritical(){
         int rnd = new Random().nextInt(100);
         return (criticalHitChance * 100) > rnd;
     }

     //Calculates the damage you will get if you get a critical strike.
     private double getCriticalDamage(){
         return damage * criticalHitDamage;
     }

    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        super.frame(dt, heroX, heroY, state);
        currentCD = Math.max(0, currentCD-dt);

        if(state.attackPressed())
        {
            // temp animation to see when we are auctually attacking
            setPosition(getNoneRelativePosition().x, (getNoneRelativePosition().y-1) % 30 +21);
        }
        else setPosition(getNoneRelativePosition().x, 16);

        if(state.attackPressed() && currentCD < 0.001)
        {
            List<WorldObject> output= CollisionManager.getInstance().getKNearest(this, 10); // get the 10 nearest
            for(WorldObject wo : output) {
                //System.out.println(WOWrapper.centerDistance(this.getParent(), wo)/10 + "wow a rapper range  " + range* Constants.meter);
                if(wo != this.getParent() && WOWrapper.centerDistance(this.getParent(), wo)/10 < (range * Constants.meter) && wo instanceof LivingObject)
                {
                    if(isCritical()) {
                        ((LivingObject)wo).damage(getCriticalDamage());
                        System.out.println("Player dealt " +  getCriticalDamage() + " CRITICAL HIT");
                    } else {
                        ((LivingObject) wo).damage(damage);
                        //System.out.println("Player dealt " +  damage);
                    }
                    ((LivingObject)wo).acceleration.y = 400;
                    if (getPosition().x >  0){
                    ((LivingObject)wo).acceleration.x = 700;
                    }
                    if (getPosition().x <  0){
                        ((LivingObject)wo).acceleration.x = -700;
                    }

                    currentCD = CD;
                }
            }
        }

    }
}
