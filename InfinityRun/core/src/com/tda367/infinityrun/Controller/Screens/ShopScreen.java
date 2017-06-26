package com.tda367.infinityrun.Controller.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.Model.Shop;
import com.tda367.infinityrun.Utils.ScreenStates;

import java.util.Observable;

public class ShopScreen extends Observable implements Screen {
    private final GameScreen masterScreen;
    private final Table upgradeTable;
    private final Shop shop;
    private final Stage shopStage = new Stage();
    private final SpriteBatch batch = new SpriteBatch();
    private final OrthographicCamera camera = new OrthographicCamera();
    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Upgrades/upgrades.pack"));
    private final TextButton[] buttonArray;
    private final String[] nameList;
    private final Label shopMessage;
    private final int numberOfUpgrades;
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            //System.out.println(actor.getName());
            buttonClickedCheck(getIndex(actor.getName()));
        }
    };;


    public ShopScreen(Shop shop, GameScreen masterScreen) {   //constructor is pretty long...
        this.masterScreen = masterScreen;
        this.shop = shop;
        nameList = shop.getUpgNameList();
        upgradeTable = new Table();
        numberOfUpgrades = shop.getUpgList().size();
        //System.out.println("number of upgrade: " + numberOfUpgrades);


        buttonArray = new TextButton[numberOfUpgrades + 1];
        TextureRegion[] textureRegionsUp = new TextureRegion[numberOfUpgrades + 1];
        TextureRegion[] textureRegionsDown = new TextureRegion[numberOfUpgrades + 1];
        TextureRegionDrawable[] texturesUpDrawable = new TextureRegionDrawable[numberOfUpgrades + 1];
        TextureRegionDrawable[] texturesDownDrawable = new TextureRegionDrawable[numberOfUpgrades + 1];
        TextButton.TextButtonStyle[] buttonStyles = new TextButton.TextButtonStyle[numberOfUpgrades + 1];

        //Loading in images and connecting them with the buttons, as well as connecting the buttons with the upgrades
        BitmapFont font = new BitmapFont();
        for (int i = 0; i < numberOfUpgrades; i++) {
            //System.out.println(nameList[i]);
            textureRegionsUp[i] = new TextureRegion((atlas.findRegion(nameList[i])));
            textureRegionsDown[i] = new TextureRegion((atlas.findRegion(nameList[i] + "down")));
            texturesUpDrawable[i] = new TextureRegionDrawable(textureRegionsUp[i]);
            texturesDownDrawable[i] = new TextureRegionDrawable(textureRegionsDown[i]);
            buttonStyles[i] = new TextButton.TextButtonStyle(
                    texturesUpDrawable[i], texturesDownDrawable[i], texturesUpDrawable[i], font);
            buttonArray[i] = new TextButton(nameList[i]
                    + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                    + ", Price: "
                    + shop.getPrice(shop.getUpgList().get(nameList[i])), buttonStyles[i]);
            buttonArray[i].getLabel().setWrap(true);
            buttonArray[i].getLabel().setWidth(128);
            buttonArray[i].setName(nameList[i]);
            buttonArray[i].getCell(buttonArray[i].getLabel()).padBottom(-158);
            //adds listener to button
            buttonArray[i].addListener(changeListener);

            //Makes a new row every 3 upgrades
            if (i % 3 == 0) {
                upgradeTable.row();
            }
            //Fetches the cost for the corresponding upgrade.
            //buttonArray[i].add("Cost: " + shop.getPrice(shop.getUpgList().get(nameList[i])));
            upgradeTable.add(buttonArray[i]).width(buttonArray[i].getPrefWidth()).pad(15);
        }//end of loop

        //Since array initilization uses the amount of elements wanted, but then starts indexing at 0,
        //"numberOfUpgrades" actually gives the empty index that comes after all the upgrades since the arrays
        //have numberOfUpgrades+1 elements.
        textureRegionsUp[numberOfUpgrades] = new TextureRegion(atlas.findRegion("back"));
        textureRegionsDown[numberOfUpgrades] = new TextureRegion(atlas.findRegion("backdown"));
        texturesUpDrawable[numberOfUpgrades] = new TextureRegionDrawable(textureRegionsUp[numberOfUpgrades]);
        texturesDownDrawable[numberOfUpgrades] = new TextureRegionDrawable(textureRegionsDown[numberOfUpgrades]);
        buttonStyles[numberOfUpgrades] = new TextButton.TextButtonStyle
                (texturesUpDrawable[numberOfUpgrades],
                        texturesDownDrawable[numberOfUpgrades],
                        texturesUpDrawable[numberOfUpgrades], font);
        buttonArray[numberOfUpgrades] = new TextButton("Back", buttonStyles[numberOfUpgrades]);
        buttonArray[numberOfUpgrades].setPosition(1400, 50);
        buttonArray[numberOfUpgrades].setName("back");
        buttonArray[numberOfUpgrades].addListener(changeListener);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, new Color(255, 0, 0, 255));
        shopMessage = new Label("", labelStyle);
        shopMessage.setPosition(800, 75);
        shopStage.addActor(shopMessage);


        camera.setToOrtho(false, 1600, 900);

        upgradeTable.setPosition(1600 / 2 - upgradeTable.getMaxWidth(), 900 / 2 - upgradeTable.getMaxHeight());
        shopStage.addActor(buttonArray[numberOfUpgrades]);
        shopStage.addActor(upgradeTable);


    }//end of constructor


    @Override
    public void show() {

        Gdx.input.setInputProcessor(shopStage);
        changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //System.out.println(actor.getName());
                buttonClickedCheck(getIndex(actor.getName()));
            }
        };
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        if (shop.displayPoorMessage()) {
            shopMessage.setText("You cannot afford this upgrade.");
            shopMessage.setPosition(800 - shopMessage.getPrefWidth() / 2, 75);
        } else {
            shopMessage.setText("");
        }

        batch.begin();
        shopStage.draw();

        //Batch&Font rendering
        batch.setProjectionMatrix(camera.combined);
        upgradeTable.draw(batch, 0);
        batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
            this.dispose();
        }
    }


    private void buttonClickedCheck(int i) {
        if (i >= 0 && i < numberOfUpgrades) { //i>=0 because getIndex() returns -1 on unfamiliar actor names
            shop.purchaseUpgrade(nameList[i]);
            buttonArray[i].getLabel().setText(nameList[i]
                    + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                    + ", Price: "
                    + shop.getPrice(shop.getUpgList().get(nameList[i])));


        } else if (i >= numberOfUpgrades) { //should only happen for the back button


            //not sure where the back button should lead until we implement saving
            //currently it works as an unpause button so that you upgrade as you go rather than on death
            setChanged();
            notifyObservers(ScreenStates.GameScreen);
            this.dispose();
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
        shopStage.dispose();
        batch.dispose();
        atlas.dispose();
    }
}
