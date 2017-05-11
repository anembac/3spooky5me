package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUD {

    Character character;
    TextureAtlas atlas = new TextureAtlas("HUD/HUDTexture.pack");
    SpriteBatch hudbatch = new SpriteBatch();
    BitmapFont hudfont = new BitmapFont();
    TextureRegion healthicon = new TextureRegion(atlas.findRegion("heart"));
    TextureRegion coinicon = new TextureRegion(atlas.findRegion("hudcoin"));


    public HUD(Character character){
        this.character = character;
    }


    public void render(){
        //health
        hudbatch.begin();
        hudbatch.draw(healthicon, 100-healthicon.getRegionWidth(), 100-healthicon.getRegionHeight()/2);
        hudfont.draw(hudbatch, ": "+character.getHealth() + "/" + character.getHealth(), 100,100);

        //coins
        hudbatch.draw(coinicon, 100-coinicon.getRegionWidth(), 850-coinicon.getRegionHeight()/2);
        hudfont.draw(hudbatch,": " + character.getCoins(), 100, 850);


        //rooms
        hudfont.draw(hudbatch, "Rooms cleared: " , 1400, 850);
        hudbatch.end();
    }


    public void dispose(){
        atlas.dispose();
        hudbatch.dispose();
        hudfont.dispose();
    }




}
