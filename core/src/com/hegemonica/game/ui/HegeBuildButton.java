package com.hegemonica.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class HegeBuildButton extends TextButton {
    public final int id;
    
    public HegeBuildButton(final int id, String text, Skin skin) {
        super(text, skin);
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}