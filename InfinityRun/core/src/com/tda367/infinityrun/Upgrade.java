package com.tda367.infinityrun;

public class Upgrade {

    private final String upgradeName;
    private final int cap;            //The maximum level of the skill, -1 means it doesn't have a cap.
    private final boolean multiply;   //If you will multiply the skill with a percentage or if you will add the value flat
    private final int basevalue;      //This is the basic value you get per level of the upgrade
    private int level = 0;      //Skill level, the higher the level the more stats it gives. Starts at level 0.

    public Upgrade(String upgradeName, int cap, boolean multiply, int basevalue) {
        this.upgradeName = upgradeName;
        this.cap = cap;
        this.multiply = multiply;
        this.basevalue = basevalue;
    }

    public void levelUp() {
        this.level++;
    }

    public int getLevel() {
        return this.level;
    }

    public String getUpgradeName() {
        return this.upgradeName;
    }

    public int getCap() {
        return this.cap;
    }

    public void frame(float dt)
    {
        //if(inputstate.isjumppressed())
    }

    public int getValue() {
        if (this.multiply) {
            return 100 + this.basevalue * this.level; //This is the number of percent that you will get of the current value
        }
        return this.basevalue * this.level; // this will be added to the current value
    }

    public boolean doMultiply() {
        return this.multiply;
    }

}
