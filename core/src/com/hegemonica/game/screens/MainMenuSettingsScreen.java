package com.hegemonica.game.screens;

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

import static com.hegemonica.game.AudioManager.Sounds.UI_CLICK;
import static com.hegemonica.game.localization.LocalizationKeys.Keys;

class MainMenuSettingsScreen implements Screen {
    Core game;

    Skin defaultSkin;
    Skin GlassyUI;

    Stage stage;
    Table table;
    Label lSettings;
    SelectBox<String> languageSelection;
    Slider musicSlider;
    Label musicSliderLabel;
    Slider soundSlider;
    Label soundSliderLabel;
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

        languageBoxItems.add(game.loc.getString(Keys.English));
        // languageBoxItems.add(game.loc.getString(Keys.English), game.loc.getString(Keys.Russian));

        lSettings = new Label(Keys.Settings.toString(), GlassyUI, "big");
        lSettings.setWidth(Gdx.graphics.getWidth());
        lSettings.setScale(1.5f);

        languageSelection = new SelectBox<String>(GlassyUI);
        languageSelection.setName(game.loc.getString(Keys.Language));
        languageSelection.setWidth(Gdx.graphics.getWidth());
        languageSelection.setItems(languageBoxItems);
        languageSelection.setScale(5f);
        languageSelection.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                switch (languageSelection.getSelectedIndex()) {
                    case 0: //english
                        game.loc.updateLanguage(game.loc.englishLocFile);
                    case 1: //russian
                        game.loc.updateLanguage(game.loc.russianLocFile);
                    default:
                        game.loc.updateLanguage(game.loc.englishLocFile);
                }
            }
        });

        musicSliderLabel = new Label(game.loc.getString(Keys.Music_Volume), GlassyUI);
        musicSlider = new Slider(0, 1, 0.001f, false, GlassyUI);
        musicSlider.setValue(game.audio.getMusicVolume());
        musicSlider.setWidth(Gdx.graphics.getWidth());
        musicSlider.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                game.audio.setMusicVolume(musicSlider.getValue());
            }
        });

        soundSliderLabel = new Label(game.loc.getString(Keys.Sound_Volume), GlassyUI);
        soundSlider = new Slider(0, 1, 0.001f, false, GlassyUI);
        soundSlider.setValue(game.audio.getSoundVolume());
        soundSlider.setWidth(Gdx.graphics.getWidth());
        soundSlider.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                game.audio.setSoundVolume(soundSlider.getValue());
            }

        });

        bBack = new TextButton(game.loc.getString(Keys.Back), GlassyUI);
        bBack.setSize(bWidth, bHeight);
        bBack.setPosition(Gdx.graphics.getWidth() - bBack.getWidth(), 0);
        bBack.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.audio.playSound(UI_CLICK);
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
        table.row().colspan(3);
        table.add(languageSelection);
        table.row().colspan(2);
        table.add(musicSliderLabel);
        table.add(musicSlider).width(Gdx.graphics.getWidth() / 3f);
        table.row().colspan(2);
        table.add(soundSliderLabel);
        table.add(soundSlider).width(Gdx.graphics.getWidth() / 3f);
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
