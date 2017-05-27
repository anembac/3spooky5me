
package Tests;

import com.tda367.infinityrun.*;
import com.tda367.infinityrun.Character;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.Math.Vec4;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.CoinObject;
import com.tda367.infinityrun.RoomTiles.Platform;
import com.tda367.infinityrun.WeaponTypes.Sword;
import com.tda367.infinityrun.WorldGeneration.TestingWorldGenerator;
import com.tda367.infinityrun.Shop;
import com.tda367.infinityrun.World;
import com.tda367.infinityrun.WorldGeneration.TextbasedWorldGenerator;
import com.tda367.infinityrun.WorldGeneration.WorldGenerator;
import junit.framework.TestCase;
import org.junit.*;


/**
 * Created by Mikael on 5/23/2017.
 **/

public class DesktopLauncherTest extends TestCase{
    TextbasedWorldGenerator testgenerator;
    World simulatedWorld;
    World simulatedWorldExtra;

    public boolean almostEqual(float a, float b)
    {
        return (a-b) < 0.001 && (a-b) > -0.001;
    }

    public void setHero(Character hero)
    {

        simulatedWorld.addWorldObject(hero);



    }

    @Override
    protected void setUp() throws Exception
    {
        Character hero = new Character(new Vec2(768,200));
        simulatedWorld = new World(new TestingWorldGenerator(),hero);
        simulatedWorldExtra = new World(new TextbasedWorldGenerator(), hero);
        simulatedWorld.setInput(new InputEmpty(false,false,false,true));
    }
    @Test
    public void testDemage(){

        Character hero = new Character(((new Vec2(0,0))),1,1,1,0,1,1,1,0);
        setHero(hero);
        hero.damage(30);
        assertTrue(hero.getHealth()  < hero.getMaxHealth());
    }




    @Test
    public void testGroundCollision()
    {
        Character hero = new Character(new Vec2(768,200));
        setHero(hero);
        for(int i = 0; i < 240; i++){
            simulatedWorld.frame(0.016f);
        }

        Vec4 collisionVariables = CollisionManager.getInstance().getDistanceToCollission(hero);
        assertTrue(almostEqual(hero.getPosition().y, collisionVariables.x));
    }

    @Test
    public void testCoinCollection() {
        Character hero = new Character(new Vec2(768, 450));
        setHero(hero);
        simulatedWorld.addWorldObject(new CoinObject(new Vec2(768,450)));
        assertTrue(hero.getCoins() == 0);
        simulatedWorld.frame(1);
        assertTrue(hero.getCoins() > 0);

    }


    @Test
    public void testPurchaseUpgrade(){
        Character hero = new Character(new Vec2(0,0));
        Shop shop = new Shop(hero);
        hero.setCoins(10);
        String upgradeName = "Speed";
        int before1 = shop.getUpgList().get(upgradeName).getLevel();
        shop.purchaseUpgrade(upgradeName);
        int after1 = shop.getUpgList().get(upgradeName).getLevel();
        String message = "Upgrade level before purchase was " + before1 + ". Upgrade level after is " + after1+".";
        assertTrue(message,before1+1 == after1);
        int before2 = shop.getUpgList().get(upgradeName).getLevel();

        //At this point the hero should be too poor to afford more speed upgrades
        shop.purchaseUpgrade(upgradeName);
        int after2 = shop.getUpgList().get(upgradeName).getLevel();
        message = "Upgrade level before purchase was " + before2 + ". Upgrade level after is " + after2+".";
        assertTrue(message,after2 == before2);
        assertTrue(shop.getPrice(shop.getUpgList().get(upgradeName)) > hero.getCoins());

        //Gave hero more coins, checking that upgrading beyond level 1 works
        hero.setCoins(20);
        shop.purchaseUpgrade(upgradeName);
        assertTrue("Upgrade level should be 2,  is now: " + shop.getUpgList().get(upgradeName).getLevel()
                , shop.getUpgList().get(upgradeName).getLevel() == 2 );


        //Change upgrade, making sure it doesn't have the level of the previous one for some reason
        upgradeName = "Looting";
        hero.setCoins(100);
        shop.purchaseUpgrade(upgradeName);
        assertTrue(shop.getUpgList().get(upgradeName).getLevel() == 1);


    }

    @Test
    public void testRegeneration(){
        Enemy monster = new Enemy(new Vec2(1,1), new Vec2(64,64), 1,0,0,0,0,0,0,0);
        simulatedWorld.addWorldObject(monster);
        assertTrue(monster.getHealth() == monster.getMaxHealth());
        monster.damage(50);
        simulatedWorld.frame(1);

        double lastdmg = monster.getHealth();
        assertTrue(monster.getHealth() < monster.getMaxHealth());

        simulatedWorld.frame(1);
        assertTrue(monster.getHealth() > lastdmg);
        lastdmg = monster.getHealth();
        simulatedWorld.frame(1);
        assertTrue(monster.getHealth() > lastdmg);
        lastdmg = monster.getHealth();
        simulatedWorld.frame(1);
        assertTrue(monster.getHealth() > lastdmg);
    }



    @Test
    public void testSpeedUpgrade() {
// test different character with different speedUpgradeLvls and see that a higher lvl runs faster.
        float oldSpeed = 0;
        simulatedWorld.setInput(new InputEmpty(true,false,false,false));
        for(int i = 0; i < 100; i++)
        {
            Character hero = new Character(new Vec2(768,450),i,0,0,0,0,0,0,0);
            setHero(hero);
            for(int j = 0; j < 60; j++){
                simulatedWorld.frame(0.016f);
            }
            hero.despawn();
            Vec2 acc = hero.getAcceleration();
            assertTrue(oldSpeed < acc.x);
            oldSpeed = acc.x;
            simulatedWorld.frame(0.016f);
        }
    }

    @Test
    public void testJumpLvl() {
// test different character with different speedUpgradeLvls and see that a higher lvl runs faster.
        float oldJumpH = 0;
        for(int i = 0; i < 100; i++)
        {
            float newJumpH = 0;
            Character hero = new Character(new Vec2(768,64),0,i,0,0,0,0,0,0);
            setHero(hero);
            simulatedWorld.setInput(new InputEmpty(false,false,false,false));
            for(int j = 0; j < 180; j++){
// let the hero fall to the ground so that he can jump..
                simulatedWorld.frame(0.016f);
            }
            simulatedWorld.setInput(new InputEmpty(false,false,true,false));
            simulatedWorld.frame(0.016f);
            newJumpH = hero.getAcceleration().y;
            hero.despawn();
            assertTrue("The new jumpH is " + newJumpH + " and the old one " + oldJumpH, newJumpH > oldJumpH);
            oldJumpH = newJumpH;
            simulatedWorld.frame(0.016f);
        }
    }

    @Test
    public void testUpgradesExist(){
        Character hero = new Character((new Vec2(0,0)));
        setHero(hero);
        assertTrue(hero.getUpgrades().get("Health").getValueInt() == 100);
        assertTrue(hero.getUpgrades().get("Speed").getValueInt() == 500);
        assertTrue(hero.getUpgrades().get("JumpH").getValueInt() == 800);
        assertTrue(hero.getUpgrades().get("Regeneration").getValueInt() == 1);
        assertTrue(hero.getUpgrades().get("CHC").getValueDouble() == 0);
        assertTrue(hero.getUpgrades().get("CHD").getValueDouble() == 2);
        assertTrue(hero.getUpgrades().get("Melee").getValueDouble() == 1);
        assertTrue(hero.getUpgrades().get("Hermes").getValueInt() == 1);
    }


 }

