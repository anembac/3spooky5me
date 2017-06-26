package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.View.ScreenDrawer;

public class MainMenuDrawer extends ScreenDrawer{
    private final OrthographicCamera camera;


    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final Label.LabelStyle labelStyle;
    private final Label instructions;


    public MainMenuDrawer(){
        TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
        TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable, font);
        labelStyle = new Label.LabelStyle(font, new Color(9, 205, 218, 255));
        instructions = new Label("", labelStyle);
        instructions.setText(
                "Press one of the buttons to start playing. If you're new, hit \"NEW CHARACTER\", otherwise do as" +
                        " you please.\nYou control your character with the arrow keys, and attack with space.\n" +
                        "Press Tab to open a shop where you can upgrade yourself, and press Escape to pause.\n" +
                        "Your goal is to get as far as you possibly can.\n Good luck."
        );
        instructions.setFontScale(1.2f);
        instructions.setPosition(100, 700);
        
        //Camera stuff
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
    }

    @Override
    public void draw(float delta) {
        super.draw(delta);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Welcome to InfinityRun PRE-ALPHA!!! ", 100, 150);
        font.draw(batch, "Press \"NEW CHARACTER\" to begin!", 100, 100);
        batch.end();


    }
}
