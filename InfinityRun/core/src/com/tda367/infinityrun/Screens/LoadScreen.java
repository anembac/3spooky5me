package com.tda367.infinityrun.Screens;


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
import com.tda367.infinityrun.InfinityRun;
import com.tda367.infinityrun.LoadCharacter;
import com.tda367.infinityrun.Screens.GameScreen;
import com.tda367.infinityrun.World;
import com.tda367.infinityrun.WorldGeneration.TextbasedWorldGenerator;

public class LoadScreen implements Screen {
    private final InfinityRun infRun;
    private final BitmapFont font = new BitmapFont();
    private final Stage loadStage;

    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("testpack1.pack"));
    private final TextureRegion textureUp = new TextureRegion(atlas.findRegion("testtexture"));
    private final TextureRegion textureDown = new TextureRegion(atlas.findRegion("testtexture2"));
    private final TextureRegionDrawable textureUpDrawable = new TextureRegionDrawable(textureUp);
    private final TextureRegionDrawable textureDownDrawable = new TextureRegionDrawable(textureDown);


    public LoadScreen(InfinityRun game){
        infRun = game;
        loadStage = new Stage();
        int numberOfButtons = LoadCharacter.getNumberOfSaves();
        TextButton[] buttons = new TextButton[numberOfButtons];

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                int whatthefuckdidyoujustsayaboutmeyoulittleshit = Integer.parseInt(actor.getName());
                dispose();
                infRun.setScreen(new GameScreen(infRun,
                        new World(new TextbasedWorldGenerator()
                                , LoadCharacter.loadCharacter(whatthefuckdidyoujustsayaboutmeyoulittleshit))));

            }
        };
        Table buttonTable = new Table();
        for(int i = 0; i< numberOfButtons; i++){
            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                    textureUpDrawable, textureDownDrawable, textureUpDrawable, font);
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
