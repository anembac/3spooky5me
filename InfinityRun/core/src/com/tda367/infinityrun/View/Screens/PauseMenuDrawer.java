package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.View.IDrawnByDrawer;
import com.tda367.infinityrun.View.VCButton;

import java.util.LinkedList;

public class PauseMenuDrawer extends ScreenDrawer {
    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    private final TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    private final TextButton.TextButtonStyle menuButtonStyle =
            new TextButton.TextButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable, font);
    VerticalGroup buttonGroup;




    public PauseMenuDrawer(VerticalGroup buttonGroup, VCButton[] vcButtons){
        this.vcButtons = vcButtons;
        this.buttonGroup = buttonGroup;
        //setButtonTexture(menuButtonStyle);
        vcButtons[0].setAndDisplayText("Exit Game", menuButtonStyle);
        vcButtons[1].setAndDisplayText("Exit to Main Menu", menuButtonStyle);
        vcButtons[2].setAndDisplayText("Unpause", menuButtonStyle);
    }

    public void draw(float delta) {
        batch.begin();
        super.draw(vcButtons, delta);
        buttonGroup.draw(batch, 1);
        batch.end();

    }

}
