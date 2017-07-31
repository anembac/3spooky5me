package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Rect;
import com.tda367.infinityrun.Utils.Math.Vec2;

import static com.tda367.infinityrun.Utils.Constants.meter;


/*
MeleeWeapon is an extension of Worldobjects, as it has basic properties of one (intersections, positions, etc) but it's main purpose is to be "attached" to a LivingObject
this gives them a source of attack to harm other LivingObjects.
 */
//todo different knockback for different weapons
public class MeleeWeapon extends WorldObject {
    public  Vec2 upperLeft;
    public  Vec2 lowerRight;
    protected String name;
    protected double damage;
    protected double criticalHitChance;
    protected double criticalHitDamage;
    private final double CD;
    protected double weaponThickness;
    protected double knockBack;
    private float rotation = 0;
    private boolean weaponFacingRight = true;

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
        /*Size here doesn't matter as we update it immediately*/
        super(new Vec2(/*offset from character*/64, 16), new Vec2(0,0));
        setTexture("WorldObjects/weapon.png");
        setCollidable(false);
        this.damage = damage;
        this.CD = CD;
        this.range = range;
    }

    public void setWeaponThickness(int thickness){
        weaponThickness = thickness;
        setBounds(new Vec2((float)range*meter, getWeaponThickness()));
        setExtraPoints(new Vec2(getNonRelativePosition().x,getBounds().y),
                new Vec2(getBounds().x, getNonRelativePosition().y));
    }

    protected void setExtraPoints(Vec2 upperLeft, Vec2 lowerRight){
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
//        System.out.println("ULx: "+upperLeft.x);
//        System.out.println("ULy: "+upperLeft.y);
//        System.out.println("LRx: "+lowerRight.x);
//        System.out.println("LRy: "+lowerRight.y);
    }

    public LivingObject possibleTarget(){
        HitBoxObject hitBoxObject = new HitBoxObject(getPosition(), getBounds());
        WorldObject wo = CollisionManager.getInstance().getCollidedObject(hitBoxObject);
        if(wo instanceof LivingObject){
            return (LivingObject)wo;
        }
        return null;
    }


    public void turnWeaponRight(){
        if(!weaponFacingRight){
            Vec2 newPos = new Vec2(64, getNonRelativePosition().y);
            setPosition(newPos);
            weaponFacingRight = true;
        }

    }
    public void turnWeaponLeft() {
        if(weaponFacingRight){
            Vec2 newPos = new Vec2(-(float)(meter*range), getNonRelativePosition().y);
            setPosition(newPos);
            weaponFacingRight = false;
        }
    }

    public void rotate(float rotation){
        this.rotation = this.rotation+rotation;
        upperLeft = rotateVec2(upperLeft, rotation);
        lowerRight = rotateVec2(lowerRight, rotation);
        setPosition(rotateVec2(getNonRelativePosition(), rotation));
        setBounds(rotateVec2(getBounds(), rotation));
    }


    public void frame(float dt){
        rotate(dt);
    }

    public float getWeaponThickness(){
        return (float)weaponThickness;
    }

    public float getRotation(){
        return rotation;
    }

    public Rect getDrawingRect() {
        return new Rect(getPosition(),upperLeft,lowerRight,getBounds());
    }

    private Vec2 rotateVec2(Vec2 v, float theta){

        float xprim = (float)((v.clone().x*Math.cos(theta))-(v.clone().y*Math.sin(theta)));
        float yprim = (float)((v.clone().x*Math.sin(theta))+(v.clone().y*Math.cos(theta)));

        return new Vec2(xprim, yprim);
    }

    public Vec2 yreflection (Vec2 v){
        float xprim = v.clone().x * 1 + v.clone().y * 0;
        float yprim = v.clone().x * 0 + v.clone().y * -1;
        return new Vec2(xprim, yprim);
    }
}
