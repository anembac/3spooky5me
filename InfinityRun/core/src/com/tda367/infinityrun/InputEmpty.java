package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;

/**
 * Created by Mikael on 5/3/2017.
 */
public class InputEmpty implements IInput {
    private InputState state = new InputState(false,false,false,false);
    public InputEmpty(boolean forw, boolean back, boolean jump, boolean attack)
    {
        state = new InputState(forw, back, jump, attack);
    }

    @Override
    public InputState getInput() {
        return state;
    }

    @Override
    public void collectInput() {

    }
}
