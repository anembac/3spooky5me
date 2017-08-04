package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Rect;
import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Utils.Math.Utils;

import java.util.ArrayList;

import static com.tda367.infinityrun.Utils.Constants.meter;


/*
MeleeWeapon is an extension of Worldobjects, as it has basic properties of one (intersections, positions, etc) but it's main purpose is to be "attached" to a LivingObject
this gives them a source of attack to harm other LivingObjects.
 */
//todo different knockback for different weapons
public class MeleeWeapon extends WorldObject {
    public Vec2 upperLeft;
    public Vec2 lowerRight;
    protected String name;
    protected double damage;
    protected double criticalHitChance;
    protected double criticalHitDamage;
    private final double CD;
    protected double weaponThickness;
    protected float knockBack;
    private float rotation = 0;
    private boolean weaponFacingRight = true;
    private boolean isAttacking = false;


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

    public boolean isWeaponFacingRight() {
        return weaponFacingRight;
    }

    private final double range;

    public MeleeWeapon(double damage, double CD, double range) {
        /*Size here doesn't matter as we update it immediately*/
        super(new Vec2(/*offset from character*/64, 16), new Vec2(0, 0));
        setTexture("WorldObjects/weapon.png");
        setCollidable(false);
        this.damage = damage;
        this.CD = CD;
        this.range = range;
    }
    //sets thickness of a weapon, used in colision detection.
    public void setWeaponThickness(int thickness) {
        weaponThickness = thickness;

        setBounds(new Vec2((float) range * meter, getWeaponThickness()));
    }
        //creates a square, nontangible worldobject with the size of the meleeWeapon. It then compares bounds with all worldobjects within it's area and returns a livingobject.
    public LivingObject possibleTarget() {
        HitBoxObject hitBoxObject = new HitBoxObject(new Vec2(1, 1), new Vec2(1, 1));


             hitBoxObject = new HitBoxObject(getPosition(), new Vec2(getBounds().x, Math.abs(getBounds().x)));
       ArrayList<WorldObject> wo = CollisionManager.getInstance().getCollidedObject(hitBoxObject);

        for(WorldObject o : wo){
            if(o instanceof LivingObject){
                return (LivingObject)o;
            }
        }
        return null;
    }


    //turns the weapon right. hardcoded for now.
    public void turnWeaponRight() {
        if (!weaponFacingRight) {
            setAttacking(false);
            setRotation(0);


            Vec2 newPos = new Vec2(64, 16);
            //  newPos = xreflection(newPos);
            Vec2 newBounds = new Vec2(((float) (meter * range)), ((float) (weaponThickness)));
            //   newBounds = xreflection(newBounds);
            setBounds(newBounds);
            setPosition(newPos);
            weaponFacingRight = true;
        }

    }

    //turns the weapon left. Hardcoded for now
    public void turnWeaponLeft() {
        if (weaponFacingRight) {
            setAttacking(false);
            setRotation(0);
            Vec2 newPos = new Vec2(0, 16);
            newPos = Utils.xreflection(newPos);
            setPosition(newPos);
            Vec2 newBounds = new Vec2(((float) (meter * range)), ((float) (weaponThickness)));
            newBounds = Utils.xreflection(newBounds);
            setBounds(newBounds);
            weaponFacingRight = false;
        }
    }
        //roates the weapon. used in attacking.
    public void rotate(float rotation) {
        this.rotation = this.rotation + rotation;

    }

    //sets roation to a fix degree
    public void setRotation(float theta) {
//        setPosition(theta-this.rotation, theta -this.rotation);
        rotate( (theta - this.rotation));
    }
        //this creates a slashing animation in the drawer. Has two cases for the direction the weapon is facing.
    public void slash(float dt) {

        if (weaponFacingRight == true) {

            if (this.rotation == 0) {
                this.setRotation(90);
            } else if (this.rotation < 0) {
                this.setRotation(0);
                setAttacking(false);
            } else {
                this.setRotation(this.getRotation() - 800 * dt);
            }
        }
        if (weaponFacingRight == false) {
            if (this.rotation == 0) {
                this.setRotation(270);
            } else if (this.rotation > 360) {
                this.setRotation(0);
                setAttacking(false);
            } else {
                this.setRotation(this.getRotation() - -800 * dt);
            }
        }
    }

    public float getWeaponThickness(){
        return (float)weaponThickness;
    }

    public float getRotation() {
        return rotation;
    }

    public Rect getDrawingRect() {
        return new Rect(getPosition(), upperLeft, lowerRight, getBounds());
    }


    public boolean isAttacking() {
        return isAttacking;
    }

    public float getKnockback() {
        return knockBack;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
