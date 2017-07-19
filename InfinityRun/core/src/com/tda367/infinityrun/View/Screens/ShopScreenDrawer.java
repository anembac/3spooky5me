package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Model.Shop;

public class ShopScreenDrawer extends ScreenDrawer{

    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Upgrades/upgrades.pack"));
    private final String[] nameList;
    private final Label shopMessage;
    private final int numberOfUpgrades;
    private final Table upgradeTable;
    private Shop shop;
    private final VCButton[] buttonArray;
    TextButton.TextButtonStyle[] buttonStyles;


    public ShopScreenDrawer(Shop shop, Table table, VCButton[] buttonArray){

        //constructor is pretty long...

        this.shop = shop;
        this.buttonArray = buttonArray;
        nameList = shop.getUpgNameList();
        upgradeTable = table;
        numberOfUpgrades = shop.getUpgList().size();
        TextureRegion[] textureRegionsUp = new TextureRegion[numberOfUpgrades + 1];
        TextureRegion[] textureRegionsDown = new TextureRegion[numberOfUpgrades + 1];
        TextureRegionDrawable[] texturesUpDrawable = new TextureRegionDrawable[numberOfUpgrades + 1];
        TextureRegionDrawable[] texturesDownDrawable = new TextureRegionDrawable[numberOfUpgrades + 1];
        buttonStyles = new TextButton.TextButtonStyle[numberOfUpgrades + 1];



        //Loading in images and connecting them with the buttons, as well as connecting the buttons with the upgrades
        for (int i = 0; i < numberOfUpgrades; i++) {
            textureRegionsUp[i] = new TextureRegion((atlas.findRegion(nameList[i])));
            textureRegionsDown[i] = new TextureRegion((atlas.findRegion(nameList[i] + "down")));
            texturesUpDrawable[i] = new TextureRegionDrawable(textureRegionsUp[i]);
            texturesDownDrawable[i] = new TextureRegionDrawable(textureRegionsDown[i]);
            buttonStyles[i] = new TextButton.TextButtonStyle(
                    texturesUpDrawable[i], texturesDownDrawable[i], texturesUpDrawable[i], font);
            updateButtonInformation(i);
            buttonArray[i].getLabel().setWrap(true);
            buttonArray[i].getLabel().setWidth(128);
            buttonArray[i].setName(nameList[i]);
            buttonArray[i].getCell(buttonArray[i].getLabel()).padBottom(-158);

            //Creates a space between the buttons.
            upgradeTable.getCell(buttonArray[i]).width(buttonArray[i].getPrefWidth()).pad(15);
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
        buttonArray[numberOfUpgrades].setAndDisplayText("Back", buttonStyles[numberOfUpgrades]);
        buttonArray[numberOfUpgrades].setName("back");

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, new Color(255, 0, 0, 255));
        shopMessage = new Label("", labelStyle);


        //Positions
        shopMessage.setPosition(Constants.windowWidth/2, 75);
        buttonArray[numberOfUpgrades].setPosition(1400, 50);
        upgradeTable.setPosition(Constants.windowWidth/2, Constants.windowHeight/2);
        System.out.println(upgradeTable.getX());
        System.out.println(upgradeTable.getY());


    }//end of constructor

    public void draw(float delta) {
        //Update button information

        //Drawing
        //updateButtonInformation();
        batch.begin();
        super.draw(vcButtons, delta);
        upgradeTable.draw(batch, 1);
        buttonArray[numberOfUpgrades].draw(batch, 1);
        if (shop.displayPoorMessage()) {
            shopMessage.setText("You cannot afford this upgrade.");
            shopMessage.setPosition(800 - shopMessage.getPrefWidth() / 2, 75);
            shopMessage.draw(batch,1);
        } else {
            shopMessage.setText("");
        }
        batch.end();
    }


    private void updateButtonInformation(int i){
        buttonArray[i].setAndDisplayText(nameList[i]
                + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                + ", Price: "
                + shop.getPrice(shop.getUpgList().get(nameList[i])), buttonStyles[i]);
    }

    private void updateButtonInformation(){
        for(int i = 0; i < numberOfUpgrades; i++){
            buttonArray[i].setAndDisplayText(nameList[i]
                    + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                    + ", Price: "
                    + shop.getPrice(shop.getUpgList().get(nameList[i])), buttonStyles[i]);

            buttonArray[i].getLabel().setWrap(true);
            buttonArray[i].getLabel().setWidth(128);
            buttonArray[i].setName(nameList[i]);
            buttonArray[i].getCell(buttonArray[i].getLabel()).padBottom(-158);
            //Makes a new row every 3 upgrades
            if (i % 3 == 0) {
                upgradeTable.row();
            }
            //Fetches the cost for the corresponding upgrade.
            //buttonArray[i].add("Cost: " + shop.getPrice(shop.getUpgList().get(nameList[i])));
            upgradeTable.add(buttonArray[i]).width(buttonArray[i].getPrefWidth()).pad(15);
        }




    }
}
