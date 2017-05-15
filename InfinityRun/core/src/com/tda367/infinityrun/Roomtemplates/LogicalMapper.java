package com.tda367.infinityrun.Roomtemplates;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by kaffe on 5/12/17.
 */
public class LogicalMapper extends RoomTemplate {

    HashMap<Point, RoomTemplate> rooms;
    private int y = 0;
    private int x = 0;


    private int checkedL;
    private int checkedU;
    private int checkedR;
    private int checkedD;




    /*
    returns -1 for no room exist, 0 for a blocked path and 1 for path.
    (same for all of the check methods)
     */
    private int checkUp(int x, int y) {
        try {
            if (rooms.get(new Point(x, y + 1)).u) {
            }
        } catch (NullPointerException n) {
            return 0;
        }
        if (rooms.get(new Point(x, y + 1)).u) {
            return 1;
        } else {
            return -1;
        }
    }


    private int checkRight(int x, int y) {
        try {
            if (rooms.get(new Point(x + 1, y)).r) {
            }
        } catch (NullPointerException n) {
            return 0;
        }
        if (rooms.get(new Point(x + 1, y)).r) {
            return 1;
        } else {
            return -1;
        }
    }

    private int checkDown(int x, int y) {
        try {
            if (rooms.get(new Point(x, y - 1)).d) {
            }
        } catch (NullPointerException n) {
            return 0;
        }
        if (rooms.get(new Point(x, y - 1)).d) {
            return 1;
        } else {
            return -1;
        }
    }

    private int checkLeft(int x, int y) {
        try {
            if (rooms.get(new Point(x - 1, y)).l) {
            }
        } catch (NullPointerException n) {
            return 0;
        }
        if (rooms.get(new Point(x - 1, y)).l) {
            return 1;
        } else {
            return-1;
        }
    }


    private void roomRandomizer() {

        rooms.put(new Point(1, 1), new RoomURL());
    }


    public void getSurrounding(){

        checkedL =checkLeft(x,y);
        checkedU = checkUp(x, y);
        checkedR = checkRight(x, y);
        checkedD = checkDown(x, y);





    }


    public void mapper() {
    }

}


