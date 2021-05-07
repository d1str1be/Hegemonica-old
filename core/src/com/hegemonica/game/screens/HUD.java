package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.logic.Province;


public class HUD {
    Core game;
    PlayScreenMap map;
    final float bWidth = Gdx.graphics.getWidth() / 12f * 3f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;

    public int turnNumber;

    Stage stage;
    Skin DefaultUI;
    Skin GlassyUI;
    TextButton bBack;
    public TextButton bTurn;

    Province selectedProvince;
    Label lselectedProvince;

    Label lTurnNumber;

    Window wGeneralInfo;
    Label l1;
    Label l2;
    Label l3;
    Label lProvName;
    Label lProvCountry;
    Label lProvPopulation;

    Window wBuildings;
    Window wUnits;


    public HUD(Core game, PlayScreenMap map) {
        this.game = game;
        this.map = map;
        this.show();
        //setDebug(Core.DEV_MODE);
    }

    public void setSelectedProvince(Province selectedProvince) {
        if (selectedProvince != null) {
            this.selectedProvince = selectedProvince;
            setGeneralInfo();
            wGeneralInfo.setVisible(true);
            HegeLog.log(HegeLog.HUD, "Selected " + selectedProvince.name);

        } else {
            HegeLog.log(HegeLog.HUD, "Selected null province");
            wGeneralInfo.setVisible(false);
        }
    }

    public void show() {
        stage = new Stage(new FitViewport(Core.gameWidth, Core.gameHeight));
        turnNumber = 1;
        DefaultUI = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));

        lTurnNumber = new Label("Turn " + turnNumber, DefaultUI);

        wGeneralInfo = new Window("General Information", DefaultUI);
        wGeneralInfo.setPosition(Core.gameWidth * 0.05f, Core.gameHeight * 0.7f, Align.bottom);
        wGeneralInfo.setWidth(Core.gameWidth * 0.25f);
        wGeneralInfo.setVisible(false);
        l1 = new Label("Name:", GlassyUI);
        l2 = new Label("Controlled by:", GlassyUI);
        l3 = new Label("Population:", GlassyUI);
        lProvName = new Label("Null", DefaultUI);
        lProvCountry = new Label("Null", DefaultUI);
        lProvPopulation = new Label("Null", DefaultUI);

        wGeneralInfo.add(l1);
        wGeneralInfo.add(lProvName);
        wGeneralInfo.row();
        wGeneralInfo.add(l2);
        wGeneralInfo.add(lProvCountry);
        wGeneralInfo.row();
        wGeneralInfo.add(l3);
        wGeneralInfo.add(lProvPopulation);

        bTurn = new TextButton("Turn", GlassyUI);
        bTurn.setSize(bWidth, bHeight);
        bTurn.setPosition(Core.gameWidth - bWidth, 0);
        bTurn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onTurn();
                HegeLog.log("HegeLogic", "Button Turn pressed. Now it`s turn " + turnNumber);
            }
        });

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
        stage.addActor(wGeneralInfo);
        stage.addActor(bTurn);
        stage.addActor(bBack);


    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public void onTurn() {
        turnNumber++;
        lTurnNumber.setText("Turn " + turnNumber);
        map.onTurn(turnNumber);
    }

    public void setGeneralInfo() {
        lProvName.setText(selectedProvince.name);
        lProvCountry.setText(selectedProvince.owner.name);
        lProvPopulation.setText(selectedProvince.population);

    }

    public void setDebug(boolean debug) {
        if (debug) {
            stage.setDebugAll(true);
        }
    }
}
