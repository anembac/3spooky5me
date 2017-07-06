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
    private final VerticalGroup buttonGroup;
    private LinkedList<IDrawnByDrawer> vcButtons = new LinkedList<IDrawnByDrawer>();
    private MainMenuDrawer mainMenuDrawer;


    public MainMenuScreen() {

        newCharButton = new VCButton();
        loadCharButton = new VCButton();
        exitButton = new VCButton();
        vcButtons.add(newCharButton);
        vcButtons.add(loadCharButton);
        vcButtons.add(exitButton);

        exitButton.setPosition(1400, 100);
        buttonGroup = new VerticalGroup();
        buttonGroup.space(30); //space between buttons in group
        buttonGroup.setX(1600 / 2 - buttonGroup.getWidth() / 2);
        buttonGroup.setY(900 / 2 - buttonGroup.getHeight() / 2);
        buttonGroup.addActor(newCharButton);
        buttonGroup.addActor(loadCharButton);


        //ADD TO STAGE
        for(IDrawnByDrawer b : vcButtons){ //only VCButtons are in the vcButtons list, so casting below is fine.
            mainMenuStage.addActor((VCButton)b);
        }
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
