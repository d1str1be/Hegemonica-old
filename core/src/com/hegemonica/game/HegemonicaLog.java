package com.hegemonica.game;

import com.badlogic.gdx.Gdx;

public class HegemonicaLog {
    public static class Tags {
        final static public String HEGEMONICA = "Hegemonica";
        final static public String INPUT = "Input";
        final static public String MAINMENU = "Main Menu";
        final static public String MAP = "Map";
        final static public String COUNTRY = "Country Logs";
        final static public String PROVINCE = "Province Logs";
    }

    public static void log(String logTag, String message){
        Gdx.app.log(logTag, message);
    }
}
