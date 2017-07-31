package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Rect;
import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Utils.Math.Vec4;

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
        super(new Vec2(/*offset from character*/65, 16), new Vec2(0,0));
        setTexture("WorldObjects/weapon.png");
        setCollidable(false);
        this.damage = damage;
        this.CD = CD;
        this.range = range;
    }

    public void setWeaponThickness(int thickness){
        weaponThickness = thickness;
        setBounds(new Vec2((float)range*meter, getWeaponThickness()));
        setExtraPoints(new Vec2(getNonRelativePosition().x,getBounds().y), new Vec2(getBounds().x, getNonRelativePosition().y));
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
        setPosition(Vec2.dotProduct(getNonRelativePosition(), new Vec2(-1,1)));

        setPosition(64,getNonRelativePosition().y);
    }
    public void turnWeaponLeft() {
        setPosition(Vec2.dotProduct(getNonRelativePosition(), new Vec2(-1,1)));
        setPosition(-64, getNonRelativePosition().y);
    }

    public void rotate(double rotation){
//        setBounds(new Vec2(
//                (float)(getBounds().x*Math.cos(rotation)- getBounds().y*Math.sin(rotation)),
//                (float)(getBounds().y*Math.cos(rotation)+ getBounds().x*Math.sin(rotation))));
        this.rotation = (float)rotation;

        double s = Math.sin(rotation);
        double c = Math.sin(rotation);

        upperLeft = new Vec2 ((float)(getNonRelativePosition().x * Math.cos(rotation)), ((float)(getBounds().y * Math.cos(rotation))));
        setBounds(new Vec2 ((float)(getBounds().x * Math.sin(rotation)), ((float)(getBounds().y * Math.sin(rotation)))));
        //setPosition(new Vec2 ((float)(getNonRelativePosition().x * -1 * Math.sin(rotation)), ((float)(getNonRelativePosition().y * -1 * Math.sin(rotation)))));
        lowerRight = new Vec2 ((float)(lowerRight.x * Math.cos(rotation)), ((float)(lowerRight.y * Math.cos(rotation))));
        System.out.println("ULx: "+upperLeft.x);
        System.out.println("ULy: "+upperLeft.y);
        System.out.println("LRx: "+lowerRight.x);
        System.out.println("LRy: "+lowerRight.y);

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
}
