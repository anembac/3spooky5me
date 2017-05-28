package com.tda367.infinityrun.Controller;

/**
 * Created by Mikael on 5/3/2017.
 */
public class InputState {
    private boolean fp = false;
    private boolean bp = false;
    private boolean jp = false;
    private boolean ap = false;

    public InputState(boolean forward, boolean back, boolean jump, boolean attack) {
        fp = forward;
        bp = back;
        jp = jump;
        ap = attack;
    }

    public boolean forwardPressed() {
        return fp;
    }

    public boolean backPressed() {
        return bp;
    }

    public boolean jumpPressed() {
        return jp;
    }

    public boolean attackPressed() {
        return ap;
    }
}
