package com.chiposlavia.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chiposlavia.game.screens.GameScreen;

public class ChiposlaviaGame extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
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
