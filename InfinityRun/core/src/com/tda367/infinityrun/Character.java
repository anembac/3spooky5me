package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.CoinObject;
import com.tda367.infinityrun.SpecialUpgrades.Looting;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends LivingObject {

    public Character(Vec2 position){
        super(position, new Vec2(64,64),0,0,50,0,0,0,0,0);
        addUpgrade("Looting", new Looting(0));    //Multiplied as a modifier to increase number of coins dropped - 250% chance of coins means 2 coins and 50% chance of extra.
        setTexture("WorldObjects/player.png");// default hero texture
        //new Rectangle(position.x, position.y, size, size);
    }

    private int coins = 0;

    @Override
    public double getMaxHealth(){
        return (20 * 20) + super.getMaxHealth();
    }

    @Override
    public int getMaxSpeed(){
        return (5 * 100) + super.getMaxSpeed();
    }

    @Override
    public int getJumpAcceleration(){
        return (20 * 10) + super.getJumpAcceleration();
    }

    @Override
    public int getRegeneration(){
        return (1 * 2) + super.getRegeneration();
    }

    @Override
    public double getMeleeHandling(){
        return (0.05 * 5) + super.getMeleeHandling();
    }

    @Override
    public int getCriticalHitChance(){
        return (1 * 5) + super.getCriticalHitChance();
    }

    @Override
    public double getCriticalHitDamage(){
        return (5 * 5) + super.getCriticalHitDamage();
    }

    public double getCoinMultiplier(){
        return (5 * 2) + upgrades.get("Looting").getValueDouble();
    }

    public int getCoins(){
        return coins;
    }

    @Override
    public void frame(float dt,float heroX, float heroY, InputState state)
    {
        List<WorldObject> rlt =  CollisionManager.getInstance().getKNearest(this, 5);

        for(WorldObject wo : rlt)
        {
            if(wo instanceof CoinObject)
            {
                if(Vec2.distance(Utils.getCenter(this), Utils.getCenter(wo)) < Constants.collectRange)
                {
                    wo.despawn();
                    coins++;
                }
            }
        }
        super.frame(dt, heroX, heroY, state);
    }

    public HashMap<String, Upgrade> getUpgrades(){
        return upgrades;
    }
}