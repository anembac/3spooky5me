package com.tda367.infinityrun.Model;

import java.util.List;

/**
 * Created by Mikael on 5/22/2017.
 */
public interface WorldGenerator {
    List<WorldObject> generate(int x, int y);

    boolean roomExists(int x, int y);

    int maxdistance = 0;

    int getMaxDistance();
}
