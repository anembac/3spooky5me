//package com.tda367.infinityrun.WeaponTypes;
//import com.tda367.infinityrun.*;
//import java.util.List;
//
///**
// * Created by Mikael on 5/22/2017.
// */
//public class AdminsDebugginStaff extends MeleeWeapon {
//    public AdminsDebugginStaff() {
//        super(20, 2, 2);
//    }
//    private double ccd = 0;
//    @Override
//    public void frame(float dt, float heroX, float heroY, InputState state) {
//        ccd -= dt;
//        //super.frame(dt, heroX, heroY, state);
//        if(state.attackPressed() && ccd < 0.0001)
//        {
//            List<WorldObject> objects = CollisionManager.getInstance().getKNearest(this.getParent(), 20);
//            int i = 0;
//            int x = (int)(Math.floor((getPosition().x+getDrawingRect().bounds.x/2) / (Constants.roomWidth*Constants.meter)));
//            int y = (int)(Math.floor((getPosition().y+getDrawingRect().bounds.y/2) / (Constants.roomHeight*Constants.meter)));
//
//            for(WorldObject wo : objects)
//            {
//                i++;
//                float dist = WOWrapper.centerDistance(this.getParent(), wo);
//
//                System.out.println(i + " : Distance is : " + dist + " and the class name is : " + wo.getClass().getName());
//            }
//            System.out.println("x y " + x + " " + y);
//            ccd = getCD();
//        }
//
//    }
//}
