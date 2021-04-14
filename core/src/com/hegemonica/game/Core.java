package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.hegemonica.game.devtools.MapEditorScreen;
import com.hegemonica.game.screens.playscreen.TestVersionScreen;


public class Core extends Game {

	public static class Tags {
		final static public String DEFAULT = "Default";
		public static final String HEGEMONICA = "Hegemonica";
		final static public String ENGINE = "Engine Logs";
		final static public String COUNTRY = "Country Logs";
		final static public String PROVINCE = "Province Logs";
	}
	@Override
	public void create () {
		Gdx.files.internal("english.json"); //настройка языка. на первое время напрямую при запуске пропишем - Богдан
		this.setScreen(new TestVersionScreen(this));
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
	public void dispose () { super.dispose();}
}
