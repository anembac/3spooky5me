package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Controller.InputState;
import com.tda367.infinityrun.Math.Vec2;

import java.util.List;

public class Hazard extends WorldObject {
    private final float range;
    private final float dps;

    protected Hazard(Vec2 position, Vec2 bounds, float dps) {
        super(position, bounds);
        range = Vec2.distance(position, WOWrapper.worldObjectCenter(this)) * 3;
        this.dps = dps;
    }

    @Override
    public void frame(float dt, float heroX, float heroY, InputState state) {
        super.frame(dt, heroX, heroY, state);
        List<WorldObject> objects = CollisionManager.getInstance().getKNearest(this, 10);
        for (WorldObject wo : objects) {
            if (WOWrapper.centerDistance(this, wo) < range && wo instanceof LivingObject) {
                ((LivingObject) wo).damage(dps * dt);
            }
        }
    }
}