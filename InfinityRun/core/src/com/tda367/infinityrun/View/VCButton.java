package com.tda367.infinityrun.View;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.tda367.infinityrun.View.IDrawnByDrawer;


/*
* VCButton for View/Controller Button, a button that can be split into view and controller. Alternative name could be
* SplitButton or similar.

* A version of the libGDX button that inplements the IDrawnByDrawer interface, letting the controlling screens
* use the button as a controller, while the drawer classes only hold a reference to the interface, preventing it from
* doing anything with the button that isn't related to drawing it.
* Since some buttons will need labels it also contains elements found in the TextButton class, although far from all
* of them. It would not be worth extending TextButton instead of Button as TextButton has many unneeded or even unwanted
* features and requirements.
*/

public class VCButton extends Button{

    //These do nothing unless the addText() method is called. This is intentional.
    private Label label;
    private String text = "";
    private final float defaultWidth = 120;
    private final float defaultHeight = 32;

    public VCButton(){
        super();
        setSize(defaultWidth,defaultHeight);
    }


    //Adds text to the button, requires a style to be added.
    public void setAndDisplayText(String text, TextButton.TextButtonStyle style){
        setText(text);
        displayText(style);
    }

    public void displayText(TextButton.TextButtonStyle style){
        float currX = getX()+getWidth()/2;
        float currY = getY()+getHeight()/2;
        setStyle(style);
        label = new Label(this.text, new Label.LabelStyle(style.font, style.fontColor));
        label.setAlignment(Align.center);
        add(label).expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
        setPosition(currX-getWidth()/2,currY-getHeight()/2);
    }

    public void changeDisplayText(String text){
        label.setText(text);
        label.invalidate();
    }

    public void setText(String text){
        this.text = text;
    }


    public Label getLabel() {
        return label;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //System.out.println("vcbutton draw() reached");
        super.draw(batch, parentAlpha);
    }




}

