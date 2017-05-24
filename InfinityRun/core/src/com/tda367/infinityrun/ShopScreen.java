package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ShopScreen implements Screen {

    Table upgradeTable;
    Shop shop;
    Stage shopStage = new Stage();
    BitmapFont font = new BitmapFont();
    SpriteBatch batch = new SpriteBatch();
    OrthographicCamera camera = new OrthographicCamera();
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    Button.ButtonStyle style = 
            new Button.ButtonStyle(textureUpDrawable, textureDownDrawable, textureUpDrawable);
    Button[] buttonArray;
    String[] nameList;
    static int count = 0;



    public ShopScreen(Shop shop){
        this.shop = shop;
        nameList = shop.getUpgNameList();
        upgradeTable = new Table();
        buttonArray = new Button[shop.getUpgList().size()];
        for(int i = 0; i < buttonArray.length; i++){
            buttonArray[i] = new Button(style);
            upgradeTable.add(buttonArray[i]);
        }
        camera.setToOrtho(false, 1600, 900);
        upgradeTable.setPosition(1600/2-upgradeTable.getMaxWidth(), 900/2-upgradeTable.getMaxHeight());
        shopStage.addActor(upgradeTable);
        Gdx.input.setInputProcessor(shopStage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(!(altButtonClick()==0)){
            count -= count;
            buttonClickedCheck();
        }
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        shopStage.draw();

        //Batch&Font rendering
        batch.setProjectionMatrix(camera.combined);
        upgradeTable.draw(batch, 1);
        batch.end();
    }


    private void buttonClickedCheck(){
        for(int i = 0; i<buttonArray.length;i++){
            if(buttonArray[i].isPressed()){
                shop.purchaseUpgrade(nameList[i]);
            }
        }
    }

    private void clickButton(int x){
        shop.purchaseUpgrade((nameList[x]));
    }

    private int altButtonClick(){
        for(int i = 0; i<buttonArray.length;i++){
            if(buttonArray[i].isPressed()){
                System.out.println("Ping " + ++count);
                return i;
            }
        }
        return 0;
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
        atlas.dispose();


    }
}
