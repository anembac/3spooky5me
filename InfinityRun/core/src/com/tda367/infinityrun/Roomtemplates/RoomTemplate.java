package com.tda367.infinityrun.Roomtemplates;

/**
 * Created by kaffe on 5/3/17.
 */
public class RoomTemplate {

    public boolean isExitup() {
        return exitup;
    }

    public boolean isExitdown() {
        return exitdown;
    }

    public boolean isExitleft() {
        return exitleft;
    }

    public boolean isExitright() {
        return exitright;
    }

    public void setExitup(boolean exitup) {
        this.exitup = exitup;
    }

    public void setExitdown(boolean exitdown) {
        this.exitdown = exitdown;
    }

    public void setExitleft(boolean exitleft) {
        this.exitleft = exitleft;
    }

    public void setExitright(boolean exitright) {
        this.exitright = exitright;
    }

    public boolean exitup;
    public boolean exitdown;
    public boolean exitleft;
    public boolean exitright;


}
