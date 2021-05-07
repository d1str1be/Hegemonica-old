package com.hegemonica.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hegemonica.game.Core;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Hegemonica";
        config.vSyncEnabled = false;
        config.width = 1000;
        config.height = 1000;
        config.addIcon("icons/hegemonicalogo32x32.png", Files.FileType.Internal);
        new LwjglApplication(new Core(true), config);
    }
}
