package com.tda367.infinityrun.Controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;


/*
* VCButton for View/Controller Button, a button that can be split into view and controller. Alternative name could be
* SplitButton or similar.

* A version of the libGDX button that inplements the IDrawnByDrawer interface, letting the controlling screens
* use the button as a controller, while the drawer classes only hold a reference to the interface, preventing it from
* doing anything with the button that isn't related to drawing it.
* SplitButton or similar.
* Since some buttons will need labels it also contains elements found in the TextButton class, although far from all
* of them. It would not be worth extending TextButton instead of Button as TextButton has many unneeded or even unwanted
* features and requirements.
*/

public class VCButton extends Button implements IDrawnByDrawer {

    //These do nothing unless the addText() method is called. This is intentional.
    private Label label;
    private TextButton.TextButtonStyle style;



    //Adds text to the button, requires a style to be added.
    public void addText(String text, TextButton.TextButtonStyle style){
        setStyle(style);
        this.style = style;
        label = new Label(text, new Label.LabelStyle(style.font, style.fontColor));
        label.setAlignment(Align.center);
        add(label).expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setText(String text){

        setStyle(this.style);
        label = new Label(text, new Label.LabelStyle(style.font, style.fontColor));
        label.setAlignment(Align.center);
        add(label).expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
    }


    public Label getLabel() {
        return label;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}

