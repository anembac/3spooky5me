package com.tda367.infinityrun.RoomTiles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tda367.infinityrun.Character;






public class Animator{

    // Constant rows and columns of the sprite sheet

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    //Texture walkSheet;
    SpriteBatch spriteBatch;
    Character dudebro;

    public Animator(Character c) {

        this.dudebro = c;
        create();
    }



    // A variable for tracking elapsed time for the animation
    float stateTime;

    
    public void create() {

        // Load the sprite sheet as a Texture
        //walkSheet = new Texture(Gdx.files.internal("worldObjects/animation/a/run.png"));
      //  TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("worldObjects/animation/a/run.png"));
        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("WorldObjects/animation/a/run.atlas"));
        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[17];

        for (int i = 0; i < 17; ) {
            System.out.println(i);
                walkFrames[i++] = new TextureRegion(atlas.findRegion(Integer.toString(i)));

            }


        // Initialize the Animation with the frame interval and array of frames
            walkAnimation = new Animation<TextureRegion>(0.5f, walkFrames);


        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    
    public void render() {
    // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, dudebro.getPosition().x ,dudebro.getPosition().y); // Draw current frame at (50, 50)
        spriteBatch.end();
    }

    
    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();

    }
}

