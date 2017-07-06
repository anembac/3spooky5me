package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.View.IDrawnByDrawer;

import java.util.LinkedList;

public class LoadScreenDrawer extends ScreenDrawer{
    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    private final TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
            textureUpDrawable, textureDownDrawable, textureUpDrawable, font);


    public LoadScreenDrawer(LinkedList<IDrawnByDrawer> vcButton){

        this.vcButtons = vcButton;
        for(IDrawnByDrawer i : vcButtons){
           i.setStyle(style);
        }

    }

    /*
            buttons[i].setName("" + (i + 1));
            buttons[i].setWidth(128);
            buttons[i].addListener(changeListener);
            buttonTable.row();
            buttonTable.add(buttons[i]).width(buttons[i].getPrefWidth()).pad(5);

            buttonTable.setPosition(800, 450);
    */


}
