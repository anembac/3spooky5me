package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tda367.infinityrun.View.IDrawnByDrawer;

import java.util.LinkedList;
/*
* Superclass for the drawer classes, contains some basic functions that all drawer classes will use.
*/
public abstract class ScreenDrawer {
    protected SpriteBatch batch = new SpriteBatch();
    protected BitmapFont font = new BitmapFont();
    protected OrthographicCamera camera = new OrthographicCamera();
    protected LinkedList<IDrawnByDrawer> vcButtons;


    public void draw(float delta){
        //TODO: Pick a nice color
        Gdx.gl.glClearColor(0.04f, 0.8f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //batch.begin();
        for(IDrawnByDrawer i : vcButtons){

            i.draw(batch,1  );
        }
        //batch.end();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
