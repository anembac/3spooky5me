package com.tda367.infinityrun.View;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/*
* More contained version of libGDX's Drawable interface, that only allows usage of the draw() and setStyle() methods.
*/
public interface IDrawnByDrawer {

    void draw(Batch batch, float parentAlpha);

    void setStyle(Button.ButtonStyle style);

    void setText(String text);

    void displayText(TextButton.TextButtonStyle style);

    void setAndDisplayText(String text, TextButton.TextButtonStyle style);
}
