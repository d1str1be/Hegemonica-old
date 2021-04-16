package com.hegemonica.game.screens.playscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.hegemonica.game.Core;
import com.hegemonica.game.Framerate;

public class TestVersionScreen implements Screen {
    TestMap testMap;

    Core game;

    public TestVersionScreen(Core game){
        this.game = game;
    }
    @Override
    public void show() {
        testMap = new TestMap();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        testMap.render();

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
