package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.HegemonicaLog;
import com.hegemonica.game.localization.LocalizationKeys;
import com.hegemonica.game.logic.scenarios.gemelch.Gemelch;

import static com.hegemonica.game.AudioManager.Sounds.UI_CLICK;

public class PlayScreen implements Screen {
    PlayScreenMap map;
    Gemelch gemelch;
    Core game;
    public OrthographicCamera camera;
    public Viewport viewport;

    Skin GlassyUI;

    Stage stage;
    TextButton bBack;
    final float bWidth = Gdx.graphics.getWidth() / 12f * 3f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;



    public PlayScreen(Core game, int provCountWidth, int provCountHeight) {
        this.game = game;
        gemelch = new Gemelch(game, provCountWidth, provCountHeight);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        map = new PlayScreenMap(game, camera, viewport, gemelch);

        bBack = new TextButton(game.loc.getString(LocalizationKeys.Keys.Back), GlassyUI);
        bBack.setSize(bWidth, bHeight);
        bBack.setPosition(Gdx.graphics.getWidth() - bBack.getWidth(), 0);
        bBack.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                HegemonicaLog.log(HegemonicaLog.Tags.INPUT, "TouchUp Back button");
                game.audio.playSound(UI_CLICK);
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                HegemonicaLog.log(HegemonicaLog.Tags.INPUT, "TouchDown Back button");
                return true;
            }
        });
        stage.addActor(bBack);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render(camera);
        gemelch.render(camera);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        map.resize(width, height);
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
        map.dispose();
    }
}
