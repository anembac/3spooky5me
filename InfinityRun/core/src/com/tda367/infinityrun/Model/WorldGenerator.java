package com.tda367.infinityrun.Model;

import java.util.List;

/*
interface for worldgeneration.
 */
public interface WorldGenerator {
    List<WorldObject> generate(int x, int y);

    boolean roomExists(int x, int y);

    int maxdistance = 0;

    int getMaxDistance();
}
