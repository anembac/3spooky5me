package com.tda367.infinityrun.Controller.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.View.IDrawnByDrawer;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Utils.LoadCharacter;
import com.tda367.infinityrun.Utils.ScreenStates;
import com.tda367.infinityrun.View.Screens.LoadScreenDrawer;


import java.util.Observable;

import static com.tda367.infinityrun.Utils.Constants.windowWidth;

//separated
public class LoadScreen extends Observable implements Screen {

    private final Stage loadStage;
    private LoadScreenDrawer loadScreenDrawer;
    Table table = new Table();


    public LoadScreen() {
        loadStage = new Stage();
        int numberOfButtons = LoadCharacter.getNumberOfSaves();
        VCButton[] buttons = new VCButton[numberOfButtons];

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Implement loading properly
                int loadID = Integer.parseInt(actor.getName());
                System.out.println("Button pressed");
                setChanged();
                notifyObservers(ScreenStates.GameScreen);

            }
        };
        for (int i = 0; i < numberOfButtons; i++) {

            buttons[i] = new VCButton();
            buttons[i].addListener(changeListener);
            if(i % 10 == 0){
                table.row();
            }
            table.add(buttons[i]).pad(5);
        }
        //Set table position
        table.setPosition(windowWidth/2 - table.getWidth()/2, Constants.windowHeight/2 - table.getHeight()/2);

        //Add to stage
        loadStage.addActor(table);

        //Create drawer/view
        loadScreenDrawer = new LoadScreenDrawer(table, buttons);

    }


    @Override
    public void show() {
        loadScreenDrawer.show();
        Gdx.input.setInputProcessor(loadStage);

    }

    @Override
    public void render(float delta) {
        loadScreenDrawer.draw(delta);
    }


    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        loadStage.dispose();
        deleteObservers();
    }

}
