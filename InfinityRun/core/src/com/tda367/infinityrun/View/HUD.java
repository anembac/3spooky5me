package com.tda367.infinityrun.View;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tda367.infinityrun.Model.Character;

public class HUD {

    private final Character character;
    private final TextureAtlas atlas = new TextureAtlas("HUD/HUDTexture.atlas");
    private final SpriteBatch hudbatch = new SpriteBatch();
    private final BitmapFont hudfont = new BitmapFont();
    private final TextureRegion healthicon = new TextureRegion(atlas.findRegion("heart"));
    private final TextureRegion coinicon = new TextureRegion(atlas.findRegion("hudcoin"));

    private final TextureRegion axeIcon = new TextureRegion(atlas.findRegion("HudAxe"));

    private final TextureRegion maceIcon = new TextureRegion(atlas.findRegion("HudMace"));

    private final TextureRegion daggerIcon = new TextureRegion(atlas.findRegion("HudDagger"));

    private final TextureRegion spearIcon = new TextureRegion(atlas.findRegion("HudSpear"));

    private final TextureRegion swordIcon = new TextureRegion(atlas.findRegion("HudSword"));

    private final TextureRegion weaponContainer = new TextureRegion(atlas.findRegion("WeaponContainer"));



    public HUD(Character character) {
        this.character = character;
    }


    public void render() {
        //health
        hudbatch.begin();
        hudbatch.draw(healthicon, 100 - healthicon.getRegionWidth(), 100 - healthicon.getRegionHeight() / 2);
        hudfont.draw(hudbatch, ": " + (int)Math.max(0, character.getHealth()) + "/" + (int) character.getMaxHealth(), 100, 100);

        //coins
        hudbatch.draw(coinicon, 100 - coinicon.getRegionWidth(), 850 - coinicon.getRegionHeight() / 2);
        hudfont.draw(hudbatch, ": " + character.getCoins(), 100, 850);


        //rooms
        hudfont.draw(hudbatch, "Highest distance from start: " + character.getMaxdistance(), 1400, 850);

        //weapon container
        hudbatch.draw(weaponContainer, 105 - healthicon.getRegionWidth(),55 - healthicon.getRegionHeight() / 2 );

        //Weapon name
        hudfont.draw(hudbatch, character.getWeapon(),165 - healthicon.getRegionWidth(),80 - healthicon.getRegionHeight() / 2 );

        //
        hudbatch.draw(Weapontype(character.getWeapon()),105 - healthicon.getRegionWidth(),55 - healthicon.getRegionHeight() / 2 );




        hudbatch.end();


    }



    public TextureRegion Weapontype(String weapon){
            switch (weapon){
                case "Axe": return axeIcon;
                case "Dagger": return daggerIcon;
                case "Mace": return maceIcon;
                case "Spear": return spearIcon;
                case "Sword": return swordIcon;

            }
            return swordIcon;
    }



    public void dispose() {
        atlas.dispose();
        hudbatch.dispose();
        hudfont.dispose();
    }


}
