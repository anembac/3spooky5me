package Tests;

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

    @org.junit.Test
    public void testSomething() {
        assertTrue(k == 2);
    }
}