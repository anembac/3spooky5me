package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.View.IDrawnByDrawer;

import java.util.LinkedList;
/*
* Superclass for the drawer classes, contains some basic functions that all drawer classes will use.
*/
public abstract class ScreenDrawer {
    SpriteBatch batch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    OrthographicCamera camera = new OrthographicCamera();
    LinkedList<IDrawnByDrawer> vcButtons = new LinkedList<IDrawnByDrawer>();


    /*
    * Responsible for drawing all the things all drawers have in common. Usually overridden, but with a call to
    * super.draw() at some point.
    *
    * Does not use own batch.begin() and batch.end(). Subclasses' draw() methods must begin with batch.begin().
    */
    public void draw(float delta){
        //TODO: Pick a nice color
        Gdx.gl.glClearColor(0.04f, 0.8f, 0.85f, 1); // #09CDDA
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        for(IDrawnByDrawer i : vcButtons){

            i.draw(batch,1  );
        }
    }


    public void show(){
       camera.setToOrtho(false, Constants.windowWidth, Constants.windowHeight);
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
