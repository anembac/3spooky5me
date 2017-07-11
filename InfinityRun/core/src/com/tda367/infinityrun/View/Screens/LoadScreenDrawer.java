package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.View.IDrawnByDrawer;
import com.tda367.infinityrun.View.VCButton;

import java.util.LinkedList;

public class LoadScreenDrawer extends ScreenDrawer{
    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    private final TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
            textureUpDrawable, textureDownDrawable, textureUpDrawable, font);
    Table table = new Table();


    public LoadScreenDrawer(LinkedList<IDrawnByDrawer> vcButtons){

        this.vcButtons = vcButtons;
        setButtonTexture(style);
        int i = 0;
        for (IDrawnByDrawer button : this.vcButtons) {
            //button.setAndDisplayText("Save " + (++i), style);
            table.add((VCButton)button).expand().center();
//            button.setPosition(
//                    800-button.getWidth()/2,
//                    450+(button.getHeight()*vcButtons.size()/2)-(i*button.getHeight()+i*10)
//            );
        }
        table.setPosition(800-table.getWidth()/2,450-table.getHeight()/2);
        System.out.println(table.getX());
        System.out.println(table.getY());
        System.out.println(vcButtons.get(0).getX());
        System.out.println(vcButtons.get(0).getY());

    }

    /*
            buttons[i].setName("" + (i + 1));
            buttons[i].setWidth(128);
            buttons[i].addListener(changeListener);
            buttonTable.row();
            buttonTable.add(buttons[i]).width(buttons[i].getPrefWidth()).pad(5);

            buttonTable.setPosition(800, 450);
    */

    public void draw(float delta){
        batch.begin();
        super.draw(delta);
        batch.end();
    }


}
