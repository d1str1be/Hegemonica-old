package com.hegemonica.game.screens.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BuildButton extends TextButton {
    public final int id;
    
    public BuildButton(final int id, String text, Skin skin) {
        super(text, skin);
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
