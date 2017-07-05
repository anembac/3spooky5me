package com.tda367.infinityrun.Controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/*
* More contained version of libGDX's Drawable interface, that only allows usage of the draw() and setStyle() methods.
*/
public interface IDrawnByDrawer {

    void draw(Batch batch, float parentAlpha);

    void setStyle(Button.ButtonStyle style);

    Cell<Label> add(CharSequence text);
}
