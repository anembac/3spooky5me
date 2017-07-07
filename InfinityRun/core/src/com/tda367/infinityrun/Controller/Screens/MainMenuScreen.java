package com.tda367.infinityrun.Controller.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.tda367.infinityrun.View.IDrawnByDrawer;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Utils.LoadCharacter;
import com.tda367.infinityrun.Utils.ScreenStates;
import com.tda367.infinityrun.View.Screens.MainMenuDrawer;

import java.util.LinkedList;
import java.util.Observable;
//Separated
public class MainMenuScreen extends Observable implements Screen { //this class creates the main menu screen
    private final Stage mainMenuStage = new Stage();

    private final VCButton newCharButton;
    private final VCButton loadCharButton;
    private final VCButton exitButton;
    private LinkedList<IDrawnByDrawer> vcButtons = new LinkedList<IDrawnByDrawer>();
    private MainMenuDrawer mainMenuDrawer;


    public MainMenuScreen() {

        newCharButton = new VCButton();
        loadCharButton = new VCButton();
        exitButton = new VCButton();
        vcButtons.add(newCharButton);
        vcButtons.add(loadCharButton);
        vcButtons.add(exitButton);

        newCharButton.setPosition(800-newCharButton.getWidth()/2, 450-newCharButton.getHeight()/2);
        loadCharButton.setPosition(800-loadCharButton.getWidth()/2, 400-loadCharButton.getHeight()/2);
        exitButton.setPosition(1400, 100);




        //ADD TO STAGE
        mainMenuStage.addActor(newCharButton);
        mainMenuStage.addActor(loadCharButton);
        mainMenuStage.addActor(exitButton);

        //Screen needs to hold an instance of drawer
        mainMenuDrawer = new MainMenuDrawer(vcButtons);

        Gdx.input.setInputProcessor(mainMenuStage);

    }


    @Override
    public void render(float delta) {

        mainMenuDrawer.draw(delta);

        if (newCharButton.isPressed() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
            this.dispose();
        }
        if (loadCharButton.isPressed() && LoadCharacter.saveDataExists()) {
            setChanged();
            notifyObservers(ScreenStates.LoadScreen);
            this.dispose();
        }

        if (exitButton.isPressed()) {
            this.dispose();
            System.out.println("Exiting Game...");
            Gdx.app.exit();
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
    public void show() {
        mainMenuDrawer.show();
        Gdx.input.setInputProcessor(mainMenuStage);

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //disposal calls
        mainMenuStage.dispose();
        deleteObservers();

    }
}
