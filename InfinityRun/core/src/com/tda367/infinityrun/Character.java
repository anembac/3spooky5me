package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.CoinObject;
import com.tda367.infinityrun.SpecialUpgrades.Looting;

import javax.rmi.CORBA.Util;
import java.util.List;

/**
 * Created by kaffe on 4/3/17.
 */
public class Character extends LivingObject {

    public Character(Vec2 position, Vec2 bounds){
        super(position, bounds,100,10,50,20,5,1,5,1);
        addUpgrade("Looting", new Looting(2));    //Multiplied as a modifier to increase number of coins dropped - 250% chance of coins means 2 coins and 50% chance of extra.
        setTexture("WorldObjects/player.png");// default hero texture
        //new Rectangle(position.x, position.y, size, size);
    }

    private int coins = 0;

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
}