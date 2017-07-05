package com.tda367.infinityrun.Model.Upgrades;

/*

    this upgrade handles the chance of you getting a critical hit. It increases the chance by 4% by level, up to a max of 80
 */
public class CriticalHitChance extends Upgrade {

    //This upgrade handles the chance of you getting a critical hit. It increases the chance by 4% by level, up to a max of 80
    public CriticalHitChance(int level) {
        super(20, level);
        perLevelMultiplier = 4;
        basePrice = 5;
    }

    //Returns the chance of a critical hit in decimal form
    @Override
    public double getValueDouble() {
        return (level * perLevelMultiplier) / (double) (100);
    }
}
