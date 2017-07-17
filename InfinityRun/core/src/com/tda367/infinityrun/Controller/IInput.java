package com.tda367.infinityrun.Controller;

import com.tda367.infinityrun.Model.InputState;

/**
 * Created by Mikael on 5/3/2017.
 */
public interface IInput {
    InputState getInput();

    void collectInput();
}
