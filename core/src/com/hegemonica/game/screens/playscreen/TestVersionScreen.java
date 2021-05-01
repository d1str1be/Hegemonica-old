package com.hegemonica.game.screens.playscreen;

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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.localization.LocalizationKeys;
import com.hegemonica.game.logic.scenarios.gemelch.Gemelch;
import com.hegemonica.game.screens.mainmenu.MainMenuScreen;

import static com.hegemonica.game.AudioManager.Sounds.UI_CLICK;

public class TestVersionScreen implements Screen {
    TestMap testMap;
    Gemelch gemelch;
    Core game;
    public OrthographicCamera camera;
    public Viewport viewport;

    Skin GlassyUI;

    Stage stage;
    TextButton bBack;
    final float bWidth = Gdx.graphics.getWidth() / 12f * 3f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;

    public TestVersionScreen(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        testMap = new TestMap(game, camera, viewport, gemelch);
        gemelch = new Gemelch(game);

        bBack = new TextButton(game.loc.getString(LocalizationKeys.Keys.Back), GlassyUI);
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
        stage.addActor(bBack);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        testMap.render(camera);
        gemelch.render(camera);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        testMap.resize(width, height);
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
        testMap.dispose();
    }
}
