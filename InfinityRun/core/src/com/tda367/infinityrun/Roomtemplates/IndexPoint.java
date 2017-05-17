package com.tda367.infinityrun.Roomtemplates;

/**
 * Created by kaffe on 5/17/17.
 */
public class IndexPoint {

    int x;
    int y;

    public IndexPoint(int x, int y){this.x = x; this.y = y;}

    @Override
    public int hashCode() {
        return x+y*1000000;

    }


}
