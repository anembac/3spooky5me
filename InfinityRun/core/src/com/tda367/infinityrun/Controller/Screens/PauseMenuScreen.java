package com.tda367.infinityrun.Controller.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Utils.ScreenStates;
import com.tda367.infinityrun.View.Screens.PauseMenuDrawer;

import java.util.Observable;

import static com.tda367.infinityrun.Utils.Constants.exitGame;
import static com.tda367.infinityrun.Utils.Constants.newGame;


public class PauseMenuScreen extends Observable implements Screen {
    private final Stage pauseStage = new Stage();

    //These buttons do not yet have any associated graphics, and are therefore not to be considered part of the view.
    private final VCButton exitButton = new VCButton();
    private final VCButton backToMenuButton = new VCButton();
    private final VCButton unPauseButton = new VCButton();
    private VCButton[] vcButtons = {exitButton, backToMenuButton, unPauseButton};
    private final GameScreen masterScreen;
    private PauseMenuDrawer pauseMenuDrawer;

    public PauseMenuScreen(GameScreen gs) {
        masterScreen = gs;
        VerticalGroup buttonGroup = new VerticalGroup();
        buttonGroup.addActor(exitButton);
        buttonGroup.addActor(backToMenuButton);
        buttonGroup.addActor(unPauseButton);
        buttonGroup.space(10);
        buttonGroup.setPosition(1600 / 2 - buttonGroup.getMaxWidth(), 900 / 2 - buttonGroup.getMaxHeight());
        pauseStage.addActor(buttonGroup);

        pauseMenuDrawer = new PauseMenuDrawer(buttonGroup, vcButtons);
    }


    private void buttonClickedCheck() {
        //Unpause
        if (vcButtons[2].isChecked()) {
            vcButtons[2].setChecked(false);
            setChanged();
            notifyObservers(ScreenStates.GameScreen); //Notifies ScreenManager

        }
        //Exit to main menu
        if (vcButtons[1].isPressed()) {
            setChanged();
            notifyObservers(newGame); //Notifies InfinityRun
            masterScreen.dispose();
            this.dispose();
        }
        //Exit game
        if (vcButtons[0].isPressed()) {
            setChanged();
            notifyObservers(exitGame); //Notifies InfinityRun
            this.dispose();
        }
    }

    @Override
    public void show() {
        pauseMenuDrawer.show();
        Gdx.input.setInputProcessor(pauseStage);
    }

    @Override
    public void render(float delta) {
        pauseMenuDrawer.draw(delta);

        buttonClickedCheck();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
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
        pauseStage.dispose();
        deleteObservers();

    }
}
