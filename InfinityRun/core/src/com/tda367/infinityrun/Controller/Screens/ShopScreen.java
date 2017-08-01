package com.tda367.infinityrun.Controller.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Model.Shop;
import com.tda367.infinityrun.Utils.ScreenStates;
import com.tda367.infinityrun.View.Screens.ShopScreenDrawer;

import java.util.Observable;

public class ShopScreen extends Observable implements Screen {
    private final Shop shop;
    private final String[] nameList;
    private final Stage shopStage = new Stage();
    private ShopScreenDrawer shopScreenDrawer;
    private Table buttonTable = new Table();
    private Character character;

    private final VCButton[] buttonArray;


    private final int numberOfUpgrades;
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            buttonClickedCheck(getIndex(actor.getName()));
        }
    };


    public ShopScreen(Character character, Shop shop) {
        this.shop = shop;
        this.character = character;

        numberOfUpgrades = shop.getUpgList().size();
        buttonArray = new VCButton[numberOfUpgrades + 1];
        nameList = shop.getUpgNameList();

        for (int i = 0; i < numberOfUpgrades; i++) {

            //create buttons
            buttonArray[i] = new VCButton();

            //adds listener to button
            buttonArray[i].addListener(changeListener);

            //adds button to table
            if (i % 3 == 0) {
                buttonTable.row();
            }
            buttonTable.add(buttonArray[i]);

        }//end of loop

        buttonArray[numberOfUpgrades] = new VCButton();


        buttonArray[numberOfUpgrades].addListener(changeListener);


        //Add actors to stage
        shopStage.addActor(buttonTable);
        shopStage.addActor(buttonArray[numberOfUpgrades]);
        //shopStage.addActor(buttonArray[numberOfUpgrades]);



        //Create the view
        shopScreenDrawer = new ShopScreenDrawer(character, shop, buttonTable, buttonArray);



    }//end of constructor


    @Override
    public void show() {
        shop.setDisplayPoorMessage(false);
        shopScreenDrawer.show();
        Gdx.input.setInputProcessor(shopStage);

    }

    @Override
    public void render(float delta) {
        shopScreenDrawer.draw(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
        }
    }


    private void buttonClickedCheck(int i) {
        if (i >= 0 && i < numberOfUpgrades) { //i>=0 because getIndex() returns -1 on unfamiliar actor names
            shop.purchaseUpgrade(nameList[i]);
            shopScreenDrawer.updateButtonInformation();


        } else if (i >= numberOfUpgrades) { //should only be true for the back button
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
        }
    }

    private int getIndex(String name) {
        for (int i = 0; i <= numberOfUpgrades; i++) {

            if (name.equals(buttonArray[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public void resize(int width, int height){}
    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void hide(){}

    @Override
    public void dispose() {
        shopStage.dispose();
    }
}
