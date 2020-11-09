package com.chiposlavia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont testfont;
	float x = 0.0f;//координаты в виде переменных оставляю на время - потом заменим на вектор
	float y = 0.0f;
	String string_height ="Высота окна этого устройства: " + String.valueOf(Gdx.graphics.getHeight()) + " пикселей";
	String string_width = "Ширина окна этого устройства: " + String.valueOf(Gdx.graphics.getWidth()) + " пикселей";
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("chipo.png");
		testfont = new BitmapFont(Gdx.files.internal("testfont.fnt"), Gdx.files.internal("testfont.fnt"),false);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		testfont.draw(batch, string_height,60,50);
		testfont.draw(batch, string_width,50,50);
		batch.draw(img, x, y);
		x++;
		y++;

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
