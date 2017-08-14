package test.com.tda367.infinityrun;


import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Model.Shop;
import com.tda367.infinityrun.Utils.Math.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopTest {

    @Test
    public void testPurchaseUpgrade(){
        Character hero = new Character(25,1,0,0,0,0,0,0,0,0);
        Shop shop = new Shop(hero);
        String upgradeName = "Speed";
        int before1 = shop.getUpgList().get(upgradeName).getLevel();
        shop.purchaseUpgrade(upgradeName);
        int after1 = shop.getUpgList().get(upgradeName).getLevel();
        String message = "Upgrade level before purchase was " + before1 + ". Upgrade level after is " + after1+".";
        System.out.println(before1 + 1 == after1);
        assertTrue(before1 + 1 == after1, message);
        int before2 = shop.getUpgList().get(upgradeName).getLevel();

        //At this point the hero should be too poor to afford more speed upgrades
        shop.purchaseUpgrade(upgradeName);
        int after2 = shop.getUpgList().get(upgradeName).getLevel();
        message = "Upgrade level before purchase was " + before2 + ". Upgrade level after is " + after2+".";
        assertTrue(after2 == before2, message);
        assertTrue(shop.getPrice(shop.getUpgList().get(upgradeName)) > hero.getCoins());

        //Gave hero more coins, checking that upgrading beyond level 1 works
        shop.purchaseUpgrade(upgradeName);
        assertTrue(shop.getUpgList().get(upgradeName).getLevel() == 2, "Upgrade level should be 2,  is now: " + shop.getUpgList().get(upgradeName).getLevel());


        //Change upgrade, making sure it doesn't have the level of the previous one for some reason
        upgradeName = "Looting";
        shop.purchaseUpgrade(upgradeName);
        assertTrue(shop.getUpgList().get(upgradeName).getLevel() == 1);


    }
}
