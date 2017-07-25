package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.tda367.infinityrun.View.VCButton;

public class LoadScreenDrawer extends ScreenDrawer{

    private Table buttonTable = new Table();
    private VCButton[] buttons;

    public LoadScreenDrawer(Table table, VCButton[] buttons){
        buttonTable = table;
        this.buttons = buttons;
        int i = 0;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
        TextButton.TextButtonStyle style = (TextButton.TextButtonStyle) generateStyle(
                atlas,
                "testtexture",
                "testtexture2",
                "testtexture",
                true);
        for (VCButton button : buttons) {

            button.setAndDisplayText("Save " + (++i), style);
        }
    }


    public void draw(float delta){
        Gdx.gl.glClearColor(0.04f, 0.8f, 0.85f, 1); // #09CDDA
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        buttonTable.draw(batch, 1);
        batch.end();
    }


}
