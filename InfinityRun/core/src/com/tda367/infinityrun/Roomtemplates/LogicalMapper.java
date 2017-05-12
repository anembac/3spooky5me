package com.tda367.infinityrun.Roomtemplates;

/**
 * Created by kaffe on 5/12/17.
 */
public class LogicalMapper extends RoomTemplate {

    public void grid() {
        int x;
        int y;
        double coordinate;
    }
        public void Coordinate (int xPos, int yPos){


    }

    // sets the value in (something in a coordinate) to that of the roomtemplate
    public void setRoom(boolean up, boolean left, boolean right, boolean down){
            this.setExitdown(down);
            this.setExitleft(left);
            this.setExitup(up);
            this.setExitright(right);

    }
/*
    public void getSurrounding(){

        if(this.coordinate.Y+1 != null){
            this.exitup=this.coordinate.Y+1.isExitDown();

        }

        if(this.coordinate.Y-1 != null){
            this.exitdown=this.coordinate.Y+1.isExitUp();

        }

        if(this.coordinate.X+1 != null){
            this.exitright=this.coordinate.Y+1.isExitLeft();

        }
        if(this.coordinate.Y+1 != null){
            this.exitleft=this.coordinate.Y+1.isExitRight();

        }

    }*/


    public void mapper(){


    }

    }


