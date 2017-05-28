package com.tda367.infinityrun;

import com.tda367.infinityrun.Controller.InputState;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.Upgrades.Looting;
import com.tda367.infinityrun.Upgrades.Upgrade;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends LivingObject {

    public Character(Vec2 position) {
        this(position,0,0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Character(Vec2 position, int numCoins, int lootLvl, int speedLvl, int jumpLvl, int hermesLvl, int healthLvl,
                     int meleeHandlingLvl, int ChcLvl, int Chdlvl, int regLvl) {

        super(position, new Vec2(64, 64), speedLvl, jumpLvl, hermesLvl,
                healthLvl, meleeHandlingLvl, ChcLvl, Chdlvl, regLvl);


        addUpgrade("Looting", new Looting(lootLvl));    //Multiplied as a modifier to increase number of coins dropped - 250% chance of coins means 2 coins and 50% chance of extra.
        setTexture("WorldObjects/player.png");// default hero texture
        //new Rectangle(position.x, position.y, size, size);
    }

    private int maxDistance = 0;
    private int characterID;
    private int coins = 0;

    public void setMaxdistance(int i){
        maxDistance = i;
    }

    public int getMaxdistance(){
        return maxDistance;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int id){
        characterID = id;
    }

    @Override
    public void damage(double damage) {
        currentHealth -= damage;
        if (this.currentHealth <= 0) {
            despawn();
        }
    System.out.println(currentHealth + " health left for player");
    }

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
        return (0.05 * 0) + upgrades.get("Looting").getValueDouble();
    }

    public int getCoins() {
        return coins;
    }

    public void chargeCoins(int cost){
        coins = coins-cost;
        if(coins<0){ //this case should never happen if shop is implemented properly
            coins = 0;
        }
    }
    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        List<WorldObject> rlt = CollisionManager.getInstance().getKNearest(this, 5);

        for (WorldObject wo : rlt) {
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

    public void lootEnemy () {
        coins += numberOfCoins();
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
        if (extraCoin(((int) (getCoinMultiplier() * 100)) % 100)) {
            return (coins + 1);
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