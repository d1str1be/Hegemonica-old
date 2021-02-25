package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hegemonica.game.screens.mainmenu.MainMenuScreen;
import android
public class Core extends com.badlogic.gdx.Game {
	public static final String TAG = "Hegemonica";
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
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
	}
}
