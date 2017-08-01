package com.tda367.infinityrun.Model;


import com.tda367.infinityrun.Model.Upgrades.Upgrade;

import java.util.HashMap;

//this is the backend of the shop function, where you buy the upgrades for the Character.

public class Shop {
    private final Character shopOwner;
    private final HashMap<String, Upgrade> upgList;
    private boolean displayPoorMessage;


    //shop is bound to one character. this is so that one character can't upgrade another.
    public Shop(Character currentChar) {
        shopOwner = currentChar;
        upgList = shopOwner.getUpgrades();
        displayPoorMessage = true;
    }

    //todo whenever upgrading, prices for all upgrades should go up by a little.

    //when trying to upgrade, it either tells the player that they're too poor, the upgrade is at max level, or upgrades.
    // if an upgrade is completed, it will "refresh" the MeleeWeapon.
    public void purchaseUpgrade(String upgName) {
        displayPoorMessage = false;

        Upgrade currentUpg = upgList.get(upgName);

        if (getPrice(currentUpg) <= shopOwner.getCoins()) {
            if (currentUpg.getLevel() < currentUpg.getCap() || currentUpg.getCap() < 0) {
                shopOwner.chargeCoins(getPrice(currentUpg));
                currentUpg.addLevel();
                shopOwner.calculateDamage();
            }
        } else {
            setDisplayPoorMessage(true);
        }
    }

    public boolean displayPoorMessage() {
        return displayPoorMessage;
    }

    public void setDisplayPoorMessage(boolean isPoor){ displayPoorMessage = isPoor;}

    public int getPrice(Upgrade upg) {
        return upg.getBasePrice() * (upg.getLevel() + 1);
    }

    public HashMap<String, Upgrade> getUpgList() {
        return upgList;
    }


    //array for getting the upgrades.
    public String[] getUpgNameList() {
        int len = getUpgList().keySet().size();
        String[] nameList = new String[len];
        for (int i = 0; i < len; i++) {
            nameList[i] = (String) getUpgList().keySet().toArray()[i];
        }
        return nameList;
    }


}
