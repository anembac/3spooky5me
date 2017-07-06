package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tda367.infinityrun.View.IDrawnByDrawer;

import java.util.LinkedList;
/*
* Superclass for the drawer classes, contains some basic functions that all drawer classes will use.
*/
public abstract class ScreenDrawer {
    final int windowWidth = 1600;
    final int windowHeight = 900;
    SpriteBatch batch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    OrthographicCamera camera = new OrthographicCamera();
    LinkedList<IDrawnByDrawer> vcButtons = new LinkedList<IDrawnByDrawer>();


    public void draw(float delta){ //Subclasses' draw() methods must begin with batch.begin().
        //TODO: Pick a nice color
        Gdx.gl.glClearColor(0.04f, 0.8f, 0.85f, 1); // #09CDDA
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //batch.begin();
        for(IDrawnByDrawer i : vcButtons){

            i.draw(batch,1  );
        }
        //batch.end();
    }


    public void show(){
       camera.setToOrtho(false, windowWidth, windowHeight);
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }

    public void setButtonTexture(Button.ButtonStyle style){
        for(IDrawnByDrawer b : vcButtons){
            b.setStyle(style);

        }
    }
}
