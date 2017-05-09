package com.tda367.infinityrun;

import java.util.HashMap;
import java.util.List;

public class Upgrades {
    //list of upgrades
    private HashMap<String, Upgrade> upgrades = new HashMap<String, Upgrade>();

    public Upgrades(int characterID) {
        upgrades.put("Health",      new Upgrade("Health Points", -1, false, 20));
        upgrades.put("Melee",       new Upgrade("Melee Weapon Handling", -1, true, 5));
        upgrades.put("Ranged",      new Upgrade("Ranged Weapon Handling", -1, true, 5));
        upgrades.put("Regeneration",new Upgrade("Health Regeneration", -1, false, 1));
        upgrades.put("Quiver",      new Upgrade("Quiver Size", -1, false, 5));
        upgrades.put("Looting",     new Upgrade("Better Looting", -1, true, 2));
        upgrades.put("Speed",       new Upgrade("Movement Speed", 100, true, 2));
        upgrades.put("Jumping",     new Upgrade("Jumping Power", 100, true, 2));
        upgrades.put("Hermes",      new Upgrade("Hermes Sandals", 1, false, 1));
        upgrades.put("Dash",        new Upgrade("Dash", 1, false, 100));
    }
}
