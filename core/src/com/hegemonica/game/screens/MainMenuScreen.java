package com.hegemonica.game.screens;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hegemonica.game.HegemonicaGame;

public class MainMenuScreen implements Screen {
   HegemonicaGame game;
    BitmapFont menufont;
    Table table;
    TextButton bPlay;
    TextButton bSettings;
    TextButton bExit;
    Skin GlassyUI;
    Stage stage;
    float centerButtonHeight;
    float centerButtonWidth;
    Label outputLabel;

    public MainMenuScreen(HegemonicaGame game){
        this.game = game;
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        centerButtonHeight = Gdx.graphics.getHeight()/12;
        centerButtonWidth = Gdx.graphics.getWidth()/12*5;

        menufont = new BitmapFont(Gdx.files.internal("fonts/Land_font.fnt"), Gdx.files.internal("fonts/Land_font.png"),false);

        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));

        bPlay = new TextButton("Play", GlassyUI);
        bPlay.setSize(centerButtonWidth, centerButtonHeight);
        bPlay.setPosition(Gdx.graphics.getWidth()/2-centerButtonWidth,Gdx.graphics.getHeight()-centerButtonHeight*);
        bPlay.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                bPlay.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                bPlay.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(bPlay);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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

    }
}
