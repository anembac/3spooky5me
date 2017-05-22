package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Utils;
import com.tda367.infinityrun.Math.Vec2;

import java.util.List;

public class Hazard extends WorldObject
{
    final float range;
    final float dps;
    public Hazard(Vec2 position, Vec2 bounds, float dps) {
        super(position, bounds);
        range = Vec2.distance(position, Utils.getCenter(this)) * 3;
        this.dps = dps;
    }

    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        super.frame(dt, heroX, heroY, state);
        List<WorldObject> objects = CollisionManager.getInstance().getKNearest(this, 10);
        for(WorldObject wo : objects)
        {
            if(Utils.distance(this, wo) < range && wo instanceof LivingObject)
            {
                ((LivingObject)wo).damage(dps*dt);
            }
        }
    }
}