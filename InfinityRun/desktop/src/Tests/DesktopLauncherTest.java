//
//package Tests;
//
//import com.tda367.infinityrun.Model.*;
//import com.tda367.infinityrun.Controller.InputEmpty;
//import com.tda367.infinityrun.Utils.Math.Vec2;
//import com.tda367.infinityrun.Utils.Math.Vec4;
//import com.tda367.infinityrun.Model.Character;
//import com.tda367.infinityrun.Model.Upgrades.JumpH;
//import com.tda367.infinityrun.TestingWorldGenerator;
//import org.junit.*;
//
//
///**
// * Created by Mikael on 5/23/2017.
// **/
//
//public class DesktopLauncherTest {
//    TextbasedWorldGenerator testgenerator;
//    private World simulatedWorld;
//
//    private boolean almostEqual(float a, float b)
//    {
//        return (a-b) < 0.001 && (a-b) > -0.001;
//    }
//
//    private void setHero(Character hero)
//    {
//
//        simulatedWorld.addWorldObject(hero);
//
//
//
//    }
//
//    @Before
//    public void setUp() throws Exception
//    {
//        Character hero = new Character(new Vec2(768,200));
//        simulatedWorld = new World(new TestingWorldGenerator(),hero);
//        World simulatedWorldExtra = new World(new TextbasedWorldGenerator(), hero);
//        simulatedWorld.setInput(new InputEmpty(false,false,false,true));
//    }
//    @Test
//    public void testDamage(){
//
//        Character hero = new Character(((new Vec2(0,0))),1,1,1,1,1,0,1,1,1,0);
//        setHero(hero);
//        hero.damage(30);
//        Assert.assertTrue(hero.getHealth() < hero.getMaxHealth());
//    }
//
//
//
//
//    @Test
//    public void testGroundCollision()
//    {
//        Character hero = new Character(new Vec2(768,200));
//        setHero(hero);
//        for(int i = 0; i < 240; i++){
//            simulatedWorld.frame(0.016f);
//        }
//
//        Vec4 collisionVariables = CollisionManager.getInstance().getDistanceToCollission(hero);
//        Assert.assertTrue(almostEqual(hero.getPosition().y, collisionVariables.x));
//    }
//
//    @Test
//    public void testCoinCollection() {
//        Character hero = new Character(new Vec2(768, 450));
//        setHero(hero);
//        simulatedWorld.addWorldObject(new CoinObject(new Vec2(768,450)));
//        Assert.assertTrue(hero.getCoins() == 0);
//        simulatedWorld.frame(1);
//        Assert.assertTrue(hero.getCoins() > 0);
//
//    }
//
//
//    @Test
//    public void testPurchaseUpgrade(){
//        Character hero = new Character(new Vec2(0,0),130,0,0,0,0,0,0,0,0,0);
//        Shop shop = new Shop(hero);
//        String upgradeName = "Speed";
//        int before1 = shop.getUpgList().get(upgradeName).getLevel();
//        shop.purchaseUpgrade(upgradeName);
//        int after1 = shop.getUpgList().get(upgradeName).getLevel();
//        String message = "Upgrade level before purchase was " + before1 + ". Upgrade level after is " + after1+".";
//        Assert.assertTrue(message, before1 + 1 == after1);
//        int before2 = shop.getUpgList().get(upgradeName).getLevel();
//
//        //At this point the hero should be too poor to afford more speed upgrades
//        shop.purchaseUpgrade(upgradeName);
//        int after2 = shop.getUpgList().get(upgradeName).getLevel();
//        message = "Upgrade level before purchase was " + before2 + ". Upgrade level after is " + after2+".";
//        Assert.assertTrue(message, after2 == before2);
//        Assert.assertTrue(shop.getPrice(shop.getUpgList().get(upgradeName)) > hero.getCoins());
//
//        //Gave hero more coins, checking that upgrading beyond level 1 works
//        shop.purchaseUpgrade(upgradeName);
//        Assert.assertTrue("Upgrade level should be 2,  is now: " + shop.getUpgList().get(upgradeName).getLevel()
//                , shop.getUpgList().get(upgradeName).getLevel() == 2);
//
//
//        //Change upgrade, making sure it doesn't have the level of the previous one for some reason
//        upgradeName = "Looting";
//        shop.purchaseUpgrade(upgradeName);
//        Assert.assertTrue(shop.getUpgList().get(upgradeName).getLevel() == 1);
//
//
//    }
//
//    @Test
//    public void testRegeneration(){
//        Enemy monster = new Enemy(new Vec2(1,1), new Vec2(64,64), 1,0,0,0,0,0,0,0);
//        simulatedWorld.addWorldObject(monster);
//        Assert.assertTrue(monster.getHealth() == monster.getMaxHealth());
//        monster.damage(50);
//        simulatedWorld.frame(1);
//
//        double lastdmg = monster.getHealth();
//        Assert.assertTrue(monster.getHealth() < monster.getMaxHealth());
//
//        simulatedWorld.frame(1);
//        Assert.assertTrue(monster.getHealth() > lastdmg);
//        lastdmg = monster.getHealth();
//        simulatedWorld.frame(1);
//        Assert.assertTrue(monster.getHealth() > lastdmg);
//        lastdmg = monster.getHealth();
//        simulatedWorld.frame(1);
//        Assert.assertTrue(monster.getHealth() > lastdmg);
//    }
//
//
//
//    @Test
//    public void testSpeedUpgrade() {
//        // Compares the movement distance of two levels of speed during a 0.16f frame tick
//        //If everything works the higher speed level should get further in a single tick than the lower speed level.
//        simulatedWorld.setInput(new InputEmpty(true,false,false,false));
//        Character hero = new Character(new Vec2(0,0),1,1,0,0,0,0,0,0,0,0);
//        setHero(hero);
//        float charPos1 = hero.getPosition().x;
//        simulatedWorld.frame(0.16f);
//        float charPos2 = hero.getPosition().x;
//        float deltavalue1 = charPos2-charPos1;
//        hero.getUpgrades().get("Speed").addLevel(); //We know this works since it's a part of testPurchaseUpgrade.
//        simulatedWorld.frame(0.16f);
//        float charPos3 = hero.getPosition().x;
//        float deltavalue2 = charPos3-charPos2;
//        Assert.assertTrue(deltavalue2 > deltavalue1);
//
//    }
//
//    @Test
//    public void testJumpLvl() {
//// test different character with different speedUpgradeLvls and see that a higher lvl runs faster.
//        Character hero = new Character(new Vec2(768,64),0,0,0,0,0,0,0,0,0,0);
//        setHero(hero);
//        int oldheight = hero.getJumpAcceleration();
//        hero.addUpgrade("JumpH",new JumpH(1));
//        simulatedWorld.frame(0.016f);
//        Assert.assertTrue(hero.getJumpAcceleration() > oldheight);
//
//
//            simulatedWorld.frame(0.016f);
//
//        }
//
//    @Test
//    public void testUpgradesExist(){
//        Character hero = new Character((new Vec2(0,0)));
//        setHero(hero);
//        Assert.assertTrue(hero.getUpgrades().get("Health").getValueInt() == 100);
//        Assert.assertTrue(hero.getUpgrades().get("Speed").getValueInt() == 500);
//        Assert.assertTrue(hero.getUpgrades().get("JumpH").getValueInt() == 800);
//        Assert.assertTrue(hero.getUpgrades().get("Regeneration").getValueInt() == 1);
//        Assert.assertTrue(hero.getUpgrades().get("CHC").getValueDouble() == 0);
//        Assert.assertTrue(hero.getUpgrades().get("CHD").getValueDouble() == 2);
//        Assert.assertTrue(hero.getUpgrades().get("Melee").getValueDouble() == 1);
//        Assert.assertTrue(hero.getUpgrades().get("Hermes").getValueInt() == 1);
//    }
//
//
// }
//
