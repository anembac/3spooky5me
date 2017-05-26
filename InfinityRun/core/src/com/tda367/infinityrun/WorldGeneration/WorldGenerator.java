package com.tda367.infinityrun.WorldGeneration;

import com.tda367.infinityrun.WorldObject;

import java.util.List;

/**
 * Created by Mikael on 5/22/2017.
 */
public interface WorldGenerator {
    public List<WorldObject> generate(int x, int y );
    public boolean roomExists(int x, int y);
    public int maxdistance = 0;
    public int getMaxDistance();
}
