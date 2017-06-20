package com.tda367.infinityrun.Model.Upgrades;

/**
 * Created by Jacob on 5/9/2017.
 */
public class CriticalHitChance extends Upgrade {

    //This upgrade handles the chance of you getting a critical hit
    public CriticalHitChance(int level) {
        super(60, level);
        perLevelMultiplier = 1;
        basePrice = 5;
    }

    //Returns the chance of a critical hit in decimal form
    @Override
    public double getValueDouble() {
        return (level * perLevelMultiplier) / (double) (100);
    }
}
