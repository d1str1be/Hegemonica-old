package com.hegemonica.game.screens.playscreen;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

//Игровой экран - тестовая карта с  4мя провинциями

public class PlayScreen implements Screen {
    private Stage stage;
    Texture testTexture;
    int txtWidth = 480, txtHeight = 480;


    @Override
    public void show() {
    Files files;
    testTexture = new Texture(files.internal("icons/hegemonica-4.png"));
    stage.addActor(Texture);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
