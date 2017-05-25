
package Tests;

import com.tda367.infinityrun.*;
import com.tda367.infinityrun.Character;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.Math.Vec4;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.CoinObject;
import com.tda367.infinityrun.Roomtemplates.TestingWorldGenerator;
import com.tda367.infinityrun.Shop;
import com.tda367.infinityrun.World;
import com.tda367.infinityrun.SpecialUpgrades.Health;
import com.tda367.infinityrun.desktop.DesktopLauncher;
import junit.framework.TestCase;
import org.junit.*;


/**
 * Created by Mikael on 5/23/2017.
 **/

public class DesktopLauncherTest extends TestCase{
    World simulatedWorld;

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
        simulatedWorld.setInput(new InputEmpty(false,false,false,false));
    }
    @Test
    public void testDemage(){

        Character hero = new Character(((new Vec2(0,0))),1,1,1,0,1,1,1,1);
        setHero(hero);
        simulatedWorld.addWorldObject(new Enemy(new Vec2(0,0),((new Vec2(64,64))),1,1,1,1,1,1,1,1));
        simulatedWorld.frame(1);
        assertTrue(hero.getHealth() < 500);
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
        assertTrue(hero.getCoins() == 1);

    }


    @Test
    public void testPurchaseSpeed(){
        Character hero = new Character(new Vec2(0,0));
        Shop shop = new Shop(hero);
        String upgradeName = "Speed";
        int before1 = shop.getUpgList().get(upgradeName).getLevel();
        shop.purchaseUpgrade(upgradeName);
        int after1 = shop.getUpgList().get(upgradeName).getLevel();
        assertTrue(before1+1 == after1);
        int before2 = shop.getUpgList().get(upgradeName).getLevel();

//At this point the hero should be too poor to afford more speed upgrades
        shop.purchaseUpgrade(upgradeName);
        int after2 = shop.getUpgList().get(upgradeName).getLevel();
        assertTrue(after2 == before2);
        assertTrue(shop.getPrice(shop.getUpgList().get(upgradeName)) > hero.getCoins());


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
        assertTrue(hero.getUpgrades().get("CHC").getValueDouble() == 1);
        assertTrue(hero.getUpgrades().get("CHD").getValueDouble() == 1.05);
        assertTrue(hero.getUpgrades().get("Melee").getValueDouble() == 1);
        assertTrue(hero.getUpgrades().get("Hermes").getValueInt() == 0);
    }

    @Test
    public void Testdamage(){
        Character hero = new Character((new Vec2(0,0)));
        setHero(hero);
        Enemy monster = new Enemy((new Vec2(0,0)),((new Vec2(64,64))),1,1,1,1,1,1,1,1);
        simulatedWorld.frame(0.016f);
        assertTrue(hero.getHealth() < hero.getMaxHealth());
    }


}

