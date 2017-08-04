package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.Utils.Math.Utils;
import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Utils.Math.Vec4;
import com.tda367.infinityrun.Model.Upgrades.*;
import com.tda367.infinityrun.Model.WeaponTypes.*;

import java.lang.*;
import java.util.LinkedHashMap;
import java.util.Random;

// LivingObject is an abstraction for any object that needs to have upgrades and can be seen as "alive". It extends regular WorldObjects with additional properties. (upgrades)
public abstract class LivingObject extends WorldObject {
    final LinkedHashMap<String, Upgrade> upgrades = new LinkedHashMap<String, Upgrade>(); //Holds all of the upgrades, gives them a name as index.

    private double timeSinceRegen = 0; //ensures that regeneration is done in solid ticks
    double currentHealth = 0;
    private Vec2 acceleration = new Vec2(0, 0);
    private double damage = 0;
    private double range = 0;
    private double cooldown = 1;
    private float currentCooldown = 0;
    private double critHitChance = 1;
    private double critHitDamage = 1;
    protected double anvilDamage = 0;
    private MeleeWeapon equippedWeapon;
    private boolean knockedBack = false;
    private float stunTimer = 0;
    private InputState state;

    /*
    Creates a LivingObject at the given position with all general upgrades at level one.
     */
    public LivingObject(Vec2 position, Vec2 bounds) {
        // Initialize the default enemy with lvl 1.
        this(position, bounds, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    /*
    Creates a LivingObject at the given position, with the given levels for each upgrade.
    After applying the upgrade stats to the LivingObject, also recalculates the damage to take MeleeHandling into account.
     */
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
        calculateDamage();
    }

    /*
    * Equips a random weapon by first removing any existing weapons, then calling the setRandomWeapon() method to get a
    * weapon that can be added to the objects' list of children. After that the damages, range, and cooldown is updated.
    * */

    public void equipWeapon() {
        removeEquippedWeapon();
        MeleeWeapon weapon = setRandomWeapon();
        addChildren(weapon);
        equippedWeapon = weapon;
        calculateDamage();
        range = equippedWeapon.getRange();
        cooldown = equippedWeapon.getCD();
    }

    /*
    * Similar to equipWepaon(), this method adds a specific weapon rather than a random one.
    * It is currently not being used.
    */
    public void equipWeapon(MeleeWeapon weapon) {
        removeEquippedWeapon();
        addChildren(weapon);
        equippedWeapon = weapon;
        calculateDamage();
        range = equippedWeapon.getRange();
        cooldown = equippedWeapon.getCD();
    }

    /*
    * Removes the equipped weapon from the list of child objects, and resets the stats back to their defaults.
    * */
    public void removeEquippedWeapon() {
        if (equippedWeapon != null) {
            removeChildren(equippedWeapon);
            damage = 0;
            range = 0;
            cooldown = 1;
            critHitChance = 0;
            critHitDamage = 0;
        }
    }

    /*
    * Returns the current damage output of this object. Used to determine damage dealt when attacking.
    * */
    public double getDamage() {
        return damage;
    }

    /*
    Returns the name of the equipped weapon's weapon type, e.g. "Axe", "Spear", "Sword", etc..
     */
    public String getWeaponName() {
        return equippedWeapon.getName();
    }

    /*
    Returns the currently equipped weapon.
     */
    public MeleeWeapon getWeapon() {
        return equippedWeapon;
    }

    /*
    After checking if there is an equipped weapon, this method updates the damage, chance of critical hits,
    and damage dealt by critical hits.

    Regular damage is calculated using the weapon's damage stat, the amount of collected anvils in the current life, and
    multiplies it with the modifier given by the MeleeHandling upgrade.

    critHitChance is calculated using the weapon's base chance to crit and the CriticalHitChance upgrade's multiplier.

    critHitDamage is calculated using teh weapon's base critical damage multiplier multiplied by the CriticalHitDamage
    upgrade's modifier.
     */
    public void calculateDamage() {
        if (equippedWeapon != null) {
            damage = (equippedWeapon.damage + anvilDamage) * getMeleeHandling();
            critHitChance = equippedWeapon.criticalHitChance * getCriticalHitChanceMultiplier();
            critHitDamage = damage * equippedWeapon.criticalHitDamage * getCriticalHitDamageMultiplier();
        }
    }

    /*
    Randomizes an integer between 1 and 100 and selects one of the existing weapons based on that number.
     */
    public MeleeWeapon setRandomWeapon() {
        Random rnd = new Random();
        int randomizedWeapon = rnd.nextInt(100) + 1;

        if (randomizedWeapon < 20) {
            return new WeaponAxe(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }

        if (randomizedWeapon < 40) {
            return new WeaponDagger(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }

        if (randomizedWeapon < 60) {
            return new WeaponMace(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }

        if (randomizedWeapon < 80) {
            return new WeaponSpear(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }
        return new WeaponSword(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
    }

    /*
    Check to see if an attack lands a critical hit.
    Chance is critHitChance%.
     */
    private boolean isCritical() {
        int rnd = new Random().nextInt(100);
        return (critHitChance) > rnd;
    }

    /*
    Called by other objects in their attack methods. If this object's health reaches zero triggers the despawn flag,
    leading to the World object deleting it upon its next frame.
     */
    public void takeDamage(double damage) {
        currentHealth -= damage;
        if (this.currentHealth <= 0) {
            despawn();

        }
    }

    /*
    Returns the object's current health.
     */
    public double getHealth() {
        return currentHealth;
    }

    /*
    Returns the object's max health, based on the Health upgrade.
     */
    public double getMaxHealth() {
        return (double) upgrades.get("Health").getValueInt();
    }

    /*
    Returns the Regeneration value, based on the Regeneration upgrade.
     */
    int getRegeneration() {
        return upgrades.get("Regeneration").getValueInt();
    }

    /*
    Returns the upward acceleration given upon jumping, based on the JumpH upgrade.
    */
    int getJumpAcceleration() {
        return upgrades.get("JumpH").getValueInt();
    }

    /*
    Returns the movement speed, based on the Speed upgrade.
     */
    int getMaxSpeed() {
        return upgrades.get("Speed").getValueInt();
    }

    /*
    Returns the MeleeHandling damage multiplier, based on the MeleeHandling upgrade.
     */
    double getMeleeHandling() {
        return upgrades.get("Melee").getValueDouble();
    }

    /*
    Returns the chance of landing a critical hit in percent.
     */
    public double getCriticalHitChance() {
        return critHitChance;
    }

    /*
    Returns the damage dealt when landing a critical hit.
     */
    public double getCriticalHitDamage() {
        return critHitDamage;
    }

    /*
    Returns the multiplier to critical hit chance, based on the CriticalHitChance upgrade.
     */
    double getCriticalHitChanceMultiplier() { return upgrades.get("CHC").getValueDouble(); }

    /*
    Returns the multiplier to critical hit damage, based on the CriticalHitDamage upgrade.
     */
    double getCriticalHitDamageMultiplier() {
        return upgrades.get("CHD").getValueDouble();
    }


    /*
    The frame method of LivingObject, this is where all the game logic is applied to the object. It's also where all the
    potential actions a LivingObject can take happen. (Under the "Actions" section.
     */
    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        this.state = state;
        stunCheck();
        //Setup
        //Collision checking
        Vec4 collisionVariables = CollisionManager.getInstance().getDistanceToCollission(this);
        float height = collisionVariables.x;
        float roof = collisionVariables.z;
        float rightIntersection = collisionVariables.w;
        float leftIntersection = collisionVariables.y;
        ObjectModifiers modifier = new ObjectModifiers(this.getPosition().y > height, acceleration);

        //Calls upgrades' own frame methods
        for (Upgrade u : upgrades.values()) {
            u.frame(dt, this.state, modifier);
        }
        acceleration = modifier.acceleration.clone();

        // limit the "jump/gravity" acceleration. This prevents problems that shouldn't occur
        acceleration.y = Utils.limit(-5000, acceleration.y, getJumpAcceleration());

        //Actions
        stunCheck();
        regenerate(dt);
        fall(dt, height);
        attack(dt);
        turn();
        move(dt, height, rightIntersection, leftIntersection, roof);
        stunTimerCountDown(dt);

    }
    /*
    Adds an upgrade to the object's list of upgrades.
     */
    public void addUpgrade(String name, Upgrade upg) {
        upgrades.put(name, upg);
    }


    /*
    The attack method.
    First checks if an attack is currently happening, and swings the weapon a number of degrees based on dt each frame.

    If there is a weapon equipped and the attack state is active then counts the cooldown down. If the cooldown is
    small enough, the object can perform an actual attack and deal damage. The weapon searches for a target and the
    target's takeDamage method is called. During an actual attack the target's knockback method is also called.
     */
    public void attack(float dt) {
        if (equippedWeapon != null && equippedWeapon.isAttacking()) {
            equippedWeapon.slash(dt);
        }

        if (equippedWeapon != null && state.attackPressed()) {
            currentCooldown = Math.max(0, currentCooldown - dt);


            if (currentCooldown < 0.001) {

                equippedWeapon.setAttacking(true);

                LivingObject target = equippedWeapon.possibleTarget();
                if (target != null && !target.equals(this)) {
                    if (isCritical()) {
                        target.takeDamage(critHitDamage);
                    }else{
                        target.takeDamage(damage);
                    }
                    target.knockback(equippedWeapon.getKnockback(), equippedWeapon.isWeaponFacingRight());
                    currentCooldown = (float) cooldown;

                }
            }
        }


    }
    /*
    Shortly stuns the object and applies some acceleration in the direction the attacker is facing.

    Stuns last 0.25 seconds.
     */
    public void knockback(float strength, boolean weaponDirection) {
        stunTimer = 0.25f;
        acceleration.y = strength;
        if (!weaponDirection) {
            acceleration.x = -1.5f * strength;
        } else {
            acceleration.x = 1.5f * strength;
        }
    }

    /*
    If the stunTimer is above zero, reduces the stunTimer by dt every frame. Also makes sure that the stunTimer is
    non-negative.
     */
    public void stunTimerCountDown(float dt) {
        stunTimer = stunTimer - dt;
        if (stunTimer < 0) {
            stunTimer = 0;
        }
    }

    /*
    If the object is stunned (i.e stunTimer > 0), sets all the input variables to false which prevents movement and
    attacking.
     */
    public void stunCheck() {
        if (stunTimer > 0) {
            state = new InputState(false, false, false, false, false, false);
        }
    }

    /*
    Controls the object's movement by applying or changing the acceleration in various ways.

    If the object collides with something , the acceleration is set to zero.
     */
    public void move(float dt, float height, float rightIntersection, float leftIntersection, float roof) {
        Vec2 currentPos = getPosition();
        currentPos.add(Vec2.mul(acceleration, dt)); //Actual movement part
        setPosition(currentPos);
        // if we accelerated right into a "block"
        if ((getPosition().x + getDrawingRect().bounds.x) > rightIntersection) {
            setPosition(rightIntersection - getDrawingRect().bounds.x, getPosition().y);
            acceleration.x = 0;
        }
        // if we accelerated left into a "block"
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
    }

    /*
    Increases the currentHealth variable by getRegeneration() amount ever second.
     */
    private void regenerate(float dt) {
        timeSinceRegen += dt;
        if (timeSinceRegen >= 1) {
            currentHealth = Math.min(getRegeneration() + currentHealth, getMaxHealth());
            timeSinceRegen = 0;
        }
    }
    /*
    Makes sure that gravity is applied properly to the object by changing the acceleration downwards.
     */
    private void fall(float dt, float height) {
        if (this.getPosition().y > height) {
            //acceleration.y -= 9.82*dt;
            // Standard "earth" gravitation feels very wrong. Changed to make the game seem smoother.
            acceleration.y -= 2.5 * 9.82 * Constants.meter * dt;
        }
    }
    /*
    Calls the equipped weapon's methods for turning the weapon to the other side.
     */
    private void turn() {
        if (this.acceleration.x > 0 && equippedWeapon != null) equippedWeapon.turnWeaponRight();
        if (this.acceleration.x < 0 && equippedWeapon != null) equippedWeapon.turnWeaponLeft();
    }

}
