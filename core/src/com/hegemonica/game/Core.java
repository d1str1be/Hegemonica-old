package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.hegemonica.game.screens.mainmenu.MainMenuScreen;


public class Core extends Game {
	public static final float VERSION  = 0.20f;
	public float musicVolume = 1f;
	public float soundVolume = 1f;

	public Music music;

	public static class Tags {
		final static public String DEFAULT = "Default";
		public static final String HEGEMONICA = "Hegemonica";
		final static public String ENGINE = "Engine Logs";
		final static public String COUNTRY = "Country Logs";
		final static public String PROVINCE = "Province Logs";
	}
	@Override
	public void create () {
		//музыка тест
		music = Gdx.audio.newMusic(Gdx.files.internal("music/mainmenu.mp3"));
		music.setVolume(this.musicVolume);
		music.setLooping(true);
		music.play();


		Gdx.files.internal("english.json"); //настройка языка. на первое время напрямую при запуске пропишем - Богдан
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
	public void dispose () { super.dispose(); music.dispose();
	}

	public void setMusicVolume(float value){
		music.setVolume(value);
	}
}
