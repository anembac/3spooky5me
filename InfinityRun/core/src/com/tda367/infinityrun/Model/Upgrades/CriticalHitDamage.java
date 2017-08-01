package com.tda367.infinityrun.Model.Upgrades;

/*

    This upgrade handles the damage increase of your critical hits Each upgrade increases the criical damage by 5%
 */
public class CriticalHitDamage extends Upgrade {

    //This upgrade handles the damage increase of your critical hits Each upgrade increases the criical damage by 5%
    public CriticalHitDamage(int baseValue) {
        super(-1, baseValue);
        perLevelMultiplier = 1;
        basePrice = 10;
    }

    //The base damage increase of a critical hit is to 200% of the normal damage and then 5% for every level of this upgrade
    @Override
    public double getValueDouble() {
        return ((double)2 + (level * (double)perLevelMultiplier) * (level*(double)perLevelMultiplier/2));
    }
}
