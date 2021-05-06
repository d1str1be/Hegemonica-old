package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hegemonica.game.Core;

public class HUD {
    Core game;
    final float bWidth = Gdx.graphics.getWidth() / 12f * 3f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;

    Stage stage;
    Skin DefaultUI;
    Skin GlassyUI;
    TextButton bBack;

    public HUD(final Core game) {
        this.game = game;
        this.show();
    }

    public void show() {
        stage = new Stage(new FitViewport(Core.gameWidth, Core.gameHeight));
        DefaultUI = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));
        bBack = new TextButton("Back", GlassyUI);
        bBack.setSize(bWidth, bHeight);
        bBack.setPosition(Core.gameWidth - bWidth, Core.gameHeight - bHeight);
        bBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(bBack);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
