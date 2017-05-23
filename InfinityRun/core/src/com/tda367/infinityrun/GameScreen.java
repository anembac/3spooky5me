package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.tda367.infinityrun.Math.Rect;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.AttackHitbox;
import com.tda367.infinityrun.SpecialUpgrades.*;
import com.tda367.infinityrun.WeaponTypes.AdminsDebugginStaff;
import com.tda367.infinityrun.WeaponTypes.Sword;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GameScreen implements Screen {  //tries to put textures onto the objects created in baseroom and draw them
    final InfinityRun game;                 // but it's not currently successful...

    //Texture ptex;
    //Texture ctex;
    HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
    OrthographicCamera camera;
    Stage gameStage = new Stage();
    //BaseRoom br;
    Character hero;
    World world;
    HUD hud;
    Shop shop;
    final int windowWidth = 1600;
    final int windowHeight = 900;

    public GameScreen(final InfinityRun game) {
        this.game = game;

        //br = new BaseRoom();
        //br.setup();

        hero = new Character(new Vec2(768, 450));
        //WorldObject hitbox = new AttackHitbox(new Vec2(hero.getPosition().x + 64, hero.getPosition().y));
        Enemy enemy = new Enemy(new Vec2(768,520), new Vec2(64,64),1,1,1,1,1,1,1,1);
        // setup a new world depending on some menu parameters maybe? diff etc. world could also be called level, std

        world = new World();
        world.setInput(new InputGDX());
        world.addWorldObject(enemy);
        world.addWorldObject(hero);
        world.setHero(hero);
        hero.setMeleeWeapon(new AdminsDebugginStaff());
        //hero.setMeleeWeapon(new Sword());
        //Shop
        shop = new Shop(hero);

        //HUDDDDDD
        hud = new HUD(hero);
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
        Rect heroRect = hero.getDrawingRect();

        float cx = -heroRect.position.x - heroRect.bounds.x / 2 + windowWidth / 2;
        float cy = -heroRect.position.y + windowHeight / 2 - heroRect.bounds.y / 2;
        translation.translate(cx, cy, 0);
        game.batch.setTransformMatrix(translation);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();


        //Object culling in case of lagg for future, may contain buggs so i will leave this commented for now.
        /*List<KdTreeNode<WorldObject>> efficientObjectsToRender = CollisionManager.getInstance().kdTree.rangeSearch2D(heroRect.position.x-100,heroRect.position.x+100,heroRect.position.y+100, heroRect.position.y-100);
        HashSet<WorldObject> efficientSet = new HashSet<WorldObject>();
        for(KdTreeNode<WorldObject> node : efficientObjectsToRender)
        {
            efficientSet.add(node.data);
        }
        for (WorldObject wo : efficientSet) {
            if (!textureMap.containsKey(wo.getTexturename())) {
                textureMap.put(wo.getTexturename(), new Texture(Gdx.files.internal(wo.getTexturename())));
            }
            game.batch.draw(textureMap.get(wo.getTexturename()), wo.getPosition().x, wo.getPosition().y);

        }*/

        for (WorldObject wo : world.getWorldObjects()) {
            checkTexture(wo);
            game.batch.draw(textureMap.get(wo.getTexturename()), wo.getPosition().x, wo.getPosition().y);
            for(WorldObject child : wo.getChildren())
            {
                checkTexture(child);
                game.batch.draw(textureMap.get(child.getTexturename()), child.getPosition().x, child.getPosition().y);
            }
        }
        //game.batch.draw(ctex, hero.position.x, hero.position.y);
        game.batch.end();
        //System.out.println(game.batch.renderCalls);
        hud.render();

        //remove this after testing
        /*if (Gdx.input.isKeyPressed(InputGDX.Keys.LEFT) && !Gdx.input.isKeyPressed(InputGDX.Keys.RIGHT))
            hero.moveXPosition(Direction.LEFT);
        else if (Gdx.input.isKeyPressed(InputGDX.Keys.RIGHT) && !Gdx.input.isKeyPressed(InputGDX.Keys.LEFT))
            hero.moveXPosition(Direction.RIGHT);
        else hero.moveXPosition(Direction.NONE);*/
        if(Gdx.input.isKeyPressed(Input.Keys.TAB)){
            this.dispose();
            game.setScreen(new ShopScreen(shop));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            //this.dispose();
            game.setScreen(new PauseMenuScreen(this));
        }
    }

    private void checkTexture(WorldObject wo)
    {
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
