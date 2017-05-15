package com.tda367.infinityrun.Roomtemplates;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by kaffe on 5/12/17.
 */
public class LogicalMapper extends RoomTemplate {

        HashMap<Point,RoomTemplate> rooms;



    private int checkUp(int x, int y) {
        try {
            if(rooms.get(new Point(x - 1, y)).u) {
            }
        } catch (NullPointerException n) {
            return -1;
        }
        if(rooms.get(new Point(x - 1, y)).u) {
            return 1;
        } else {
            return 0;
        }
    }


    private int checkRight(int x, int y) {
        try {
            if(rooms.get(new Point(x - 1, y)).r) {
            }
        } catch (NullPointerException n) {
            return -1;
        }
        if(rooms.get(new Point(x - 1, y)).r) {
            return 1;
        } else {
            return 0;
        }
    }

    private int checkDown(int x, int y) {
        try {
            if(rooms.get(new Point(x - 1, y)).d) {
            }
        } catch (NullPointerException n) {
            return -1;
        }
        if(rooms.get(new Point(x - 1, y)).d) {
            return 1;
        } else {
            return 0;
        }
    }

    private int checkLeft(int x, int y) {
        try {
            if(rooms.get(new Point(x - 1, y)).l) {
            }
        } catch (NullPointerException n) {
            return -1;
        }
        if(rooms.get(new Point(x - 1, y)).l) {
            return 1;
        } else {
            return 0;
        }
    }


        private void roomRandomizer(){

            rooms.put(new Point(1,1),new RoomURL());
        }

/*
    public void getSurrounding(){




        if(this.coordinate.Y+1 != null){
            this.u=this.coordinate.Y+1.isd();

        }

        if(this.coordinate.Y-1 != null){
            this.d=this.coordinate.Y+1.isu();

        }

        if(this.coordinate.X+1 != null){
            this.r=this.coordinate.Y+1.isl();

        }
        if(this.coordinate.Y+1 != null){
            this.l=this.coordinate.Y+1.isr();

        }

    }*/


    public void mapper(){
    }

    }


