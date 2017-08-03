package com.tda367.infinityrun.Model;

// class to create IndexPoints for the WorldGeneration, meaning that each point is supposed to be unique. This is done by multiplying two large prime numbers.

public class IndexPoint {

    private final int x;
    private final int y;

    public IndexPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
//hashcode to not get equivivalent room-id's
    @Override
    public int hashCode() {
        return x * 104119 + y * 104729;
    }

//compares id's
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        IndexPoint point = (IndexPoint) obj;
        return point.x == this.x && point.y == this.y && point.hashCode() == this.hashCode();
    }

}
