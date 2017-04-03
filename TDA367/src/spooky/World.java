package spooky;

import java.util.List;

/**
 * Created by Jacob on 4/3/2017.
 */
public class World {

    private List<WorldObject> worldObjects;
    private double difficulty = 1.0;


    public void increaseDifficulty (double difficulty){
        this.difficulty = difficulty + 0.1;
    }

}
