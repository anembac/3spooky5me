package com.tda367.infinityrun.Controller.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tda367.infinityrun.Model.World;
import com.tda367.infinityrun.Utils.ScreenStates;
import com.tda367.infinityrun.View.Screens.GameScreenDrawer;


import java.util.HashMap;
import java.util.Observable;

import static com.tda367.infinityrun.Utils.Constants.newGame;

public class GameScreen extends Observable implements Screen {
    private final Stage gameStage = new Stage();
    private GameScreenDrawer gameScreenDrawer;
    protected World world;


    //Constructor
    public GameScreen(World world) {
        this.world = world;
        gameScreenDrawer = new GameScreenDrawer(world);
    }

    @Override
    public void show() {
        gameScreenDrawer.show();
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        world.frame(delta);
        gameScreenDrawer.draw(delta);
        if (world.gameDone()) {
            dispose();
            setChanged();
            notifyObservers(newGame); //Notifies InfinityRun
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            setChanged();
            notifyObservers(ScreenStates.ShopScreen);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            setChanged();
            notifyObservers(ScreenStates.PauseMenuScreen);
        }
    }


    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        gameStage.dispose();
        gameScreenDrawer.dispose();
    }
}
