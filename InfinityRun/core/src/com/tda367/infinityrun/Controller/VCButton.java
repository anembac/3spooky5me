package com.tda367.infinityrun.Controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;


/*
* A version of the libGDX button that inplements the IDrawnByDrawer interface, letting the controlling screens
* use the button as a controller, while the drawer classes only hold a reference to the interface, preventing it from
* doing anything with the button other than drawing it.
*/
public class VCButton extends Button implements IDrawnByDrawer {

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
