package com.tda367.infinityrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}

	boolean gameRunning = false;        //an equivalent function in our graphics library of choice
	World world = null;
	Character player;


	public Game()
	{
		player = new Character();

		System.out.println("lmao");
	      startGame();

	}

	/*
        This method should be called upon the user clicking start game and select difficulty? or other options.
     */
	public void startGame()
	{

		world = new World(/* required parameters to create the correct world*/);

		gameRunning = true;
		System.out.println("Game started!");

	}

	/*
        Takes delta time as argument i case of laggs and stuffs.
     */
	public void frame(double dt)
	{
		// When the game is running we should frame the world
		if(gameRunning)
		{
			world.frame(dt);
		}
	}

	public void renderOld()
	{
		if(gameRunning)
		{
			world.render();
		}
	}

	public Character getPlayer(){
		return this.player;
	}
}
