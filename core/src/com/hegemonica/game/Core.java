package com.hegemonica.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hegemonica.game.localization.LocalizationManager;
import com.hegemonica.game.screens.mainmenu.MainMenuScreen;

import static com.hegemonica.game.HegemonicaLog.Tags.HEGEMONICA;


public class Core extends Game {
    private static final float VERSION = 0.40f;

    /**
     * Для релизных версий ставить false. DevMode нужен для отладки, тестирования и для пометки особого статуса в Discord и, вероятно, на других площадках вроде Steam
     */
    public final boolean DEV_MODE = true;
    public final boolean enableDiscord;
    public LocalizationManager loc;
    public AudioManager audio;

    public Discord discord;

    public Core(boolean enableDiscord) {
        this.enableDiscord = enableDiscord;
    }

    @Override
    public void create() {
        HegemonicaLog.log(HEGEMONICA, "Width of app: " + Gdx.graphics.getWidth() + "\nHeight of app: " + Gdx.graphics.getHeight());
        Gdx.input.setCatchKey(Input.Keys.BACK, true); // перехват сист.кнопки "назад" на андроиде
        audio = new AudioManager();
        loc = new LocalizationManager();
        if(enableDiscord)
            discord = new Discord(DEV_MODE);

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
