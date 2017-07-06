package com.tda367.infinityrun.Controller.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.tda367.infinityrun.View.IDrawnByDrawer;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Utils.SaveCharacter;
import com.tda367.infinityrun.Utils.ScreenStates;
import com.tda367.infinityrun.View.Screens.PauseMenuDrawer;

import java.util.LinkedList;
import java.util.Observable;

import static com.tda367.infinityrun.Utils.Constants.newGame;

//separated
public class PauseMenuScreen extends Observable implements Screen {
    private final Stage pauseStage = new Stage();

    //These buttons do not yet have any associated graphics, and are therefore not to be considered part of the view.
    private final VCButton exitButton = new VCButton();
    private final VCButton backToMenuButton = new VCButton();
    private final VCButton unPauseButton = new VCButton();
    private LinkedList<IDrawnByDrawer> vcButtons = new LinkedList<IDrawnByDrawer>();
    private final GameScreen masterScreen;
    private PauseMenuDrawer pauseMenuDrawer;

    public PauseMenuScreen(GameScreen gs) {
        vcButtons.add(exitButton);
        vcButtons.add(backToMenuButton);
        vcButtons.add(unPauseButton);
        masterScreen = gs;
        VerticalGroup buttonGroup = new VerticalGroup();
        buttonGroup.addActor(exitButton);
        buttonGroup.addActor(backToMenuButton);
        buttonGroup.addActor(unPauseButton);
        buttonGroup.space(10);
        buttonGroup.setPosition(1600 / 2 - buttonGroup.getMaxWidth(), 900 / 2 - buttonGroup.getMaxHeight());
        pauseStage.addActor(buttonGroup);

        pauseMenuDrawer = new PauseMenuDrawer(vcButtons);
    }


    private void buttonClickedCheck() {
        if (unPauseButton.isPressed()) {
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
            this.dispose();
        }

        if (backToMenuButton.isPressed()) {
            //TODO rewrite saving to remove screen-level dependencies on the world class
            SaveCharacter.saveCharacter(masterScreen.world.getHero(), masterScreen.world.getHero().getCharacterID());
            setChanged();
            notifyObservers(newGame);
            masterScreen.dispose();
            this.dispose();
        }

        if (exitButton.isPressed()) {
            SaveCharacter.saveCharacter(masterScreen.world.getHero(), masterScreen.world.getHero().getCharacterID());
            masterScreen.dispose();
            this.dispose();
            System.out.println("Exiting Game...");
            Gdx.app.exit();

        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(pauseStage);
    }

    @Override
    public void render(float delta) {
        pauseMenuDrawer.draw(delta);

        buttonClickedCheck();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
            this.dispose();
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
