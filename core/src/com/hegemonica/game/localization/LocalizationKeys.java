package com.hegemonica.game.localization;

import com.badlogic.gdx.utils.ObjectMap;

public class LocalizationKeys {
    public ObjectMap<String, String> map;

    public LocalizationKeys() {
    }

    public enum Keys {
        Hegemonica, Language, English, Russian,
        Play, Settings, Back, Exit, Sound_Volume, Music_Volume,

        Turn,

        Svadia, Tay, Singenada,

        Levian, Valinia, Haadra, Eliria, Gasiliya, Odar,

        Mountain, Swarm, Steppe, Desert, Jungle, Hills, Plain,

        In_Main_Menu, Playing,
    }

    public String getValue(Keys key) {
        return map.get(key.name());
    }
}
