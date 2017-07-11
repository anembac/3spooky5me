package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    Stage stage = new Stage();


    public LoadScreenDrawer(LinkedList<IDrawnByDrawer> vcButtons){

        this.vcButtons = vcButtons;
        setButtonTexture(style);
        int i = 0;
        for (IDrawnByDrawer button : this.vcButtons) {
            button.setAndDisplayText("Save " + (++i), style);
            if(i%10 == 1){ //this yields rows of 10 columns each
                table.row();
            }
            table.add((VCButton)button).width(button.getWidth()).pad(5);

        }
        table.setPosition(800-table.getWidth()/2,450-table.getHeight()/2);
        stage.addActor(table);

//        System.out.println(table.getX());
//        System.out.println(table.getY());
//        System.out.println(vcButtons.get(0).getX());
//        System.out.println(vcButtons.get(0).getY());

    }


    public void draw(float delta){
        Gdx.gl.glClearColor(0.04f, 0.8f, 0.85f, 1); // #09CDDA
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //super.draw(delta);
        stage.draw();
        batch.end();
    }


}
