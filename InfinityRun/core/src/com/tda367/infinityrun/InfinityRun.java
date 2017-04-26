package com.tda367.infinityrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class InfinityRun extends Game {
	public SpriteBatch batch;
	public BitmapFont font;


    //creates a spritebatch and a font
    //shows mainmenuscreen, doesn't really do anything else right now I think
	//based on https://github.com/libgdx/libgdx/wiki/Extending-the-simple-game
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));

    }

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
	    batch.dispose();
	    font.dispose();
	}
}
