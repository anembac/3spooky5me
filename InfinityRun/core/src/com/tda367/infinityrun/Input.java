package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;

/**
 * Created by Mikael on 5/3/2017.
 */
public class Input implements IInput {
    private InputState state = new InputState(false,false,false,false);
    public Input()
    {

    }

    @Override
    public InputState getInput() {
        return state;
    }

    @Override
    public void collectInput() {
        boolean forward = false;
        boolean back = false;
        boolean jump = false;
        boolean attack = false;
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) forward = true;
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) back = true;
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) jump = true;
        //if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.)) attack = true;
        state = new InputState(forward, back, jump, attack);
    }
}
