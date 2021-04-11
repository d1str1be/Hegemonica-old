package com.hegemonica.game.devtools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.devtools.PolygonCreator;

public class MapEditorScreen implements Screen {
    private Core game;
    private PolygonCreator polycr;
    private BitmapFont font;
    private SpriteBatch batch;
    public MapEditorScreen (Core game){
        this.game = game;
    }
    @Override
    public void show() {
        font = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), Gdx.files.internal("fonts/test.png"), false);
        batch = new SpriteBatch();
        polycr = new PolygonCreator();
       // camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        polycr.render(delta);
        batch.begin();
        font.draw(batch, "Map Editor Screen", 10, (int) (Gdx.graphics.getHeight() - font.getScaleY()));
        batch.end();
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
        polycr.dispose();
    }
}
