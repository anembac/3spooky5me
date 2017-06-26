package com.tda367.infinityrun.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.tda367.infinityrun.Utils.SaveCharacter;
import com.tda367.infinityrun.Utils.ScreenStates;

import java.util.Observable;

public class PauseMenuScreen extends Observable implements Screen {
    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private final Stage pauseStage = new Stage();

    //These buttons do not yet have any associated graphics, and are therefore not to be considered part of the view.
    private final Button exitButton = new Button();
    private final Button backToMenuButton = new Button();
    private final Button unPauseButton = new Button();
    private final GameScreen masterScreen;

    public PauseMenuScreen(GameScreen gs) {
        masterScreen = gs;
        VerticalGroup buttonGroup = new VerticalGroup();
        buttonGroup.addActor(exitButton);
        buttonGroup.addActor(backToMenuButton);
        buttonGroup.addActor(unPauseButton);
        buttonGroup.space(10);
        buttonGroup.setPosition(1600 / 2 - buttonGroup.getMaxWidth(), 900 / 2 - buttonGroup.getMaxHeight());
        pauseStage.addActor(buttonGroup);
        addObserver(ScreenManager, InfinityRun);

    }


    private void buttonClickedCheck() {
        if (unPauseButton.isPressed()) {
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
            this.dispose();
        }

        if (backToMenuButton.isPressed()) {   //broken; graphics disposed, collision with old objects still occurs
            SaveCharacter.saveCharacter(masterScreen.world.getHero(), masterScreen.world.getHero().getCharacterID());
            setChanged();
            notifyObservers("new");
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
        buttonClickedCheck();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pauseStage.draw();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //this.dispose();
            masterScreen.game.setScreen(masterScreen);
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
        batch.dispose();
        font.dispose();
        atlas.dispose();
        pauseStage.dispose();

    }
}
