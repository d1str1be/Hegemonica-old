package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hegemonica.game.AudioManager;
import com.hegemonica.game.Core;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.HegeProgressBar;
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
    Label l4;
    Label lPopulationProgress;
    HegeProgressBar populationProgress;
    Label l5;
    Label lProductionProgress;
    HegeProgressBar productionProgress;
    Label l6;
    Label lScienceProgress;
    HegeProgressBar scienceProgress;

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
            game.audio.playSound(AudioManager.Sounds.UI_CLICK);
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
        wGeneralInfo.setPosition(Core.gameWidth * 0.05f, Core.gameHeight * 0.4f);
        wGeneralInfo.setSize(Core.gameWidth * 0.15f, Core.gameWidth * 0.125f);
        wGeneralInfo.setScale(2f);
        wGeneralInfo.setVisible(false);
        l1 = new Label("Name:", GlassyUI);
        l2 = new Label("Controlled by:", GlassyUI);
        l3 = new Label("Population:", GlassyUI);
        lProvName = new Label("Null", DefaultUI);
        lProvCountry = new Label("Null", DefaultUI);
        lProvPopulation = new Label("Null", DefaultUI);
        l4 =  new Label("Food points", GlassyUI);
        lPopulationProgress = new Label("Null", DefaultUI);
        l5 = new Label("Production points", GlassyUI);
        lProductionProgress = new Label("Null", DefaultUI);
        l6 = new Label("Science points", GlassyUI);
        lScienceProgress = new Label("Null", DefaultUI);

        populationProgress = new HegeProgressBar(wGeneralInfo.getWidth() * 0.15f, wGeneralInfo.getWidth() * 0.02f, HegeProgressBar.ID.FOOD);
        productionProgress = new HegeProgressBar(wGeneralInfo.getWidth() * 0.15f, wGeneralInfo.getWidth() * 0.02f, HegeProgressBar.ID.PRODUCTION);
        scienceProgress = new HegeProgressBar(wGeneralInfo.getWidth() * 0.15f, wGeneralInfo.getWidth() * 0.02f, HegeProgressBar.ID.SCIENCE);

        wGeneralInfo.add(l1);
        wGeneralInfo.add(lProvName);
        wGeneralInfo.row();
        wGeneralInfo.add(l2);
        wGeneralInfo.add(lProvCountry);
        wGeneralInfo.row();
        wGeneralInfo.add(l3);
        wGeneralInfo.add(lProvPopulation);
        wGeneralInfo.row();
        wGeneralInfo.add(l4);
        wGeneralInfo.row();
        wGeneralInfo.add(populationProgress);
        wGeneralInfo.add(lPopulationProgress);
        wGeneralInfo.row();
        wGeneralInfo.add(l5);
        wGeneralInfo.row();
        wGeneralInfo.add(populationProgress);
        wGeneralInfo.add(lProductionProgress);
        wGeneralInfo.row();
        wGeneralInfo.add(l6);
        wGeneralInfo.row();
        wGeneralInfo.add(scienceProgress);
        wGeneralInfo.add(lScienceProgress);


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
        if (selectedProvince != null)
            setGeneralInfo();
    }

    public void setGeneralInfo() {
        lProvName.setText(selectedProvince.name);
        lProvCountry.setText(selectedProvince.owner.name);
        lProvPopulation.setText(selectedProvince.population);
        lPopulationProgress.setText(selectedProvince.foodPoints + " / " + selectedProvince.neededFoodPoints);
        lProductionProgress.setText(selectedProvince.productionPoints + " / " + selectedProvince.neededProductionPoints);
        lScienceProgress.setText(selectedProvince.owner.sciencePoints + " / " + selectedProvince.owner.neededSciencePoints);

        populationProgress.setRange(0, selectedProvince.neededFoodPoints);
        populationProgress.setValue(selectedProvince.foodPoints);
        populationProgress.updateVisualValue();

        productionProgress.setRange(0, selectedProvince.neededProductionPoints);
        productionProgress.setValue(selectedProvince.productionPoints);
        productionProgress.updateVisualValue();

        scienceProgress.setRange(0, selectedProvince.owner.neededSciencePoints);
        scienceProgress.setValue(selectedProvince.owner.sciencePoints);
        scienceProgress.updateVisualValue();
    }

    public void setDebug(boolean debug) {
        if (debug) {
            stage.setDebugAll(true);
        }
    }
}
