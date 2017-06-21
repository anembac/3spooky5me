package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Controller.InputState;
import com.tda367.infinityrun.Utils.Utils;
import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Utils.Math.Vec4;
import com.tda367.infinityrun.Model.Upgrades.*;
import com.tda367.infinityrun.Model.WeaponTypes.WeaponSword;

import java.util.HashMap;

/**
 * Created by Mikael on 5/3/2017.
 */
// I guess this class will be some kind of base for "Upgradable" objects, moving objects will probably use some kind
// of upgrade to allow them to use the command pattern to move. RENAME to upgradableObject?
public class LivingObject extends WorldObject {
    final HashMap<String, Upgrade> upgrades = new HashMap<String, Upgrade>(); //Holds all of the upgrades, gives them a name as index.

    private double timeSinceRegen = 0;
    double currentHealth = 0;
    Vec2 acceleration = new Vec2(0, 0);
    private MeleeWeapon meleeWeapon = null;
    private RangedWeapon rangedWeapon = null;

    public LivingObject(Vec2 position, Vec2 bounds) {
        // Initialize the default enemy with lvl 1.
        this(position, bounds, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    public void setMeleeWeapon() {

        if (meleeWeapon != null) {
            removeChildren(meleeWeapon);
        }
        MeleeWeapon weapon = new WeaponSword(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        addChildren(weapon);
        meleeWeapon = weapon;
    }

    //TODO implement ranged
    public void setRangedWeapon() {
    }

    public void damage(double damage) {
        currentHealth -= damage;
        if (this.currentHealth <= 0) {
            despawn();

        }
    }

    public Vec2 getAcceleration() {
        return acceleration.clone();
    }

    public double getHealth() {
        return currentHealth;
    }

    public double getMaxHealth() {
        return (double) upgrades.get("Health").getValueInt();
    }

    int getRegeneration() {
        return upgrades.get("Regeneration").getValueInt();
    }

    int getJumpAcceleration() {
        return upgrades.get("JumpH").getValueInt();
    }

    int getMaxSpeed() {
        return upgrades.get("Speed").getValueInt();
    }

    double getMeleeHandling() {
        return upgrades.get("Melee").getValueDouble();
    }

    double getCriticalHitChance() {
        return upgrades.get("CHC").getValueDouble();
    }

    double getCriticalHitDamage() {
        return upgrades.get("CHD").getValueDouble();
    }


    LivingObject(Vec2 position, Vec2 bounds, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl, int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl) {
        super(position, bounds);
        addUpgrade("Speed", new Speed(speedLvl));    //Added as a flat increase to Movement Speed
        addUpgrade("JumpH", new JumpH(jumpLvl));    //Added as a flat increase to Jump Power
        addUpgrade("Hermes", new HermesSandals(hermesLvl));     //Added as a flat increase
        addUpgrade("Health", new Health(healthLvl));  //Added flat on current health
        addUpgrade("Melee", new MeleeHandling(meleeHandlingLvl));     //Multiplied to your weapons damage to determine your characters overall damage per hit with melee.
        addUpgrade("CHC", new CriticalHitChance(ChcLvl)); //Added as flat CriticalHitChance
        addUpgrade("CHD", new CriticalHitDamage(Chdlvl)); //Added as multiplier to your CriticalHitDamage
        addUpgrade("Regeneration", new Regeneration(regLvl)); //Added as a flat increase to your overall health regeneration per second
        currentHealth = getMaxHealth();
    }

    public LivingObject(Vec2 pos, Vec2 bound, WorldObject parent) {
        super(pos, bound, parent);
    }

    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {

        timeSinceRegen += dt;
        if (timeSinceRegen >= 1) {
            currentHealth = Math.min(getRegeneration() + currentHealth, getMaxHealth());
            timeSinceRegen = 0;
        }


        Vec4 collisionVariables = CollisionManager.getInstance().getDistanceToCollission(this);
        float height = collisionVariables.x;
        float roof = collisionVariables.z;
        float rightIntersection = collisionVariables.w;
        float leftIntersection = collisionVariables.y;

        ObjectModifiers modifier = new ObjectModifiers(this.getPosition().y > height, acceleration);
        for (Upgrade u : upgrades.values()) {
            u.frame(dt, state, modifier);
        }
        acceleration = modifier.acceleration.clone();

        // add acceleration down if we are in the air.
        if (this.getPosition().y > height) {
            //acceleration.y -= 9.82*dt;
            // so 1 px is 1 unit here, we need to guess the pixel height of the character in meters, etc 150?! this is 64 now but we gravity feels quite low...
            acceleration.y -= 2.5 * 9.82 * Constants.meter * dt;
        }
        // limit the forward acceleration
        // acceleration.x = Utils.limit(-getMaxSpeed(), acceleration.x, getMaxSpeed());
        // limit the "jump/gravity" acceleration.
        acceleration.y = Utils.limit(-5000, acceleration.y, getJumpAcceleration());

        if (this.acceleration.x > 0 && meleeWeapon != null) meleeWeapon.setDirRight();
        else if (meleeWeapon != null && this.acceleration.x < 0) meleeWeapon.setDirLeft();

        // move the character according to the acceleration vectors.
        Vec2 currentPos = getPosition();
        currentPos.add(Vec2.mul(acceleration, dt));
        setPosition(currentPos);
        // if we accelerated right into a "block"
        if ((getPosition().x + getDrawingRect().bounds.x) > rightIntersection) {
            setPosition(rightIntersection - getDrawingRect().bounds.x, getPosition().y);
            acceleration.x = 0;
        }
        // if we accelerated left into a "blobk"
        if (getPosition().x < leftIntersection) {
            setPosition(leftIntersection, getPosition().y);
            acceleration.x = 0;
        }
        // if we accelerated below the ground:
        if (getPosition().y < height) {
            setPosition(getPosition().x, height);
            acceleration.y = 0;
        }
        // if we jumped and hit a roof.
        if ((getPosition().y + getDrawingRect().bounds.y) > roof) {
            setPosition(getPosition().x, roof - getDrawingRect().bounds.y);
            acceleration.y = 0;
        }

        if (meleeWeapon != null) meleeWeapon.frame(dt, heroX, heroY, state);
    }

    public void addUpgrade(String name, Upgrade upg) {
        upgrades.put(name, upg);
    }


}
