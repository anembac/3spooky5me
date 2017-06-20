package com.tda367.infinityrun.Model;


import com.tda367.infinityrun.Model.Upgrades.Upgrade;

import java.util.HashMap;

public class Shop {
    private final Character shopOwner;
    private final HashMap<String, Upgrade> upgList;
    private boolean displayPoorMessage;

    public Shop(Character currentChar) {
        shopOwner = currentChar;
        upgList = shopOwner.getUpgrades();
        displayPoorMessage = false;
    }

    public void purchaseUpgrade(String upgName) {
        displayPoorMessage = false;

        Upgrade currentUpg = upgList.get(upgName);

        if (getPrice(currentUpg) <= shopOwner.getCoins()) {
            if (currentUpg.getLevel() < currentUpg.getCap() || currentUpg.getCap() < 0) {
                shopOwner.chargeCoins(getPrice(currentUpg));
                currentUpg.addLevel();
                shopOwner.setMeleeWeapon();
            }
        } else {
            //System.out.println("You cannot afford this upgrade.");
            displayPoorMessage = true;
        }
    }

    public boolean displayPoorMessage() {
        return displayPoorMessage;
    }

    public int getPrice(Upgrade upg) {
        return upg.getBasePrice() * (upg.getLevel() + 1);
    }

    public HashMap<String, Upgrade> getUpgList() {
        return upgList;
    }

    public String[] getUpgNameList() {
        int len = getUpgList().keySet().size();
        String[] nameList = new String[len];
        for (int i = 0; i < len; i++) {
            nameList[i] = (String) getUpgList().keySet().toArray()[i];
        }
        return nameList;
    }


}
