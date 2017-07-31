package com.tda367.infinityrun.View.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.tda367.infinityrun.Model.Enemy;
import com.tda367.infinityrun.Model.MeleeWeapon;
import com.tda367.infinityrun.Model.World;
import com.tda367.infinityrun.Model.WorldObject;
import com.tda367.infinityrun.Utils.Math.Rect;
import com.tda367.infinityrun.View.HUD;

import java.util.HashMap;
import java.util.LinkedList;

import static com.tda367.infinityrun.Utils.Constants.windowHeight;
import static com.tda367.infinityrun.Utils.Constants.windowWidth;

public class GameScreenDrawer extends ScreenDrawer {

    private HUD hud;
    private World world;
    private final Texture background = new Texture(Gdx.files.internal("WorldObjects/castle.png"));
    private final HashMap<String, Texture> textureMap = new HashMap<String, Texture>();

    public GameScreenDrawer(World world) {
        this.world = world;
        hud = new HUD(world.getHero());
    }

    public void draw(float delta) {
        Matrix4 translation = new Matrix4();
        Rect heroRect = world.getHero().getDrawingRect();

        //Translates camera to world.getHero() position
        float cx = -heroRect.position.x - heroRect.bounds.x / 2 + windowWidth / 2;
        float cy = -heroRect.position.y + windowHeight / 2 - heroRect.bounds.y / 2;
        translation.translate(cx, cy, 0);
        batch.setTransformMatrix(translation);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.draw(vcButtons, delta);
        batch.draw(background, -cx, -cy);
        for (WorldObject wo : world.getWorldObjects()) {
            checkTexture(wo);
            float woWidth = wo.getBounds().x;
            float woHeight = wo.getBounds().y;


            batch.draw(textureMap.get(wo.getTexturename()), wo.getPosition().x, wo.getPosition().y, woWidth, woHeight);
            if (wo instanceof Enemy) {

                Enemy currentEnemy = (Enemy) wo;
                font.draw(batch, (int) currentEnemy.getHealth() + "/" + (int) currentEnemy.getMaxHealth(), wo.getPosition().x, wo.getPosition().y + wo.getDrawingRect().bounds.y + 20);
            }
            for (WorldObject child : wo.getChildren()) {
                checkTexture(child);
                float childWidth = child.getBounds().x;
                float childHeight =  child.getBounds().y;
                float theta = -((MeleeWeapon)child).getRotation(); //negative because rotation is counterclockwise and we want clockwise
                System.out.println(theta);
                batch.draw(textureMap.get(child.getTexturename()),child.getPosition().x,child.getPosition().y,0,0, childWidth, childHeight, 1, 1, theta, 0, 0, 0, 0, false, false);
            }
        }

        batch.end();
        hud.render();

    }

    private void checkTexture(WorldObject wo) {
        if (!textureMap.containsKey(wo.getTexturename())) {
            textureMap.put(wo.getTexturename(), new Texture(Gdx.files.internal(wo.getTexturename())));
        }
    }

    public void dispose() {
        for (Texture tex : textureMap.values()) {
            tex.dispose();
        }
    }


}
