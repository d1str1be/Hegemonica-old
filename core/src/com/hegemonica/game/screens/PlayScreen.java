package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hegemonica.game.Core;
import com.hegemonica.game.logic.Gemelch;
import com.hegemonica.game.screens.hud.HUD;

public class PlayScreen implements Screen {
    Core game;
    PlayScreenMap map;
    Stage stage;
    HUD hud;
    Gemelch gemelch;
    Skin GlassyUI;
    
    public PlayScreen(Core game, int provCountWidth, int provCountHeight) {
        this.game = game;
        map = new PlayScreenMap(game, provCountWidth, provCountHeight);
    }
    
    
    @Override
    public void show() {
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render(delta);
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
