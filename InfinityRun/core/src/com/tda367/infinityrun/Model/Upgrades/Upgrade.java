package com.tda367.infinityrun.Model.Upgrades;

import com.tda367.infinityrun.Controller.InputState;

public class Upgrade {

    private final int cap;            //The maximum level of the skill, -1 means it doesn't have a cap.
    protected int perLevelMultiplier;      //This is the basic value you get per level of the upgrade
    protected int level = 0;      //Skill level, the higher the level the more stats it gives. Starts at level 0.
    protected int basePrice;    //The base cost of an upgrade - the higher the level the higher the price will become.

    protected Upgrade(int cap, int level) {
        this.cap = cap;
        this.level = level;
    }

    //Adds a level to the upgrade unless it is at the upgrade cap, the uncapped will go to the else condition and give a level.
    public void addLevel() {
        if (cap >= 0) {
            level = Math.min(level + 1, cap);
        } else {
            level = level + 1;
        }
    }

    //Works like the add single level but this can takes an int and give the upgrade the value of the int levels.
    public void addLevel(int level) {
        if (cap > 0) {
            this.level = Math.min(this.level + level, cap);
        } else {
            this.level = this.level + level;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public int getCap() {
        return this.cap;
    }

    public int getBasePrice() {
        return this.basePrice;
    }

    public void frame(float dt, InputState input, ObjectModifiers modifier) {
    }

    //Returns the int value - which is a flat increase of your normal stats.
    public int getValueInt() {
        return this.perLevelMultiplier * this.level; // this will be added to the current value
    }

    //Return the double value - this is a multiplier to your different stats.
    public double getValueDouble() {
        return 1 + (this.perLevelMultiplier * this.level) / 100;
    }
}
