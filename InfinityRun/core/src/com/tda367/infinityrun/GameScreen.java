package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.tda367.infinityrun.Math.Vec2;

import java.util.HashMap;

public class GameScreen implements Screen{  //tries to put textures onto the objects created in baseroom and draw them
    final InfinityRun game;                 // but it's not currently successful...

    //Texture ptex;
    //Texture ctex;
    HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
    OrthographicCamera camera;
    //BaseRoom br;
    Character hero;
    World world;

    public GameScreen(final InfinityRun game){
        this.game = game;

        //br = new BaseRoom();
        //br.setup();
        hero = new Character(new Vec2(100,200), new Vec2(64,64),"player.png");
        // setup a new world depending on some menu parameters maybe? diff etc. world could also be called level, std
        world = new World();
        world.generateWorld(/*params*/);
        world.addWorldObject(hero);

        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        world.frame(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        for(WorldObject wo : world.getWorldObjects()){
            if(!textureMap.containsKey(wo.getTexturename()))
            {
                textureMap.put(wo.getTexturename(), new Texture(Gdx.files.internal(wo.getTexturename())));
            }
            game.batch.draw(textureMap.get(wo.getTexturename()), wo.position.x, wo.position.y);
        }
        //game.batch.draw(ctex, hero.position.x, hero.position.y);
        game.batch.end();

        //remove this after testing
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            hero.moveXPosition(Direction.LEFT);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT))
            hero.moveXPosition(Direction.RIGHT);
        else hero.moveXPosition(Direction.NONE);
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
        for(Texture tex : textureMap.values())
        {
            tex.dispose();
        }
    }
}
