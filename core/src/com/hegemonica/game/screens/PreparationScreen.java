package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hegemonica.game.Core;

import static com.hegemonica.game.Core.gameHeight;
import static com.hegemonica.game.Core.gameWidth;

public class PreparationScreen implements Screen {
    Core game;
    Stage stage;
    Skin defaultSkin;
    int provCountWidth = 2;
    int provCountHeight = 2;

    Table table;
    Window window;
    Slider sProvCount1;
    Slider sProvCount2;
    Label lProvCount1;
    Label lProvCount2;
    Label lIntProvCount1;
    Label lIntProvCount2;
    TextButton bPlay;
    TextButton bBack;

    public PreparationScreen(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        defaultSkin = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        table = new Table(defaultSkin);
        window = new Window("Set Up Map", defaultSkin);
        window.setBounds(gameWidth * 0.4f, gameHeight * 0.3f, gameWidth * 0.2f, gameHeight * 0.35f);

        lProvCount1 = new Label("Prov Count in width", defaultSkin);
        sProvCount1 = new Slider(provCountWidth, 10, 1, false, defaultSkin);
        lProvCount2 = new Label("Prov Count in height", defaultSkin);
        sProvCount2 = new Slider(provCountHeight, 10, 1, false, defaultSkin);
        lIntProvCount1 = new Label(Integer.toString(provCountWidth), defaultSkin);
        lIntProvCount2 = new Label(Integer.toString(provCountHeight), defaultSkin);
        bPlay = new TextButton("Play", defaultSkin, "toggle");
        bBack = new TextButton("Back", defaultSkin, "toggle");
        this.setListeners();

        window.align(Align.top);
        window.add(bPlay);
        window.row();
        window.add(lProvCount1);
        window.row();
        window.add(sProvCount1);
        window.add(lIntProvCount1);
        window.row();
        window.add(lProvCount2);
        window.add(lIntProvCount2);
        window.row();
        window.add(sProvCount2);
        window.add(lIntProvCount2);
        window.row();
        window.add(bBack);
        stage.addActor(window);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
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
        stage.dispose();
    }

    public void setListeners() {
        Gdx.input.setInputProcessor(stage);
        bPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new PlayScreen(game, provCountWidth, provCountHeight));
            }
        });
        bBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        sProvCount1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                provCountWidth = (int) sProvCount1.getValue();
                lIntProvCount1.setText(Integer.toString(provCountWidth));
            }
        });
        sProvCount2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                provCountHeight = (int) sProvCount2.getValue();
                lIntProvCount2.setText(Integer.toString(provCountHeight));
            }
        });
    }
}
