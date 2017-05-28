package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Mikael on 5/24/2017.
 */
public class TestingWorldGenerator implements WorldGenerator {
    private final HashSet<IndexPoint> madeRooms = new HashSet<IndexPoint>();
    @Override
    public List<WorldObject> generate(int x, int y) {
        if(madeRooms.contains(new IndexPoint(x,y))) return new ArrayList<WorldObject>();

        madeRooms.add(new IndexPoint(x,y));
        List<WorldObject> output = new ArrayList<WorldObject>();
        for(int i = 0; i < Constants.roomWidth; i++){
            output.add(new BrickObject(new Vec2(x*Constants.meter*Constants.roomWidth+i*Constants.meter,-Constants.meter)));
        }
        return output;
    }

    @Override
    public boolean roomExists(int x, int y) {
        return madeRooms.contains(new IndexPoint(x,y));
    }

    @Override
    public int getMaxDistance() {
        return maxdistance;
    }
}
