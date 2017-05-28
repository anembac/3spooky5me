package com.tda367.infinityrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.*;
import com.tda367.infinityrun.Character;
import com.tda367.infinityrun.Math.Vec2;

public class MainMenuScreen implements Screen { //this class creates the main menu screen
    private final InfinityRun game;
    private final Stage mainMenuStage = new Stage();
    private final OrthographicCamera camera;


    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final TextButton newCharButton;
    private final TextButton loadCharButton;
    private final TextButton exitButton;
    private final VerticalGroup buttonGroup;
    private final Label.LabelStyle labelStyle;
    private final Label instructions;


    public MainMenuScreen(final InfinityRun game) {
        this.game = game;
        Gdx.input.setInputProcessor(mainMenuStage);
        TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
        TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable, game.font);
        newCharButton = new TextButton("NEW CHARACTER", menuButtonStyle);
        loadCharButton = new TextButton("LOAD CHARACTER", menuButtonStyle);
        exitButton = new TextButton("EXIT GAME", menuButtonStyle);
        exitButton.setPosition(1400, 100);
        newCharButton.getLabel().setFontScale(2.3f);
        loadCharButton.getLabel().setFontScale(2.3f);
        buttonGroup = new VerticalGroup();
        buttonGroup.space(30); //space between buttons in group
        buttonGroup.setX(1600 / 2 - buttonGroup.getWidth() / 2);
        buttonGroup.setY(900 / 2 - buttonGroup.getHeight() / 2);
        buttonGroup.addActor(newCharButton);
        buttonGroup.addActor(loadCharButton);
        labelStyle = new Label.LabelStyle(game.font, new Color(9, 205, 218, 255));
        instructions = new Label("", labelStyle);
        instructions.setText(
                "Press one of the buttons to start playing. If you're new, hit \"NEW CHARACTER\", otherwise do as" +
                        " you please.\nYou control your character with the arrow keys, and attack with space.\n" +
                        "Press Tab to open a shop where you can upgrade yourself, and press Escape to pause.\n" +
                        "Your goal is to get as far as you possibly can.\n Good luck."
        );
        instructions.setFontScale(1.2f);
        instructions.setPosition(100, 700);

        //ADD TO STAGE

        mainMenuStage.addActor(buttonGroup);
        mainMenuStage.addActor(exitButton);
        mainMenuStage.addActor(instructions);

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


        if (newCharButton.isPressed()) {
            this.dispose();
            game.setScreen(new GameScreen(game,
                    new World(new TextbasedWorldGenerator(), new Character(new Vec2(800, 450)))));
        }
        if (loadCharButton.isPressed() && LoadCharacter.saveDataExists()) {
            this.dispose();
            game.setScreen(new LoadScreen(game));
        }

        if (exitButton.isPressed()) {
            this.dispose();
            System.out.println("Exiting Game...");
            Gdx.app.exit();
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
        //not sure if stage and group need to be cleared or not for java garbage disposal to deal with the actors

        //disposal calls
        mainMenuStage.dispose();
        atlas.dispose();

    }


}
