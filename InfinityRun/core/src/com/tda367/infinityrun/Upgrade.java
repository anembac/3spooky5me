package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Utils;

public class Upgrade {

    protected final int cap;            //The maximum level of the skill, -1 means it doesn't have a cap.
    protected int upgradeValue;      //This is the basic value you get per level of the upgrade
    protected int level = 0;      //Skill level, the higher the level the more stats it gives. Starts at level 0.

    public Upgrade(int cap, int level) {
        this.cap = cap;
        this.level = level;
    }

    public void addLevel() {
        level = Math.min(level + 1, cap);
    }

    public void addLevel(int level){
        this.level = Math.min(this.level + level, cap);
    }

    public int getLevel() {
        return this.level;
    }

    public int getCap() {
        return this.cap;
    }

    public void frame(float dt, InputState input, ObjectModifiers modifier) {
    }


    public int getValueInt() {
        return this.upgradeValue * this.level; // this will be added to the current value
    }

    public double getValueDouble() {
        return (100 + this.upgradeValue * this.level)/100; //This is the value you will multiply with the standard to get ur true value.
    }
}
