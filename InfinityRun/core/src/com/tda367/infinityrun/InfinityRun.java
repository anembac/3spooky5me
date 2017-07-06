package com.tda367.infinityrun;

import com.badlogic.gdx.Game;
import com.tda367.infinityrun.Controller.ScreenManager;
import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Model.Shop;
import com.tda367.infinityrun.Model.TextbasedWorldGenerator;
import com.tda367.infinityrun.Model.World;
import com.tda367.infinityrun.Utils.ScreenStates;
import java.util.Observable;
import java.util.Observer;
import static com.tda367.infinityrun.Utils.Constants.newGame;

/*
* Creates instances of certain model objects needed for the game to function.
*
* NOTE:
* In future might manage saving because it's the only class that has access to all needed information by default,
* although that property could also potentially lead to it becoming a god class, refactor into several classes if
* needed.
* TODO: Refactor or delete the note above once potential issues become clearer
*/
public class InfinityRun extends Game implements Observer {
    private ScreenManager screenManager;
    private TextbasedWorldGenerator tbWorldGen;
    private Character hero;
    private World world;
    private Shop shop;


    @Override
    public void create() {
        //World creation
        tbWorldGen = new TextbasedWorldGenerator();
        hero = new Character();
        world = new World(tbWorldGen, hero);
        shop = new Shop(hero);
        screenManager = new ScreenManager(this, world, shop);
        screenManager.switchToScreen(ScreenStates.MainMenuScreen);
        observeNewGameScreens();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals(newGame)){
            create();
        }
    }

//    private void newGame(){
//        hero = new Character();
//        world = new World(tbWorldGen, hero);
//        shop = new Shop(hero);
//        screenManager = new ScreenManager(this, world, shop);
//        screenManager.switchToScreen(ScreenStates.MainMenuScreen);
//        observeNewGameScreens();
//    }

    /*
    * This method makes InfinityRun observe GameScreen and PauseMenuScreen in order to be notified of when a new game
    * should be started.
    */

    private void observeNewGameScreens(){
        screenManager.getGameScreen().addObserver(this);
        screenManager.getPauseMenuScreen().addObserver(this);

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
