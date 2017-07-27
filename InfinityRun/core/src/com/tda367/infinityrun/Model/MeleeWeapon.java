package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Utils.Math.Vec4;

import static com.tda367.infinityrun.Utils.Constants.meter;


/*
MeleeWeapon is an extension of Worldobjects, as it has basic properties of one (intersections, positions, etc) but it's main purpose is to be "attached" to a LivingObject
this gives them a source of attack to harm other LivingObjects.
 */
//todo different knockback for different weapons
public class MeleeWeapon extends WorldObject {
    protected String name;
    protected double damage;
    protected double criticalHitChance;
    protected double criticalHitDamage;
    private final double CD;
    protected double weaponThickness;
    protected double knockBack;

    public double getDamage() {
        return damage;
    }

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public double getCriticalHitDamage() {
        return criticalHitDamage;
    }

    public double getCD() {
        return CD;
    }

    public double getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    private final double range;

    public MeleeWeapon(double damage, double CD, double range) {
        super(new Vec2(/*offset from character*/65, 16), new Vec2(/*size*/164, 12));
        System.out.println(this.getPosition().x + this.getPosition().y);
        setTexture("WorldObjects/weapon.png");
        setCollidable(false);
        this.damage = damage;
        this.CD = CD;
        this.range = range;
    }

    public LivingObject possibleTarget(){
        HitBoxObject hitBoxObject = new HitBoxObject(getPosition(),
                new Vec2((float)range*meter, (float)weaponThickness));

        for(double i = 0; i < 999; i = i+0.1){}
        WorldObject wo = CollisionManager.getInstance().getCollidedObject(hitBoxObject);
        if(wo instanceof LivingObject){
            System.out.println("MeleeWeapon: Thing is living");
            return (LivingObject)wo;
        }
        //System.out.println("MeleeWeapon: Thing is null");
        return null;
    }


    public void turnWeaponRight(){
        setPosition(Vec2.dotProduct(getNonRelativePosition(), new Vec2(-1,1)));
        setPosition(64,getNonRelativePosition().y);
    }
    public void turnWeaponLeft() {
        setPosition(Vec2.dotProduct(getNonRelativePosition(), new Vec2(-1,1)));
        setPosition(-64, getNonRelativePosition().y);
    }

    public void rotate(double rotation){
        setPosition(
                (float)(getPosition().x*Math.cos(rotation)- getPosition().y*Math.sin(rotation)),
                (float)(getPosition().y*Math.cos(rotation)+ getPosition().x*Math.sin(rotation)));
    }
}
