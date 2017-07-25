package com.tda367.infinityrun.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tda367.infinityrun.Model.InputState;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mikael on 5/3/2017.
 */
public class InputGDX implements IInput, Observer {
    private InputState state;

    public InputGDX(InputState state) {
        this.state = state;


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
        boolean goToMenu = false;
        boolean goToShop = false;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) forward = true;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) back = true;
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.UP)) jump = true;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.SPACE)) attack = true;

        //state = new InputState(forward, back, jump, attack, goToMenu, goToShop);
        state.update(forward, back, jump, attack, goToMenu, goToShop);
    }

    @Override
    public void update(Observable o, Object arg) {
        collectInput();
    }
}
