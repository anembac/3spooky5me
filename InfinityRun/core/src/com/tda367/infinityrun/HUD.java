package com.tda367.infinityrun;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUD {

    private final Character character;
    private final TextureAtlas atlas = new TextureAtlas("HUD/HUDTexture.pack");
    private final SpriteBatch hudbatch = new SpriteBatch();
    private final BitmapFont hudfont = new BitmapFont();
    private final TextureRegion healthicon = new TextureRegion(atlas.findRegion("heart"));
    private final TextureRegion coinicon = new TextureRegion(atlas.findRegion("hudcoin"));


    public HUD(Character character) {
        this.character = character;
    }


    public void render() {
        //health
        hudbatch.begin();
        hudbatch.draw(healthicon, 100 - healthicon.getRegionWidth(), 100 - healthicon.getRegionHeight() / 2);
        hudfont.draw(hudbatch, ": " + (int) character.getHealth() + "/" + (int) character.getMaxHealth(), 100, 100);

        //coins
        hudbatch.draw(coinicon, 100 - coinicon.getRegionWidth(), 850 - coinicon.getRegionHeight() / 2);
        hudfont.draw(hudbatch, ": " + character.getCoins(), 100, 850);


        //rooms
        hudfont.draw(hudbatch, "Rooms cleared: " + character.getMaxdistance(), 1400, 850);
        hudbatch.end();
    }


    public void dispose() {
        atlas.dispose();
        hudbatch.dispose();
        hudfont.dispose();
    }


}
