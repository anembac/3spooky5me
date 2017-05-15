package com.tda367.infinityrun;

public class Upgrade {

    protected final int cap;            //The maximum level of the skill, -1 means it doesn't have a cap.
    protected final int baseValue;      //This is the basic value you get per level of the upgrade
    protected int level = 0;      //Skill level, the higher the level the more stats it gives. Starts at level 0.

    public Upgrade(int cap, int basevalue) {
        this.cap = cap;
        this.baseValue = basevalue;
    }

    public void levelUp() {
        this.level++;
    }

    public int getLevel() {
        return this.level;
    }

    public int getCap() {
        return this.cap;
    }

    public void frame(float dt, InputState input, ObjectModifiers modifier)
    {
        //if(inputstate.isjumppressed())
    }


    public int getValueInt() {
        return this.baseValue * this.level; // this will be added to the current value
    }

    public double getValueDouble() {
        return (100 + this.baseValue * this.level)/100; //This is the value you will multiply with the standard to get ur true value.
    }
}
