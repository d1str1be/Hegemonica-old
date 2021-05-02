package com.hegemonica.game.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.hegemonica.game.HegemonicaLog;

public class LocalizationManager {
    public Json json;
    private LocalizationKeys loc;

    public final String russianLocFile = "russian.json";
    public final String englishLocFile = "english.json";

    public LocalizationManager() {
        json = new Json();
        loc = json.fromJson(LocalizationKeys.class, Gdx.files.internal("english.json"));
    }

    public void updateLanguage(String langJSONFilePath) {
        loc = json.fromJson(LocalizationKeys.class, Gdx.files.internal(langJSONFilePath));
        Gdx.app.log(HegemonicaLog.Tags.HEGEMONICA, "Changed language to: " + langJSONFilePath);
    }

    public String getString(LocalizationKeys.Keys key) {
        return loc.getValue(key);
    }

}
