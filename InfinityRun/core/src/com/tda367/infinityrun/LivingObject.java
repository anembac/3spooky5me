package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.Math.Vec4;
import com.tda367.infinityrun.Upgrades.*;
import com.tda367.infinityrun.WeaponTypes.Sword;

import java.util.HashMap;

/**
 * Created by Mikael on 5/3/2017.
 */
// I guess this class will be some kind of base for "Upgradable" objects, moving objects will probably use some kind
    // of upgrade to allow them to use the command pattern to move. RENAME to upgradableObject?
public class LivingObject extends WorldObject {
    protected HashMap<String, Upgrade> upgrades = new HashMap<String, Upgrade>(); //Holds all of the upgrades, gives them a name as index.

    protected double timeSinceRegen = 0;
    protected double currentHealth = 0;
    protected Vec2 acceleration = new Vec2(0,0);
    private MeleeWeapon meleeWeapon = null;
    private RangedWeapon rangedWeapon = null;

    public LivingObject(Vec2 position, Vec2 bounds) {
        // Initialize the default enemy with lvl 1.
        this(position,bounds,1,1,1,1,1,1,1,1);
    }

    public void setMeleeWeapon() {
        MeleeWeapon weapon = new Sword(getMeleeHandling(), getCriticalHitChance(), getCriticalHitDamage());
        if(meleeWeapon != null)
        {
            removeChildren(meleeWeapon);
        }
        addChildren(weapon);
        meleeWeapon = weapon;
    }

    public void setRangedWeapon(RangedWeapon weapon)
    {

    }

    public void damage(double damage)
    {
        currentHealth -= damage;
        if(this.currentHealth < 0)
        {
            despawn();
        }
        System.out.println(currentHealth + " health left for some creature.");
    }

    public Vec2 getAcceleration()
    {
        return acceleration.clone();
    }

    public double getHealth()
    {
        return currentHealth;
    }

    public double getMaxHealth()
    {
        return (double)upgrades.get("Health").getValueInt();
    }

    public int getRegeneration()
    {
        return upgrades.get("Regeneration").getValueInt();
    }

    public int getJumpAcceleration()
    {
        return upgrades.get("JumpH").getValueInt();
    }

    public int getMaxSpeed()
    {
        return upgrades.get("Speed").getValueInt();
    }

    public double getMeleeHandling() {return upgrades.get("Melee").getValueDouble();}

    public double getCriticalHitChance(){return upgrades.get("CHC").getValueDouble();}

    public double getCriticalHitDamage(){return upgrades.get("CHD").getValueDouble();}


    public LivingObject(Vec2 position, Vec2 bounds, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl, int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl)
    {
        super(position,bounds);
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
    public void frame(float dt, float heroX, float heroY, InputState state){

        timeSinceRegen += dt;
        if(timeSinceRegen >= 1) {
            currentHealth = Math.min(getRegeneration()+currentHealth, getMaxHealth());
            timeSinceRegen = 0;
        }


        Vec4 collisionVariables = CollisionManager.getInstance().getDistanceToCollission(this);
        float height = collisionVariables.x;
        float roof = collisionVariables.z;
        float rightIntersection = collisionVariables.w;
        float leftIntersection = collisionVariables.y;

        ObjectModifiers modifier = new ObjectModifiers(this.position.y > height, acceleration);
        for(Upgrade u :upgrades.values())
        {
            u.frame(dt, state, modifier);
        }
        acceleration = modifier.acceleration.clone();

        // add acceleration down if we are in the air.
        if(this.position.y > height)
        {
            //acceleration.y -= 9.82*dt;
            // so 1 px is 1 unit here, we need to guess the pixel height of the character in meters, etc 150?! this is 64 now but we gravity feels quite low...
            acceleration.y -= 2.5*9.82*Constants.meter*dt;
        }
        // limit the forward acceleration
       // acceleration.x = Utils.limit(-getMaxSpeed(), acceleration.x, getMaxSpeed());
        // limit the "jump/gravity" acceleration.
        acceleration.y = Utils.limit(-100000, acceleration.y, getJumpAcceleration());

        // move the character according to the acceleration vectors.
        position.add(Vec2.mul(acceleration, dt));

        // if we accelerated right into a "block"
        if((position.x + bounds.x) > rightIntersection)
        {
            position.x = rightIntersection - bounds.x;
            acceleration.x = 0;
        }
        // if we accelerated left into a "blobk"
        if(position.x < leftIntersection)
        {
            position.x = leftIntersection;
            acceleration.x = 0;
        }
        // if we accelerated below the ground:
        if(position.y < height)
        {
            position.y = height;
            acceleration.y = 0;
        }
        // if we jumped and hit a roof.
        if((position.y + bounds.y) > roof)
        {
            position.y = roof - bounds.y;
            acceleration.y = 0;
        }

        if(meleeWeapon != null) meleeWeapon.frame(dt,heroX, heroY, state);
        if(this.acceleration.x > 0.1 && meleeWeapon != null) meleeWeapon.setDirRight();
        else if(meleeWeapon != null && this.acceleration.x < -0.1) meleeWeapon.setDirLeft();
    }

    public void addUpgrade(String name, Upgrade upg)
    {
        upgrades.put(name, upg);
    }


}
