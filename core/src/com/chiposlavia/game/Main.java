package com.chiposlavia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Main extends ApplicationAdapter {
	Chipa chipo = new Chipa(120.0f,130.0f,5.0f,5.0f);
	SpriteBatch batch;
	Texture img;
	BitmapFont testfont;

	int heightOfScreen;
	int widthOfScreen;

	String string_height="";
	String string_width="";

	@Override
	public void create () {
		int heightOfScreen = Gdx.graphics.getHeight();
		int widthOfScreen = Gdx.graphics.getWidth();

		string_height ="Высота окна этого устройства: " + String.valueOf(heightOfScreen) + " пикселей";
		string_width = "Ширина окна этого устройства: " + String.valueOf(widthOfScreen) + " пикселей";

		testfont = new BitmapFont(Gdx.files.internal("testfont.fnt"), Gdx.files.internal("testfont.png"),false);

		batch = new SpriteBatch();
		Chipa.setMyTexture(new Texture("chipo.png"));
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		testfont.draw(batch, "test",Gdx.app.getGraphics().getWidth()-200,Gdx.app.getGraphics().getHeight()-100);
		testfont.draw(batch, string_width,200,100);
		testfont.draw(batch, string_height,200,240);
		chipo.render(batch);

		batch.end();
	}

	public void update(){
		chipo.update();
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
	}
}
