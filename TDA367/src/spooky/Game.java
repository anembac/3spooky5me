package spooky;

import javax.swing.*;

/**
 * Created by Mikael on 4/4/2017.
 * this should be the mainclass of the entire game, we could have the main method here.
 */
public class Game extends JComponent{ //We don't want to use swing in the final product and will be replacing this with
    boolean gameRunning = false;        //an equivalent function in our graphics library of choice
    World world = null;
    Character player;
    MovementController mc;

    public Game()
    {
        player = new Character();
        mc = new MovementController(player);
        this.addKeyListener(mc);
//        startGame();

    }

    /*
        This method should be called upon the user clicking start game and select difficuty? or other options.
     */
    public void startGame()
    {

        world = new World(/* required parameters to create the correct world*/);

        gameRunning = true;
        System.out.println("Game started!");

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

    public Character getPlayer(){
        return this.player;
    }


}
