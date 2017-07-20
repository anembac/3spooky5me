package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.View.IDrawnByDrawer;
import com.tda367.infinityrun.View.VCButton;

import java.util.LinkedList;

public class MainMenuDrawer extends ScreenDrawer{

    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final Label.LabelStyle labelStyle;
    private final Label instructions;


    /*
    * Receives a LinkedList of IDrawnByDrawers (although in this case they are all VCButtons) to draw.
    * Also manages everything else that is visual but not also inherently part of the controller.
    *
    * Because it has a list of IDrawnByDrawers the only thing it can do with the buttons is to draw them,
    * removing the possiblity of the view doing something it shouldn't be able to do with the buttons.
    */
    
    public MainMenuDrawer(VCButton[] vcButtons){

        TextButton.TextButtonStyle menuButtonStyle =
                (TextButton.TextButtonStyle) generateStyle(
                        atlas,
                        "testtexture",
                        "testtexture2",
                        "testtexture",
                        true);

        this.vcButtons = vcButtons;
        vcButtons[0].setAndDisplayText("New Character", menuButtonStyle);
        vcButtons[1].setAndDisplayText("Load Character", menuButtonStyle);
        vcButtons[2].setAndDisplayText("Exit Game", menuButtonStyle);

        //Instructions
        labelStyle = new Label.LabelStyle(font, new Color(1, 1, 1, 1));
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

    public void draw(float delta) {
        batch.begin();
        super.draw(vcButtons, delta);
        font.draw(batch, "Welcome to InfinityRun ALPHA!!! ", 100, 150);
        instructions.draw(batch, 1);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
