package test.com.tda367.infinityrun;

import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Model.Enemy;
import com.tda367.infinityrun.Model.MeleeWeapon;
import com.tda367.infinityrun.Utils.Math.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LivingObjectTest {

    private Character hero = new Character();

    @Test
   public void testEquipWeapon(){
        assertTrue(hero.getWeapon() != null, "" + hero.getWeapon());
        MeleeWeapon formerWeapon = hero.getWeapon();
        hero.equipWeapon();
        assertTrue(hero.getWeapon() != formerWeapon);

    }
}
