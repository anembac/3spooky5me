package com.tda367.infinityrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Matrix4;
import com.tda367.infinityrun.*;
import com.tda367.infinityrun.Math.Rect;

import java.util.HashMap;

public class GameScreen implements Screen {  //tries to put textures onto the objects created in baseroom and draw them
    final InfinityRun game;                 // but it's not currently successful...


    private final HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
    private final OrthographicCamera camera;
    private final Stage gameStage = new Stage();
    final World world;
    private final HUD hud;
    private final int windowWidth = 1600;
    private final int windowHeight = 900;
    private final Texture background = new Texture(Gdx.files.internal("WorldObjects/arbetefrihet.png"));

    public GameScreen(final InfinityRun game, World world) {
        this.game = game;
        this.world = world;

        //HUDDDDDD
        hud = new HUD(world.getHero());
        Gdx.input.setInputProcessor(gameStage);
        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, windowWidth, windowHeight);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        world.frame(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        Matrix4 translation = new Matrix4();
        Rect heroRect = world.getHero().getDrawingRect();

        //Translates camera to world.getHero() position
        float cx = -heroRect.position.x - heroRect.bounds.x / 2 + windowWidth / 2;
        float cy = -heroRect.position.y + windowHeight / 2 - heroRect.bounds.y / 2;
        translation.translate(cx, cy, 0);
        game.batch.setTransformMatrix(translation);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background,-cx,-cy);
        for (WorldObject wo : world.getWorldObjects()) {
            checkTexture(wo);
            game.batch.draw(textureMap.get(wo.getTexturename()), wo.getPosition().x, wo.getPosition().y);
            if(wo instanceof Enemy){
                BitmapFont font = new BitmapFont();
                Enemy currentEnemy = (Enemy)wo;
                font.draw(game.batch, (int)currentEnemy.getHealth() + "/" + (int)currentEnemy.getMaxHealth(), wo.getPosition().x, wo.getPosition().y + wo.getDrawingRect().bounds.y + 20);
            }
            for (WorldObject child : wo.getChildren()) {
                checkTexture(child);
                game.batch.draw(textureMap.get(child.getTexturename()), child.getPosition().x, child.getPosition().y);
            }
        }

        game.batch.end();
        hud.render();
        //spöket
        if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {

            game.setScreen(new ShopScreen(world.getShop(), this));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            //this.dispose();
            game.setScreen(new PauseMenuScreen(this));
        }
    }

    private void checkTexture(WorldObject wo) {
        if (!textureMap.containsKey(wo.getTexturename())) {
            textureMap.put(wo.getTexturename(), new Texture(Gdx.files.internal(wo.getTexturename())));
        }
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
    public void dispose() {
        gameStage.dispose();
        for (Texture tex : textureMap.values()) {
            tex.dispose();
        }
    }
}
