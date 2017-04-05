package spooky;

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

    public void addWorldObject(WorldObject obj)
    {
        worldObjects.add(obj);
    }

    public void frame(double dt)
    {
        for(WorldObject obj : worldObjects)
        {
            obj.frame(dt);
        }
    }

    public void render()
    {
        for(WorldObject obj : worldObjects)
        {
            obj.render();
        }
    }

}
