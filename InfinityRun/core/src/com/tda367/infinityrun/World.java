package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.BrickObject;
import com.tda367.infinityrun.RoomTiles.Platform;
import com.tda367.infinityrun.RoomTiles.SpikeObject;
import com.tda367.infinityrun.Roomtemplates.LogicalMapper;
import com.tda367.infinityrun.Roomtemplates.RoomTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 4/3/2017.
 */
public class World {

    //private List<WorldObject> worldObjects;
    private List<WorldObject> worldObjects;
    private KdTree<WorldObject> kdTree = new KdTree<WorldObject>();
    private double difficulty = 1.0;
    IInput input = null;
    private WorldObject hero = null;
    private LogicalMapper logicalMapper = new LogicalMapper();

    public void increaseDifficulty(double difficulty) {
        this.difficulty = difficulty + 0.05;
    }

    public World() {

        worldObjects = new ArrayList<WorldObject>();

        input = new Input();

    }

    public void generateWorld() {
        int x = (int)(Math.floor((hero.getPosition().x+hero.getDrawingRect().bounds.x/2) / (Constants.roomWidth*Constants.meter)));
        int y = (int)(Math.floor((hero.getPosition().y+hero.getDrawingRect().bounds.y/2) / (Constants.roomHeight*Constants.meter)));
        //System.out.println(x + "  " + y + "  " + (hero.getPosition().x+hero.getDrawingRect().bounds.x/2) / (Constants.roomWidth*Constants.meter));

      //creates the first room
        addRoomIfItDoesntExist(x - 0, y-0);
        //creates the non-corner rooms
        addRoomIfItDoesntExist(x+1,y);
        addRoomIfItDoesntExist(x-1,y);
        addRoomIfItDoesntExist(x-0,y-1);
        addRoomIfItDoesntExist(x-0,y+1);


        //creates diagonal rooms
        addRoomIfItDoesntExist(x-1,y-1);

        addRoomIfItDoesntExist(x-1,y+1);



        addRoomIfItDoesntExist(x+1,y-1);

        addRoomIfItDoesntExist(x+1,y+1);
    }

    public List<WorldObject> getWorldObjects()
    {
        return worldObjects;
    }


    public void frame(float dt) {
        // 25 15

        generateWorld();


        input.collectInput();
        for (WorldObject obj : worldObjects) {
            obj.frame(dt, input.getInput());
        }
        for (WorldObject obj : worldObjects) {
            if(obj instanceof MovableObject)
            {
                // This object might have a different position now, the easiest way is to remove it and then re add it.
                CollisionManager.getInstance().updatePosition(obj);
            }
        }
    }

    // to add the player etc to the world.
    public void addWorldObject(WorldObject obj) {
        //for(WorldObject obj:roomObjects) {

        CollisionManager.getInstance().addWorldObject(obj);
        worldObjects.add(obj);
        //}
    }

    public void setHero(WorldObject obj)
    {
        this.hero = obj;
    }

    public void addWorldObjects(List<WorldObject> objs)
    {
        for(WorldObject wo : objs)
        {
            addWorldObject(wo);
        }
    }

    public void addRoomIfItDoesntExist(int x, int y)
    {
        if(!logicalMapper.roomExists(x,y))
        {
            addWorldObjects(logicalMapper.mapper(x,y));
        }
    }


    /*public void addHero(WorldObject obj) {
        worldObjects.add(obj);
    }*/

}
