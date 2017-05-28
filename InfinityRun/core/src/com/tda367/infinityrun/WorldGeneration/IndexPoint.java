package com.tda367.infinityrun.WorldGeneration;

/**
 * Created by kaffe on 5/17/17.
 */
class IndexPoint {

    private final int x;
    private final int y;

    public IndexPoint(int x, int y){this.x = x; this.y = y;}

    @Override
    public int hashCode() {
        return x*104119+y*104729;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        IndexPoint point = (IndexPoint) obj;
        return point.x == this.x && point.y == this.y && point.hashCode() == this.hashCode();
    }

}
