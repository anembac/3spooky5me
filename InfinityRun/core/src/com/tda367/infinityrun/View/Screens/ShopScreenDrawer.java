package com.tda367.infinityrun.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.Model.Character;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.View.VCButton;
import com.tda367.infinityrun.Model.Shop;

public class ShopScreenDrawer extends ScreenDrawer{
    private final Character character;
    private final String[] nameList;
    private final Label shopMessage;
    private final int numberOfUpgrades;
    private final Table upgradeTable;
    private Shop shop;
    private final VCButton[] buttonArray;


    public ShopScreenDrawer(Character character, Shop shop, Table table, VCButton[] buttonArray){

        this.shop = shop;

            this.character = character;
        this.buttonArray = buttonArray;
        nameList = shop.getUpgNameList();
        upgradeTable = table;
        numberOfUpgrades = shop.getUpgList().size();
        TextButton.TextButtonStyle[] buttonStyles = new TextButton.TextButtonStyle[numberOfUpgrades + 1];

        //Generating the buttonstyles needed, plus other code to make it look nice
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Upgrades/upgrades.pack"));
        for (int i = 0; i < numberOfUpgrades; i++) {
            buttonStyles[i] = (TextButton.TextButtonStyle)generateStyle(atlas,
                    nameList[i],
                    nameList[i]+"down",
                    nameList[i],
                    true);

            buttonArray[i].setAndDisplayText(nameList[i]
                    + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                    + ", Price: "
                    + shop.getPrice(shop.getUpgList().get(nameList[i])), buttonStyles[i]);
            buttonArray[i].getLabel().setWrap(true);
            buttonArray[i].getLabel().setWidth(128);
            buttonArray[i].setName(nameList[i]);
            ///158 comes from the buttonwidth/height, 128 + a decent spacing for text, which happened to be 30
            buttonArray[i].getCell(buttonArray[i].getLabel()).padBottom(-158);

            //Creates a space between the buttons.
            upgradeTable.getCell(buttonArray[i]).width(buttonArray[i].getPrefWidth()).pad(15);
        }//end of loop

        buttonStyles[numberOfUpgrades] = (TextButton.TextButtonStyle) generateStyle(atlas,
                "back",
                "backdown",
                "back",
                true);
        buttonArray[numberOfUpgrades].setAndDisplayText("Back", buttonStyles[numberOfUpgrades]);
        buttonArray[numberOfUpgrades].setName("back");

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, new Color(255, 0, 0, 255));
        shopMessage = new Label("", labelStyle);


        //Positions
        shopMessage.setPosition(Constants.windowWidth/2, 75);
        buttonArray[numberOfUpgrades].setPosition(1400, 50);
        upgradeTable.setPosition(Constants.windowWidth/2, Constants.windowHeight/2);
    }//end of constructor

    public void draw(float delta) {
        //Update button information

        //Drawing
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

        //Character stats
        font.draw(batch, "Weapon:: "+  (character.getWeapon().getName()), 20, 800);
        font.draw(batch, "Damage: "+ Integer.toString((int)character.getDamage()), 20, 780);
        font.draw(batch, "Critical Chance : "+ Double.toString(character.getCriticalHitChance()) +  "%", 20, 760);
        font.draw(batch, "Critical Damage: "+ Integer.toString((int)character.getCriticalHitDamage()), 20, 740);
        font.draw(batch, "Regen: "+ Integer.toString(character.getRegeneration()), 20, 720);

        batch.end();
    }


    private void updateButtonInformation(int i){
        buttonArray[i].changeDisplayText(nameList[i]
                + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                + ", Price: "
                + shop.getPrice(shop.getUpgList().get(nameList[i])));
    }

    public void updateButtonInformation(){
        for(int i = 0; i < numberOfUpgrades; i++){
            buttonArray[i].changeDisplayText(nameList[i]
                    + ", Level: " + shop.getUpgList().get(nameList[i]).getLevel()
                    + ", Price: "
                    + shop.getPrice(shop.getUpgList().get(nameList[i])));
        }




    }
}
