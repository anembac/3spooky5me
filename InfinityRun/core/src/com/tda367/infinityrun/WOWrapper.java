package com.tda367.infinityrun;


import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;

//Wrapper class for worldobjects -> Utils so that utils doesn't need to contain any worldobjects.
// (Utils shouldn't contain model code.)
public class WOWrapper {

    public static Vec2 worldObjectCenter(WorldObject wo){
       return  Utils.getCenter(wo.getPosition(),wo.getDrawingRect().bounds);
    }

    public static float centerDistance(WorldObject A, WorldObject B){
        return Utils.distance(worldObjectCenter(A), worldObjectCenter(B));
    }
}
