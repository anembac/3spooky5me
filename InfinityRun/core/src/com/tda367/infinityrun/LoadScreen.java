package com.tda367.infinityrun;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tda367.infinityrun.WorldGeneration.TextbasedWorldGenerator;

public class LoadScreen implements Screen {
    final InfinityRun infRun;
    BitmapFont font = new BitmapFont();
    Stage loadStage;

    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);
    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
            textureUpDrawable,textureDownDrawable, textureUpDrawable, font);
    TextButton[] buttons;
    private int numberOfButtons;
    private ChangeListener changeListener;
    private Table buttonTable = new Table();


    public LoadScreen(InfinityRun game){
        infRun = game;
        loadStage = new Stage();
        numberOfButtons = LoadCharacter.getNumberOfSaves();
        buttons = new TextButton[numberOfButtons];

        changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                int whatthefuckdidyoujustsayaboutmeyoulittleshit = Integer.parseInt(actor.getName());
                dispose();
                infRun.setScreen(new GameScreen(infRun,
                        new World(new TextbasedWorldGenerator()
                                ,LoadCharacter.loadCharacter(whatthefuckdidyoujustsayaboutmeyoulittleshit))));

            }
        };
        for(int i = 0; i<numberOfButtons;i++){
            buttons[i] = new TextButton("Save "+(i+1), style);
            buttons[i].setName(""+(i+1));
            buttons[i].setWidth(128);
            buttons[i].addListener(changeListener);
            //buttonTable.add(buttons[i]);
            buttonTable.row();
            buttonTable.add(buttons[i]).width(buttons[i].getPrefWidth()).pad(5);
        }
        buttonTable.setPosition(800, 450);
        loadStage.addActor(buttonTable);

        Gdx.input.setInputProcessor(loadStage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        infRun.batch.begin();
        loadStage.draw();
        infRun.batch.end();

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
        atlas.dispose();
        font.dispose();
    }
}
