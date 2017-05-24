package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.Roomtemplates.LogicalMapper;

public class PauseMenuScreen implements Screen{
    SpriteBatch batch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    Stage pauseStage = new Stage();
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    TextButton.TextButtonStyle menuButtonStyle =
            new TextButton.TextButtonStyle(textureUpDrawable,textureDownDrawable,textureUpDrawable, font);

    TextButton exitButton = new TextButton("EXIT GAME", menuButtonStyle);
    TextButton backToMenuButton= new TextButton("MAIN MENU", menuButtonStyle);
    TextButton unPauseButton = new TextButton("UNPAUSE", menuButtonStyle);
    VerticalGroup buttonGroup = new VerticalGroup();
    GameScreen masterScreen;

    public PauseMenuScreen(GameScreen gs){
        masterScreen = gs;
        buttonGroup.addActor(exitButton);
        buttonGroup.addActor(backToMenuButton);
        buttonGroup.addActor(unPauseButton);
        buttonGroup.space(10);
        buttonGroup.setPosition(1600/2-buttonGroup.getMaxWidth(), 900/2-buttonGroup.getMaxHeight());
        pauseStage.addActor(buttonGroup);
        Gdx.input.setInputProcessor(pauseStage);

    }


    private void buttonClickedCheck(){
        if(unPauseButton.isPressed()){
            masterScreen.game.setScreen(masterScreen);
            this.dispose();

        }
        if(backToMenuButton.isPressed()){   //broken; graphics disposed, collision with old objects still occurs

            masterScreen.game.setScreen(new MainMenuScreen(masterScreen.game));
            masterScreen.dispose();
            this.dispose();
        }
        if(exitButton.isPressed()){
            masterScreen.dispose();
            this.dispose();
            System.out.println("Exiting Game...");
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        buttonClickedCheck();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pauseStage.draw();
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
