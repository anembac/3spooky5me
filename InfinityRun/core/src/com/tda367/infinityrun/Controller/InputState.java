package com.tda367.infinityrun.Controller;

/**
 * Created by Mikael on 5/3/2017.
 */
public class InputState {
    private boolean forward = false;
    private boolean back = false;
    private boolean jump = false;
    private boolean attack = false;
    private boolean goToMenu = false;
    private boolean goToShop = false;

    public InputState(boolean forward, boolean back, boolean jump, boolean attack, boolean goToMenu, boolean goToShop) {
        this.forward = forward;
        this.back = back;
        this.jump = jump;
        this.attack = attack;
        this.goToMenu = goToMenu;
        this.goToShop = goToShop;
    }

    public boolean forwardPressed() {
        return forward;
    }

    public boolean backPressed() {
        return back;
    }

    public boolean jumpPressed() {
        return jump;
    }

    public boolean attackPressed() {
        return attack;
    }

    public boolean goToMenuPressed() { return goToMenu; }

    public boolean goToShopPressed() { return goToShop; }
}
