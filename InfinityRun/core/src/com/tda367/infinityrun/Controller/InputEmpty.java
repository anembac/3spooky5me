package com.tda367.infinityrun.Controller;

import com.tda367.infinityrun.Model.InputState;

//FOR TESTING ONLY
public class InputEmpty implements IInput {
    private InputState state = new InputState(false, false, false, false, false, false);

    public InputEmpty(boolean forw, boolean back, boolean jump, boolean attack) {
        state = new InputState(forw, back, jump, attack, false, false);
    }

    @Override
    public InputState getInput() {
        return state;
    }

    @Override
    public void collectInput() {

    }
}
