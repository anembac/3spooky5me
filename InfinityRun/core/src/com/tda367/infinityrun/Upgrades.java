package com.tda367.infinityrun;

import com.tda367.infinityrun.SpecialUpgrades.*;

import java.util.HashMap;

public class Upgrades {
    //list of upgrades
    private HashMap<String, Upgrade> upgrades = new HashMap<String, Upgrade>();

    public Upgrades(int characterID) {
        upgrades.put("Health",      new Health("Health Points", -1, false, 20));
        upgrades.put("Melee",       new MeleeHandling("Melee Weapon Handling", -1, true, 5));
        //Not yet implemented ranged        upgrades.put("Ranged",      new Upgrade("Ranged Weapon Handling", -1, true, 5));
        upgrades.put("Regeneration",new Regeneration("Health Regeneration", -1, false, 1));
        //Not yet implemented ranged        upgrades.put("Quiver",      new Upgrade("Quiver Size", -1, false, 5));
        //Needs to be worked on...          upgrades.put("Looting",     new Upgrade("Better Looting", -1, true, 2));
        upgrades.put("Speed",       new Speed("Movement Speed", 100, true, 2));
        upgrades.put("JumpH",       new JumpH("Jumping Power", 100, true, 2));
        upgrades.put("Hermes",      new HermesSandals("Hermes Sandals", 1, false, 1));
        //Not sure if we will have this     upgrades.put("Dash",        new Upgrade("Dash", 1, false, 100));
    }
}
