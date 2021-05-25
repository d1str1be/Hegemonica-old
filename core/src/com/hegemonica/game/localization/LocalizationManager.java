package com.hegemonica.game.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.hegemonica.game.HegeLog;

/**
 * Во время разработки проекта был замененен на использование обычных String на английском языке для ускорения процесса разработки.
 */
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
        HegeLog.log(HegeLog.HEGEMONICA, "Changed language to: " + langJSONFilePath);
    }

    public String getString(LocalizationKeys.Keys key) {
        return loc.getValue(key);
    }

}
