package spooky;

/**
 * Created by Mikael on 4/4/2017.
 * this should be the mainclass of the entire game, we could have the main method here.
 */
public class Game {
    boolean gameRunning = false;
    World world = null;

    public Game()
    {

    }

    /*
        This method should be called upon the user clicking start game and select difficuty? or other options.
     */
    public void startGame()
    {
        world = new World(/* required parameters to create the correct world*/);
        gameRunning = true;
    }

    /*
        Takes delta time as argument i case of laggs and stuffs.
     */
    public void frame(double dt)
    {
        // When the game is running we should frame the world
        if(gameRunning)
        {
            world.frame(dt);
        }
    }

    public void render()
    {
        if(gameRunning)
        {
            world.render();
        }
    }
}
