package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.tda367.infinityrun.SaveCharacter;

class PauseMenuScreen implements Screen {
    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private final Stage pauseStage = new Stage();
    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    private final TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    private final TextButton.TextButtonStyle menuButtonStyle =
            new TextButton.TextButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable, font);

    private final TextButton exitButton = new TextButton("SAVE AND EXIT", menuButtonStyle);
    private final TextButton backToMenuButton = new TextButton("SAVE AND GO TO MAIN MENU", menuButtonStyle);
    private final TextButton unPauseButton = new TextButton("UNPAUSE", menuButtonStyle);
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
        Gdx.input.setInputProcessor(pauseStage);

    }


    private void buttonClickedCheck() {
        if (unPauseButton.isPressed()) {
            masterScreen.game.setScreen(masterScreen);
            this.dispose();
        }

        if (backToMenuButton.isPressed()) {   //broken; graphics disposed, collision with old objects still occurs
            SaveCharacter.saveCharacter(masterScreen.world.getHero(), masterScreen.world.getHero().getCharacterID());
            masterScreen.game.setScreen(new MainMenuScreen(masterScreen.game)); //masterscreen.game is an instance of
            masterScreen.dispose();                                             //the InfinityRun class
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
