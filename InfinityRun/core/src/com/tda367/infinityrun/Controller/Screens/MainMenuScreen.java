package com.tda367.infinityrun.Controller.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Model.TextbasedWorldGenerator;
import com.tda367.infinityrun.Model.World;
import com.tda367.infinityrun.Utils.LoadCharacter;
import com.tda367.infinityrun.Utils.ScreenStates;

import java.util.Observable;

public class MainMenuScreen extends Observable implements Screen { //this class creates the main menu screen
    private final Stage mainMenuStage = new Stage();

    private final Button newCharButton;
    private final Button loadCharButton;
    private final Button exitButton;
    private final VerticalGroup buttonGroup;

    public MainMenuScreen() {

        newCharButton = new Button();
        loadCharButton = new Button();
        exitButton = new Button();
        exitButton.setPosition(1400, 100);

        buttonGroup = new VerticalGroup();
        buttonGroup.space(30); //space between buttons in group
        buttonGroup.setX(1600 / 2 - buttonGroup.getWidth() / 2);
        buttonGroup.setY(900 / 2 - buttonGroup.getHeight() / 2);
        buttonGroup.addActor(newCharButton);
        buttonGroup.addActor(loadCharButton);


        //ADD TO STAGE

        mainMenuStage.addActor(buttonGroup);
        mainMenuStage.addActor(exitButton);

    }


    @Override
    public void render(float delta) { //render is a strange name now that it doesn't render anything
        if (newCharButton.isPressed()) {
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
