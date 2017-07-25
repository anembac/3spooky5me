package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.Utils.Math.Vec2;
import com.tda367.infinityrun.Model.Upgrades.Looting;
import com.tda367.infinityrun.Model.Upgrades.Upgrade;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.tda367.infinityrun.Utils.Constants.meter;

/*
 the player character for the game, an extension of a LivingObject, but it can also collect coins and move from the pllayer input.
 */
public class Character extends LivingObject {
private static final Vec2 startingcoordinates = new Vec2(800,450);
private static final Vec2 sizeBounds = new Vec2(meter,meter);

    public Character() {
        this( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

        //stats for the player character: Number of current coins, level of Looting upgrade, speed upgrade, JumpHeight upgrade, HermesSandal upgrade, Health upgrade, MeleeHandling upgrade,
        //CriticalHitChance upgrade, CriticalHitDamage upgrade and Regeneration upgrade.

    public Character(int numCoins, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl,
                     int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl, int lootLvl) {

        super(startingcoordinates, sizeBounds, speedLvl, jumpLvl, hermesLvl, healthLvl, meleeHandlingLvl, ChcLvl,
                Chdlvl, regLvl);
        coins = numCoins;
        this.addObserver(new DeathObserver());


        addUpgrade("Looting", new Looting(lootLvl));    //Multiplied as a modifier to increase number of coins dropped - 250% chance of coins means 2 coins and 50% chance of extra.
        setTexture("WorldObjects/player.png");     // default hero texture

    }

    private int maxDistance = 0;
    private int characterID;
    private int coins = 0;

   // public String getWeapon(){return MeleeWeapon.g;}

    public void setMaxdistance(int i) {
        maxDistance = i;
    }

    public int getMaxdistance() {
        return maxDistance;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int id) {
        characterID = id;
    }

    @Override
    //method for taking damage, and despawning the character if  it's health is below 0. This also notifies the save function to save the character.
    public void damage(double damage) {
        currentHealth -= damage;
        if (this.currentHealth <= 0) {
            setChanged();
            notifyObservers("dead"+getCharacterID());
            despawn();
            currentHealth = 10000;  //Sets health to above 1 to avoid entering if-statement more than once
                                    //due to the frame-based method calling it will save more than once before
                                    //despawning otherwise. High value to prevent dying more than once per frame.
        }
    }
            //methods to get the values from the upgrades.
    @Override
    public double getMaxHealth() {
        return (20 * 5) + super.getMaxHealth();
    }

    @Override
    public int getMaxSpeed() {
        return (5 * 0) + super.getMaxSpeed();
    }

    @Override
    public int getJumpAcceleration() {
        return (20 * 10) + super.getJumpAcceleration();
    }

    @Override
    public int getRegeneration() {
        return (1 * 2) + super.getRegeneration();
    }

    @Override
    public double getMeleeHandling() {
        return (0.05 * 0) + super.getMeleeHandling();
    }

    @Override
    public double getCriticalHitChance() {
        return (0.01 * 5) + super.getCriticalHitChance();
    }

    @Override
    public double getCriticalHitDamage() {
        return (0.05 * 0) + super.getCriticalHitDamage();
    }

    private double getCoinMultiplier() {
        return upgrades.get("Looting").getValueDouble();
    }

    public int getCoins() {
        return coins;
    }


    //removes coins from the character if the players purchases an upgrade.
    public void chargeCoins(int cost) {
        coins = coins - cost;
        if (coins < 0) { //this case should never happen if shop is implemented properly
            coins = 0;
        }
    }

    @Override
    //method for collecting, and flagging coins for despawning. This is done by a small sub-section of the K-D tree.
    //TODO despawn is still a bit retarded imo

    public void frame(float dt, float heroX, float heroY, InputState state) {
        List<WorldObject> NearObjects = CollisionManager.getInstance().getKNearest(this, 5); //pickup distance

        for (WorldObject wo : NearObjects) {
            if (wo instanceof CoinObject) {
                if (Vec2.distance(WOWrapper.worldObjectCenter(this)
                        , WOWrapper.worldObjectCenter(wo)) < Constants.collectRange) {

                    wo.despawn();
                    coins += numberOfCoins();
                }
            }
        }
        super.frame(dt, heroX, heroY, state);
    }


    public HashMap<String, Upgrade> getUpgrades() {
        return upgrades;
    }

    /*
    This method determines how many coins you will get, 200% would equal 2 coins.
    250% equals 2 coins and a 50% chance to get an extra coin.
    */
    private int numberOfCoins() {
        int coins = ((int) (getCoinMultiplier() * 100)) / 100;
        if (extraCoin(((int) (getCoinMultiplier() *100) %100))) {
            coins++;
            return (coins);
        } else {
            return coins;
        }
    }

    //Determines if you will get the extra coin based on you extra percentage chance.
    private boolean extraCoin(int chance) {
        Random rnd = new Random();
        return chance >= (rnd.nextInt(100) + 1);

    }
}