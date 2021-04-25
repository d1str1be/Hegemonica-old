package com.hegemonica.game.screens.playscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.Framerate;
import com.hegemonica.game.logic.scenarios.gemelch.Gemelch;

import java.awt.Polygon;

public class TestVersionScreen implements Screen {
    TestMap testMap;
    Gemelch gemelch;
    Core game;
    public OrthographicCamera camera;
    public Viewport viewport;
    public TestVersionScreen(Core game){
        this.game = game;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        testMap = new TestMap(camera, viewport, gemelch);
        gemelch = new Gemelch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        testMap.render(camera);
        gemelch.render(camera);
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
