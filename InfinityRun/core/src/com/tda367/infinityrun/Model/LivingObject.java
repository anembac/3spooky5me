package com.tda367.infinityrun.Model;

import com.badlogic.gdx.Input;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.Utils.Utils;
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

    public LivingObject(Vec2 position, Vec2 bounds) {
        // Initialize the default enemy with lvl 1.
        this(position, bounds, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    //Equips a random weapon.
    public void equipWeapon() {
        removeEquippedWeapon();
        MeleeWeapon weapon = setRandomWeapon();
        addChildren(weapon);
        equippedWeapon = weapon;
        calculateDamage();
        range = equippedWeapon.getRange();
        cooldown = equippedWeapon.getCD();


    }
    //Equips a specific weapon, not currently in use.
    public void equipWeapon(MeleeWeapon weapon) {
        removeEquippedWeapon();
        addChildren(weapon);
        equippedWeapon = weapon;
        calculateDamage();
        range = equippedWeapon.getRange();
        cooldown = equippedWeapon.getCD();
    }

    public void removeEquippedWeapon(){
        if (equippedWeapon != null) {
            removeChildren(equippedWeapon);
            damage = 0;
            range = 0;
            cooldown = 1;
            critHitChance = 0;
            critHitDamage = 0;
        }
    }

    public double getDamage() {
        return damage;
    }

    //gets a weapon if the worldobject has one.
    public String getWeaponName() {
        return equippedWeapon.getName();
    }

    public MeleeWeapon getWeapon() {
        return equippedWeapon;
    }

    public void calculateDamage() {
        if (equippedWeapon != null) {
            damage = (equippedWeapon.damage + anvilDamage) * getMeleeHandling();
            critHitChance = equippedWeapon.criticalHitChance * getCriticalHitChanceMultiplier();
            critHitDamage = damage * equippedWeapon.criticalHitDamage * getCriticalHitDamageMultiplier();
        }

    }

    //this method randomizes a weapon for livingobjects.
    //TODO: Improve extensiblity
    public MeleeWeapon setRandomWeapon (){
        Random rnd = new Random();
        int randomizedWeapon = rnd.nextInt(100) + 1;
    //    randomizedWeapon=60; //always spear TODO: remove this line when done debugging

        if (randomizedWeapon <20){
            return new WeaponAxe(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }

        if (randomizedWeapon<40){
            return new WeaponDagger(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }

        if (randomizedWeapon <60){
            return new WeaponMace(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }

        if (randomizedWeapon <80){
            return new WeaponSpear(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        }
        return new WeaponSword(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
    }

    //TODO implement ranged
    public void equipRangedWeapon() {
    }


    private boolean isCritical() {
        int rnd = new Random().nextInt(100);
        return (critHitChance) > rnd;
    }


    public void takeDamage(double damage) {
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

    public double getCriticalHitChance() {
        return critHitChance;
    }

    public double getCriticalHitDamage() {
        return critHitDamage;
    }

    double getCriticalHitChanceMultiplier() {

        return upgrades.get("CHC").getValueDouble();
    }

    double getCriticalHitDamageMultiplier() {
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
        calculateDamage();
    }

    public LivingObject(Vec2 pos, Vec2 bound, WorldObject parent) {
        super(pos, bound, parent);
    }


    //method that regenerates LivingObjects based on their Regeneration level.
    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
     this.state = state;
        stunCheck();
        //Setup
        //Movement and collision checking
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

    public void addUpgrade(String name, Upgrade upg) {
        upgrades.put(name, upg);
    }

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
                    }
                    target.takeDamage(damage);
                    target.knockback(equippedWeapon.getKnockback(), target.equippedWeapon.isWeaponFacingRight());
                    currentCooldown = (float) cooldown;

                }
            }
        }


    }

    public void knockback(float strength, boolean weaponDirection) {
        if(!weaponDirection){
            acceleration.x = -strength;
        }else{
            acceleration.x = strength;
        }
        acceleration.y = strength;
        stunTimer = 250;
    }

    public void stunTimerCountDown(float dt){
        System.out.println(stunTimer);
        stunTimer = stunTimer - 1000*dt;
        if(stunTimer<0){
            stunTimer = 0;
        }
    }

    public void stunCheck(){
        if(stunTimer > 0){
            state = new InputState(false, false, false, false, false, false);
        }
    }


    public void move(float dt, float height, float rightIntersection, float leftIntersection, float roof) {
        Vec2 currentPos = getPosition();
        currentPos.add(Vec2.mul(acceleration, dt));
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

    private void regenerate(float dt) {
        timeSinceRegen += dt;
        if (timeSinceRegen >= 1) {
            currentHealth = Math.min(getRegeneration() + currentHealth, getMaxHealth());
            timeSinceRegen = 0;
        }
    }

    private void fall(float dt, float height) {
        if (this.getPosition().y > height) {
            //acceleration.y -= 9.82*dt;
            // Standard "earth" gravitation feels very wrong. Changed to make the game seem smoother.
            acceleration.y -= 2.5 * 9.82 * Constants.meter * dt;
        }
    }

    private void turn() {
        if (this.acceleration.x > 0 && equippedWeapon != null) equippedWeapon.turnWeaponRight();
        if (this.acceleration.x < 0 && equippedWeapon != null) equippedWeapon.turnWeaponLeft();
    }

}
