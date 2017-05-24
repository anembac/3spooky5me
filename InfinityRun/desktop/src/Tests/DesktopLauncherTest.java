package Tests;

import com.tda367.infinityrun.Character;
import com.tda367.infinityrun.InputEmpty;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.CoinObject;
import com.tda367.infinityrun.Roomtemplates.LogicalMapper;
import com.tda367.infinityrun.World;
import com.tda367.infinityrun.desktop.DesktopLauncher;
import junit.framework.TestCase;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Mikael on 5/23/2017.
*/
public class DesktopLauncherTest extends TestCase{
    int k;

    @Override
    protected void setUp() throws Exception
    {
        k = 2;
    }

    @Test
    public void testCoinCollection() {
        World world = new World();
        world.setInput(new InputEmpty());
        Character hero = new Character(new Vec2(0,0));
        world.addWorldObject(hero);
        world.setHero(hero);
        world.addWorldObject(new CoinObject(new Vec2(0,0)));
        assertTrue(hero.getCoins() == 0);
        world.frame(1);
        assertTrue(hero.getCoins() == 1);
    }
    @Test
    public void testgeneration(){
        World world = new World();
        LogicalMapper mapper = new LogicalMapper();
        mapper.addBaseroom();
        assertTrue(mapper.roomExists(0,0));
    }


}