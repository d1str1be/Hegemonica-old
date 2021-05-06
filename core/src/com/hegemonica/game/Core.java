package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hegemonica.game.localization.LocalizationManager;
import com.hegemonica.game.screens.MainMenuScreen;


public class Core extends Game {
    private static final float VERSION = 0.40f;
    public static float gameWidth;
    public static float gameHeight;
    public static float uiFactor;

    /**
     * Для релизных версий ставить false. DevMode нужен для отладки, тестирования и для пометки особого статуса в Discord и, вероятно, на других площадках вроде Steam.
     */
    public static final boolean DEV_MODE = true;
    /**
     * Логическая переменная, отвечающая за доступ к интеграции Discord. Значение задаётся в классе лаунчера платформы через конструктор класса.
     */
    public final boolean enableDiscord;
    public static final boolean IS_SERVER = false;

    public LocalizationManager loc;
    public AudioManager audio;
    public DiscordManager discord;
    public NetConnManager internet;

    public Core(boolean enableDiscord) {
        this.enableDiscord = enableDiscord;
    }

    @Override
    public void create() {
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        uiFactor = gameWidth * 0.01625f;
        HegeLog.log(HegeLog.HEGEMONICA, "Width of app: " + gameWidth +
                "\nHeight of app: " + gameHeight);
        Gdx.input.setCatchKey(Input.Keys.BACK, true); // перехват сист.кнопки "назад" на андроиде
        audio = new AudioManager();
        loc = new LocalizationManager();
        internet = new NetConnManager();

        if (enableDiscord)
            discord = new DiscordManager(DEV_MODE);
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        discord.stopRPC();
        super.dispose();
    }
}
