package com.tda367.infinityrun;

import java.util.List;

/**
 * Created by Jacob on 4/3/2017.
 */
public class World {

    private List<WorldObject> worldObjects;
    private double difficulty = 1.0;


    public void increaseDifficulty (double difficulty){
        this.difficulty = difficulty + 0.05;
    }



    //to become a method for adding the created rooms to the world. will require some sort of magic box algorithm to not break dimensions as we know it.
    public void addRoomObject(WorldObject obj)
    {
        worldObjects.add(obj);
    }

//    public void frame(double dt)
//    {
//        for(WorldObject obj : worldObjects)
//        {
//            obj.frame(dt);
//        }
//    }
//
//
//    public void render()
//    {
//        for(WorldObject obj : worldObjects)
//        {
//
//            obj.render();
//        }
//    }

}
