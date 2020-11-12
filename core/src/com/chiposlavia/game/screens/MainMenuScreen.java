package com.chiposlavia.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.chiposlavia.game.ChiposlaviaGame;

public class MainMenuScreen implements Screen {
    ChiposlaviaGame game;
    BitmapFont menufont;
    public MainMenuScreen(ChiposlaviaGame game){
        this.game = game;
    }
    @Override
    public void show() {
        menufont = new BitmapFont(Gdx.files.internal("Land_font.fnt"), Gdx.files.internal("Land_font.png"),false);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);

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
