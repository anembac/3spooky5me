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
        newGame();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("new")){
            newGame();
        }
    }

    private void newGame(){
        hero = new Character();
        world = new World(tbWorldGen, hero);
        shop = new Shop(hero);
        screenManager = new ScreenManager(this, world, shop);
        screenManager.switchToScreen(ScreenStates.MainMenuScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
