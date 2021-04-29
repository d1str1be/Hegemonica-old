package com.hegemonica.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private float musicVolume = 0f;
    private float soundVolume = 1f;

    private final Music testMusic;
    private final Sound uiClickSound;
    private final Sound ChillSound;


    public static class Sounds {
        final static public String UI_CLICK = "1";
        final static public String Chill = "2";
    }


    public AudioManager(){
        //музыка тест
        testMusic = Gdx.audio.newMusic(Gdx.files.internal("music/mainmenu.mp3"));
        testMusic.setVolume(this.musicVolume);
        testMusic.setLooping(true);
        testMusic.play();

        //звук тест
        uiClickSound = Gdx.audio.newSound(Gdx.files.internal("sound/confirm.wav"));
        uiClickSound.setVolume(1, this.soundVolume);

        ChillSound = Gdx.audio.newSound(Gdx.files.internal("sound/chill.wav"));
        ChillSound.setVolume(1, 100);


    }

    public void setMusicVolume(float value){
        musicVolume = value;
        testMusic.setVolume(value);
    }
    public void setSoundVolume(float value){
        soundVolume = value;
        uiClickSound.setVolume(1,value);
    }

    public void dispose(){ testMusic.dispose(); uiClickSound.dispose(); }

    public void playSound(String soundId){
        switch (soundId){
            case Sounds.UI_CLICK:
                uiClickSound.play(soundVolume);
            case Sounds.Chill:
                ChillSound.play(100);
        }
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }
}
