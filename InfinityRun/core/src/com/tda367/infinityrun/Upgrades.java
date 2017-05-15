package com.tda367.infinityrun;

import com.tda367.infinityrun.SpecialUpgrades.*;

import java.util.HashMap;

public class Upgrades {
    private HashMap<String, Upgrade> upgrades = new HashMap<String, Upgrade>(); //Holds all of the upgrades, gives them a name as index.

    public Upgrades() {
        upgrades.put("Health", new Health(-1, 20)); //Added flat on current health
        upgrades.put("Melee", new MeleeHandling(-1, 5)); //Multiplied to your weapons damage to determine your characters overall damage per hit with melee.
        upgrades.put("Regeneration", new Regeneration(-1, 1)); //Added as a flat increase to your overall health regeneration per second
        upgrades.put("Looting", new Looting(-1, 2));    //Multiplied as a modifier to increase number of coins dropped - 250% chance of coins means 2 coins and 50% chance of extra.
        upgrades.put("Speed", new Speed(100, 4));       //Added as a flat increase to Movement Speed
        upgrades.put("JumpH", new JumpH(100, 2));       //Added as a flat increase to Jump Power
        upgrades.put("Hermes", new HermesSandals(1, 1)); //Added as a flat increase
        upgrades.put("CHC", new CriticalHitChance(60, 1)); //Added as flat CriticalHitChance
        upgrades.put("CHD", new CriticalHitDamage(-1, 5)); //Added as multiplier to your CriticalHitDamage
        //Not sure if we will have this     upgrades.put("Dash",        new Upgrade(1 100));
        //Not yet implemented ranged        upgrades.put("Ranged",      new Upgrade(-1, 5));
        //Not yet implemented ranged        upgrades.put("Quiver",      new Upgrade(-1, 5));
    }

    public void getUpgrade(){
        upgrades.get("Health");
    }
}

//THIS CLASS IS NO LONGER USED - STILL HAS SOME SHIT I WOULD LIKE TO USE PLEASE NO REMOVE.