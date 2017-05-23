package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;

/**
 * Created by Mikael on 5/3/2017.
 */
public class InputEmpty implements IInput {
    private InputState state = new InputState(false,false,false,false);
    public InputEmpty()
    {

    }

    @Override
    public InputState getInput() {
        return state;
    }

    @Override
    public void collectInput() {
        state = new InputState(false, false, false, false);
    }
}
