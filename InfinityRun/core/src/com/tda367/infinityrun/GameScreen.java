package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen{  //tries to put textures onto the objects created in baseroom and draw them
    final InfinityRun game;                 // but it's not currently successful...

    Texture ptex;
    Texture ctex;
    OrthographicCamera camera;
    BaseRoom br;
    Character hero;


    public GameScreen(final InfinityRun game){
        this.game = game;

        br = new BaseRoom();
        br.setup();
        hero = new Character((new Vector2(128,17)));


        //load images
        ptex = new Texture(Gdx.files.internal("platform.png"));
        ctex = new Texture(Gdx.files.internal("player.png"));

        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();


        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        for(WorldObject wo : br.getRoomObjects()){
            game.batch.draw(ptex,wo.position.x, wo.position.y);
        }
        game.batch.draw(ctex, hero.position.x, hero.position.y);
        game.batch.end();


        //remove this after testing
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            hero.moveXPosition(Direction.LEFT);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            hero.moveXPosition(Direction.RIGHT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose(){
        ptex.dispose();
        ctex.dispose();

    }



}
