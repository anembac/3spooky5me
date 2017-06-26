package com.tda367.infinityrun.Controller;
import com.badlogic.gdx.Game;
import com.tda367.infinityrun.Controller.Screens.*;
import com.tda367.infinityrun.Model.*;
import com.tda367.infinityrun.Utils.ScreenStates;

import java.util.Observable;
import java.util.Observer;

/*
* The purpose of this class is to (not surprisingly) manage the screens of the game. This will allow us to centralize
* the creation of screens so that a single high-level class as access to them all, which together with the ScreenStates
* enum removes the need for several lower-level classes to all have access to them.

* As a result we will be able to switch back and forth between screens using the same instances and variables,
* something that was not possible in earlier iterations where new screens had to be created due to visibility issues.
*/
public class ScreenManager implements Observer {
private Game game;
private GameScreen gameScreen;
private LoadScreen loadScreen;
private MainMenuScreen mainMenuScreen;
private PauseMenuScreen pauseMenuScreen;
private ShopScreen shopScreen;

    public ScreenManager(Game game, World world, Shop shop){
        this.game = game;
        gameScreen  = new GameScreen(game, world);
        loadScreen = new LoadScreen(game);
        pauseMenuScreen = new PauseMenuScreen(gameScreen);
        shopScreen = new ShopScreen(shop, gameScreen);
        mainMenuScreen = new MainMenuScreen(game);

        //The following lines makes the ScreenManager listen to the screens, Is this the right way to do this?
        gameScreen.addObserver(this);
        loadScreen.addObserver(this);
        pauseMenuScreen.addObserver(this);
        shopScreen.addObserver(this);
        mainMenuScreen.addObserver(this);
    }


    //Takes a string as argument
    public void switchToScreen(ScreenStates screen){
        switch(screen){
            case GameScreen: game.setScreen(gameScreen);
            break;
            case LoadScreen: game.setScreen(loadScreen);
            break;
            case MainMenuScreen: game.setScreen(mainMenuScreen);
            break;
            case PauseMenuScreen: game.setScreen(pauseMenuScreen);
            break;
            case ShopScreen: game.setScreen(shopScreen);
            default:
        }
    }


    /*
    * InfinityRun needs access to these two classes as they might result
    * in a situation where a newGame() call is needed.
    */
    public GameScreen getGameScreen(){
        return gameScreen;
    }

    public PauseMenuScreen getPauseMenuScreen(){
        return pauseMenuScreen;
    }

    @Override
    public void update(Observable o, Object arg) {

        switchToScreen(((ScreenStates)arg));
    }
}
