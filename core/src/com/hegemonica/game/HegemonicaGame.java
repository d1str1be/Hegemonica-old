package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hegemonica.game.screens.MainMenuScreen;

public class HegemonicaGame extends Game {

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
	public void update(){
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