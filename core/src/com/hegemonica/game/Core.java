package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hegemonica.game.localization.LocalizationManager;
import com.hegemonica.game.screens.mainmenu.MainMenuScreen;

import static com.hegemonica.game.Log.Tags.HEGEMONICA;


public class Core extends Game {
	private static final float VERSION  = 0.30f;

	public LocalizationManager loc;
	public AudioManager audio;

	@Override
	public void create () {
		Gdx.app.log(HEGEMONICA,"Width of app: " + Gdx.graphics.getWidth() + "\nHeight of app: " + Gdx.graphics.getHeight());
		Gdx.input.setCatchKey(Input.Keys.BACK,true); // перехват сист.кнопки "назад" на андроиде
		audio = new AudioManager();
		loc = new LocalizationManager();
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
	public void dispose () { super.dispose(); ;
	}
}
