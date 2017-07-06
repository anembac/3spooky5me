package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.View.IDrawnByDrawer;

import java.util.LinkedList;

public class MainMenuDrawer extends ScreenDrawer{

    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final Label.LabelStyle labelStyle;
    private final Label instructions;


    /*
    * Receives a LinkedList of IDrawnByDrawers (although in this case they are all VCButtons) to draw.
    * Also manages everything else that is visual but not also inherently part of the controller.
    *
    * Because it has a list of IDrawnByDrawers the only thing it can do with the buttons is to draw them,
    * removing the possiblity of the view doing something it shouldn't be able to do with the buttons.
    */

    //TODO: Make buttons have their associated text
    public MainMenuDrawer(LinkedList<IDrawnByDrawer> vcButtons){
        TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
        TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
        TextButton.TextButtonStyle menuButtonStyle =
                new TextButton.TextButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable, font);
        this.vcButtons = vcButtons;
        for(IDrawnByDrawer b : vcButtons){
            b.setStyle(menuButtonStyle);

        }


        //Instructions
        labelStyle = new Label.LabelStyle(font, new Color(9, 205, 218, 1));
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
        camera.setToOrtho(false, 1600, 900);
    }

    @Override
    public void draw(float delta) {
        batch.begin();
        font.draw(batch, "Welcome to InfinityRun ALPHA!!! ", 100, 150);
        instructions.draw(batch, 1);
        super.draw(delta);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
