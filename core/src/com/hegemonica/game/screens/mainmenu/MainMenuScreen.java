package com.hegemonica.game.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hegemonica.game.Framerate;
import com.hegemonica.game.Core;
import com.hegemonica.game.localization.MenuLoc;
import com.hegemonica.game.screens.playscreen.TestVersionScreen;

import jdk.incubator.jpackage.internal.Log;

public class MainMenuScreen implements Screen {
    Core game;
    BitmapFont menufont;

    Table table;
    TextButton bPlay;
    TextButton bSettings;
    TextButton bExit;

    Skin GlassyUI;
    Stage stage;
    Label hegemonicaLabel;

    Framerate fps;

    float centerButtonHeight;
    float centerButtonWidth;

    int startTime;
    int timeTillStart;


    public MainMenuScreen(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        menufont = new BitmapFont(Gdx.files.internal("fonts/land.fnt"), Gdx.files.internal("fonts/land.png"), false);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        fps = new Framerate();


        //таймер с начала игры
        startTime = (int) System.currentTimeMillis();

        centerButtonHeight = Gdx.graphics.getHeight() / 8f;
        centerButtonWidth = Gdx.graphics.getWidth() / 12f * 5f;

        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));
        //надпись "гегемоника"
        hegemonicaLabel = new Label("Hegemonica", GlassyUI, "big");
        hegemonicaLabel.setAlignment(Align.center);
        hegemonicaLabel.setY(Gdx.graphics.getHeight() * 2 / 3f);
        hegemonicaLabel.setWidth(Gdx.graphics.getWidth());
        hegemonicaLabel.setFontScale(2);
        stage.addActor(hegemonicaLabel);

        //кнопка "играть"
        bPlay = new TextButton(MenuLoc.Buttons.Play.toString(), GlassyUI);
        bPlay.setSize(centerButtonWidth, centerButtonHeight);
        bPlay.setPosition((Gdx.graphics.getWidth() - centerButtonWidth) / 2, Gdx.graphics.getHeight() - centerButtonHeight * 5);
        bPlay.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new TestVersionScreen(game));
                dispose();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        stage.addActor(bPlay);

        //кнопка "настройки"
        bSettings = new TextButton(MenuLoc.Buttons.Settings.toString(), GlassyUI);
        bSettings.setSize(centerButtonWidth, centerButtonHeight);
        bSettings.setPosition((Gdx.graphics.getWidth() - centerButtonWidth) / 2, Gdx.graphics.getHeight() - centerButtonHeight * 6);
        bSettings.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(Core.Tags.DEFAULT, "Zapustil");
                game.setScreen(new MainMenuSettingsScreen(game));
                dispose();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        stage.addActor(bSettings);

        //кнопка "выйти"
        bExit = new TextButton(MenuLoc.Buttons.Exit.toString(), GlassyUI);
        bExit.setSize(centerButtonWidth, centerButtonHeight);
        bExit.setPosition((Gdx.graphics.getWidth() - centerButtonWidth) / 2, Gdx.graphics.getHeight() - centerButtonHeight * 7);
        bExit.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(bExit);

        Gdx.app.log(Core.Tags.ENGINE, "Engine log");

        // creating animations
//        tweenManager = new TweenManager();
//        Tween.registerAccessor(Actor.class, new ActorAccessor());
//
//        // heading and buttons fade-in
//        Timeline.createSequence().beginSequence()
//                .push(Tween.set(bPlay, ActorAccessor.ALPHA).target(0))
//                .push(Tween.set(bSettings, ActorAccessor.ALPHA).target(0))
//                .push(Tween.set(bExit, ActorAccessor.ALPHA).target(0))
//                .push(Tween.from(hegemonicaLabel, ActorAccessor.ALPHA, .25f).target(0))
//                .push(Tween.to(bPlay, ActorAccessor.ALPHA, .25f).target(1))
//                .push(Tween.to(bSettings, ActorAccessor.ALPHA, .25f).target(1))
//                .push(Tween.to(bExit, ActorAccessor.ALPHA, .25f).target(1))
//                .end().start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        fps.render();
        timerupdate();
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

    public void timerupdate() {
        timeTillStart = (int) ((System.currentTimeMillis() - startTime) / 1000);
    }
}
