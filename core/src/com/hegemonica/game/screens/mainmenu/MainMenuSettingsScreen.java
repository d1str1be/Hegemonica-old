package com.hegemonica.game.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hegemonica.game.Core;

class MainMenuSettingsScreen implements Screen {
    Core game;

    Skin defaultSkin;
    Skin GlassyUI;

    Stage stage;
    Table table;
    Label lSettings;
    SelectBox<String> languageSelection;
    Slider musicSlider;
    Slider soundSlider;
    TextButton bBack;

    Array<String> languageBoxItems = new Array<>(true, 2);

    float bWidth;
    float bHeight;


    public MainMenuSettingsScreen(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        defaultSkin = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));

        bWidth = Gdx.graphics.getWidth() / 12f * 3f;
        bHeight = Gdx.graphics.getHeight() / 8f;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        languageBoxItems.add("English", "Russian");

        lSettings = new Label("Settings", GlassyUI, "big");
        lSettings.setWidth(Gdx.graphics.getWidth());
        lSettings.setScale(1.5f);

        languageSelection = new SelectBox<String>(GlassyUI);
        languageSelection.setName("Language");
        languageSelection.setWidth(Gdx.graphics.getWidth());
        languageSelection.setItems(languageBoxItems);
        languageSelection.setScale(5f);

        musicSlider = new Slider(0, 1, 0.05f, false, GlassyUI);
        musicSlider.setValue(1);
        musicSlider.setName("Music Volume");
        musicSlider.setWidth(Gdx.graphics.getWidth());
        musicSlider.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setMusicVolume(musicSlider.getValue());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        soundSlider = new Slider(0, 1, 0.05f, false, GlassyUI);

        bBack = new TextButton("Back", GlassyUI);
        bBack.setSize(bWidth, bHeight);
        bBack.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        table = new Table(GlassyUI);
        table.setSize(stage.getWidth(), stage.getHeight());
        table.setOrigin(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.65f);
        table.add(lSettings);
        table.row();
        table.add(languageSelection);
        table.row();
        table.add(musicSlider);
        table.row();
        table.add(bBack);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        stage.dispose();
    }
}
