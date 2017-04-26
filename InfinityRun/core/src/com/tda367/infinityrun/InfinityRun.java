package com.tda367.infinityrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class InfinityRun extends Game {
	public SpriteBatch batch;
	public BitmapFont font;



//looks at mainmenuscreen, doesn't really do anything else right now, probably...
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
