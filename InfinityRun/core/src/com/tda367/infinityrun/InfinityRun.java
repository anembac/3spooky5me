package com.tda367.infinityrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.infinityrun.Controller.IInput;
import com.tda367.infinityrun.Controller.InputGDX;
import com.tda367.infinityrun.Controller.ScreenManager;
import com.tda367.infinityrun.Controller.Screens.LoadScreen;
import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Model.Shop;
import com.tda367.infinityrun.Model.TextbasedWorldGenerator;
import com.tda367.infinityrun.Model.World;
import com.tda367.infinityrun.Utils.LoadCharacter;
import com.tda367.infinityrun.Utils.SaveCharacter;
import com.tda367.infinityrun.Utils.ScreenStates;
import java.util.Observable;
import java.util.Observer;

import static com.tda367.infinityrun.Utils.Constants.exitGame;
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
    private InputGDX input;
    private int loadID = -1;


    @Override
    public void create() {
        //World creation
        tbWorldGen = new TextbasedWorldGenerator();
        if(loadID>0){
            hero = LoadCharacter.loadCharacter(loadID);
        }else{
            hero = new Character();
        }

        world = new World(tbWorldGen, hero);
        shop = new Shop(hero);
        input = new InputGDX(world.getInputState());
        world.addObserver(input);
        screenManager = new ScreenManager(this, world, shop);

        if(loadID>0){
            screenManager.switchToScreen(ScreenStates.GameScreen);
        }else{
            screenManager.switchToScreen(ScreenStates.MainMenuScreen);
        }
        observeNewGameScreens();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals(newGame)){
            newGame();
        }
        if(o instanceof LoadScreen && arg instanceof Integer){
            loadGame((int)arg);
        }
        if(arg.equals(exitGame)){
            exitGame();
        }
    }

    /*
    * This method makes InfinityRun observe GameScreen and PauseMenuScreen in order to be notified of when a new game
    * should be started, and LoadScreen in order to be notified of which save to load.
    */

    private void observeNewGameScreens(){
        screenManager.getGameScreen().addObserver(this);
        screenManager.getPauseMenuScreen().addObserver(this);
        screenManager.getLoadScreen().addObserver(this);

    }

    private void newGame(){
        SaveCharacter.saveCharacter(hero, hero.getCharacterID());
        create();
    }

    private void exitGame(){
        SaveCharacter.saveCharacter(hero, hero.getCharacterID());
        System.out.println("Exiting Game...");
        Gdx.app.exit();
    }

    private void loadGame(int id){
        loadID = id;
        create();
        loadID = -1;
    }



    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
