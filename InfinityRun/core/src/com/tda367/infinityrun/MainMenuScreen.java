package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen { //this class creates the main menu screen, as shown in
                                                // https://github.com/libgdx/libgdx/wiki/Extending-the-simple-game
    final InfinityRun game;

    OrthographicCamera camera;

    public MainMenuScreen(final InfinityRun game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to InfinityRun PRE-ALPHA!!! ", 100, 150);
        game.font.draw(game.batch, "Press any key to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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

    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
