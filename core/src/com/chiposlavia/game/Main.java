package com.chiposlavia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.lang.Math;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont testfont;
	Random rand = new Random();
	FrameRate fps;
	int heightOfScreen;
	int widthOfScreen;
	private final int CHIPO_COUNT = 20;
	Chipa[] chpo = new Chipa[CHIPO_COUNT];

	String string_height="";
	String string_width="";

	@Override
	public void create () {
		int heightOfScreen = Gdx.graphics.getHeight();
		int widthOfScreen = Gdx.graphics.getWidth();
		fps = new FrameRate();
		string_height ="Высота окна этого устройства: " + String.valueOf(heightOfScreen) + " пикселей";
		string_width = "Ширина окна этого устройства: " + String.valueOf(widthOfScreen) + " пикселей";
		testfont = new BitmapFont(Gdx.files.internal("fonts/testfont.fnt"), Gdx.files.internal("fonts/testfont.png"),false);
		for (int i = 0;i < CHIPO_COUNT; i++){
			chpo[i] = new Chipa(new Vector2(1+rand.nextInt(800), 1+(rand.nextInt(2000))), new Vector2(15.0f * (rand.nextFloat()-0.5f), 15.0f * (rand.nextFloat()-0.5f)));
		}
		batch = new SpriteBatch();
		Chipa.setMyTexture(new Texture("icons/chipo.png"));
	}

	@Override
	public void render () {
		update();
		fps.update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
	public void pause(){
		super.pause();
	}
	@Override
	public void resume(){
		super.resume();
	}
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		testfont.dispose();
		fps.dispose();
	}
}
