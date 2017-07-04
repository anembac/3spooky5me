package com.tda367.infinityrun.Controller;

import com.badlogic.gdx.graphics.g2d.Batch;

/*
* More contained version of libGDX's Drawable interface, so that it only allows usage of the draw() method.
*/
public interface IDrawnByDrawer {

    void draw(Batch batch, float parentAlpha);
}
