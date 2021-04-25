package com.hegemonica.game.noneed.funscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hegemonica.game.Framerate;
import com.hegemonica.game.Core;

import java.util.Random;

public class FunScreen implements Screen{
    Core game;
    Texture img;
    BitmapFont testfont;
    Random rand = new Random();
    Framerate fps;
    int heightOfScreen;
    int widthOfScreen;
    SpriteBatch batch;
    private final int CHIPO_COUNT = 10;
    ChipoIcon[] chpo = new ChipoIcon[CHIPO_COUNT];

    String string_height="";
    String string_width="";


    public FunScreen(Core game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        heightOfScreen = Gdx.graphics.getHeight();
        widthOfScreen = Gdx.graphics.getWidth();
        fps = new Framerate();
        string_height ="Высота окна этого устройства: " + String.valueOf(heightOfScreen) + " пикселей";
        string_width = "Ширина окна этого устройства: " + String.valueOf(widthOfScreen) + " пикселей";

        testfont = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), Gdx.files.internal("fonts/test.png"),false);
        ChipoIcon.setMyTexture(new Texture("icons/chipo.png"));

        for (int i = 0;i < CHIPO_COUNT; i++){
            chpo[i] = new ChipoIcon(new Vector2(1+rand.nextInt(800), 1+(rand.nextInt(2000))), new Vector2(15.0f * (rand.nextFloat()-0.5f), 15.0f * (rand.nextFloat()-0.5f)));
        }
    }

    @Override
    public void render(float delta) {
        fps.update();
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        testfont.draw(batch, string_width,200,100);
        testfont.draw(batch, string_height,200,240);
        for (int i = 0; i < CHIPO_COUNT ; i++) {
            chpo[i].render(batch);
        }
        fps.render();
        batch.end();

    }
    public void update(){
        for (int i = 0; i < CHIPO_COUNT ; i++) {
            chpo[i].update();
        }


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
        batch.dispose();
    }




}
