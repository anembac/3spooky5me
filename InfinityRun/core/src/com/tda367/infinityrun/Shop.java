package com.tda367.infinityrun;


import java.util.HashMap;

public class Shop {
    private Character shopOwner;
    private HashMap<String, Upgrade> upgList;

    public Shop(Character currentChar){
        shopOwner = currentChar;
        upgList = shopOwner.getUpgrades();
    }

    public void purchaseUpgrade(String upgName){

        Upgrade currentUpg = upgList.get(upgName);

        if(getPrice(currentUpg)<=shopOwner.getCoins()){
            int templvl = currentUpg.getLevel();
            currentUpg.addLevel();
            if(currentUpg.getLevel()!=templvl){
                shopOwner.chargeCoins(getPrice(currentUpg));
            }
        }else{
            System.out.println("You cannot afford this upgrade.");
        }
    }

    public int getPrice(Upgrade upg){
        return upg.getBasePrice()*(upg.getLevel()+1);
    }

    public HashMap<String, Upgrade> getUpgList(){
        return upgList;
    }

    public String[] getUpgNameList(){
        int len = getUpgList().keySet().size();
        String[] nameList = new String[len];
        for(int i = 0; i<len;i++){
            nameList[i] = (String)getUpgList().keySet().toArray()[i];
        }
        return nameList;
    }







}
