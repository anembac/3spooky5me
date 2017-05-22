package com.tda367.infinityrun.WeaponTypes;

import com.tda367.infinityrun.*;
import com.tda367.infinityrun.Math.Utils;

import java.util.List;

/**
 * Created by Mikael on 5/22/2017.
 */
public class AdminsDebugginStaff extends MeleeWeapon {
    public AdminsDebugginStaff() {
        super(20, 2, 2);
    }
    double ccd = 0;
    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        ccd -= dt;
        //super.frame(dt, heroX, heroY, state);
        if(state.attackPressed() && ccd < 0.0001)
        {
            List<WorldObject> objects = CollisionManager.getInstance().getKNearest(this.getParent(), 20);
            int i = 0;
            for(WorldObject wo : objects)
            {
                i++;
                float dist = Utils.distance(this.getParent(), wo);
                System.out.println(i + " : Distance is : " + dist + " and the class name is : " + wo.getClass().getName());
            }
            ccd = getCD();
        }

    }
}
