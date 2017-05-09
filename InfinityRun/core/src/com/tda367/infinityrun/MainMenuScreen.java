package com.tda367.infinityrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenuScreen implements Screen { //this class creates the main menu screen, as shown in
                                                // https://github.com/libgdx/libgdx/wiki/Extending-the-simple-game
    final InfinityRun game;
    Stage mainMenuStage = new Stage();
    OrthographicCamera camera;

    
    Texture packedtexture = new Texture(Gdx.files.internal("testpack1.png"));
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    TextButton.TextButtonStyle menuButtonStyle;
    TextButton newCharButton;
    TextButton loadCharButton;
    VerticalGroup buttonGroup;


    public MainMenuScreen(final InfinityRun game) {
        this.game = game;
        Gdx.input.setInputProcessor(mainMenuStage);
        menuButtonStyle
                = new TextButton.TextButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable, game.font);
        newCharButton = new TextButton("NEW CHARACTER", menuButtonStyle);
        loadCharButton = new TextButton("LOAD CHARACTER", menuButtonStyle);
        buttonGroup = new VerticalGroup();
        buttonGroup.space(10);
        buttonGroup.setX(1600/2-buttonGroup.getWidth()/2);
        buttonGroup.setY(900/2-buttonGroup.getHeight()/2);
        buttonGroup.addActor(newCharButton);
        buttonGroup.addActor(loadCharButton);

        //ADD TO STAGE

        mainMenuStage.addActor(buttonGroup);

        //Camera stuff
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mainMenuStage.draw();

        //Batch&Font rendering
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to InfinityRun PRE-ALPHA!!! ", 100, 150);
        game.font.draw(game.batch, "Press \"NEW CHARACTER\" to begin!", 100, 100);
        game.batch.end();


        if(newCharButton.isPressed()){
            this.dispose();
            game.setScreen(new GameScreen(game));
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

        mainMenuStage.clear();
        buttonGroup.clear();
        mainMenuStage.dispose();
        atlas.dispose();
        packedtexture.dispose();

    }


}
