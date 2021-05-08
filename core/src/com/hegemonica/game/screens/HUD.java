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
import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Gemelch;
import com.hegemonica.game.logic.Province;


public class HUD {
    Core game;
    public Gemelch gemelch;
    final float bWidth = Gdx.graphics.getWidth() / 5f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;
    

    Stage stage;
    Skin DefaultUI;
    Skin GlassyUI;
    TextButton bCountry;
    TextButton bBack;
    public TextButton bTurn;

    Country selectedCountry;
    Province selectedProvince;

    Label lTurnNumber;

    Window wProvinceInfo;
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

    Window wCountryInfo;
    Label lCountryName;

    Window wBuildings;
    Window wUnits;

    public HUD(Core game, Gemelch gemelch) {
        this.game = game;
        this.gemelch = gemelch;
        this.show();
        //setDebug(Core.DEV_MODE);
    }

    public void setSelectedProvince(Province selectedProvince) {
        if (selectedProvince != null) {
            this.selectedProvince = selectedProvince;
            setProvinceInfo();
            game.audio.playSound(AudioManager.Sounds.UI_CLICK);
            wProvinceInfo.setVisible(true);
            HegeLog.log(HegeLog.HUD, "Selected " + selectedProvince.name);

        } else {
            HegeLog.log(HegeLog.HUD, "Selected null province");
            wProvinceInfo.setVisible(false);
        }
    }
    public void setSelectedCountry(Country selectedCountry){
        this.selectedCountry = selectedCountry;
    }

    public void show() {
        stage = new Stage(new FitViewport(Core.gameWidth, Core.gameHeight));
        DefaultUI = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));

        

        wProvinceInfo = new Window("Province Info", DefaultUI);
        wProvinceInfo.setPosition(Core.gameWidth * 0.05f, Core.gameHeight * 0.4f);
        wProvinceInfo.setSize(Core.gameWidth * 0.15f, Core.gameWidth * 0.125f);
        wProvinceInfo.setScale(2f);
        wProvinceInfo.setVisible(false);
        l1 = new Label("Name:", GlassyUI);
        l2 = new Label("Controlled by:", GlassyUI);
        l3 = new Label("Population:", GlassyUI);
        lProvName = new Label("Null", DefaultUI);
        lProvCountry = new Label("Null", DefaultUI);
        lProvPopulation = new Label("Null", DefaultUI);
        l4 = new Label("Food points", GlassyUI);
        lPopulationProgress = new Label("Null", DefaultUI);
        l5 = new Label("Production points", GlassyUI);
        lProductionProgress = new Label("Null", DefaultUI);
        l6 = new Label("Science points", GlassyUI);
        lScienceProgress = new Label("Null", DefaultUI);

        populationProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.FOOD);
        productionProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.PRODUCTION);
        scienceProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.SCIENCE);

        wProvinceInfo.add(l1);
        wProvinceInfo.add(lProvName);
        wProvinceInfo.row();
        wProvinceInfo.add(l2);
        wProvinceInfo.add(lProvCountry);
        wProvinceInfo.row();
        wProvinceInfo.add(l3);
        wProvinceInfo.add(lProvPopulation);
        wProvinceInfo.row();
        wProvinceInfo.add(l4);
        wProvinceInfo.row();
        wProvinceInfo.add(populationProgress);
        wProvinceInfo.add(lPopulationProgress);
        wProvinceInfo.row();
        wProvinceInfo.add(l5);
        wProvinceInfo.row();
        wProvinceInfo.add(productionProgress);
        wProvinceInfo.add(lProductionProgress);
        wProvinceInfo.row();
        wProvinceInfo.add(l6);
        wProvinceInfo.row();
        wProvinceInfo.add(scienceProgress);
        wProvinceInfo.add(lScienceProgress);

        wCountryInfo = new Window("Country Info", DefaultUI);
        wCountryInfo.setPosition(Core.gameWidth * 0.05f, Core.gameHeight * 0.4f);
        wCountryInfo.setSize(Core.gameWidth * 0.15f, Core.gameWidth * 0.125f);
        wCountryInfo.setScale(2f);
        wCountryInfo.setVisible(false);

        bTurn = new TextButton("Turn", DefaultUI);
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
                HegeLog.log("HegeLogic", "Button Turn pressed. Now it`s turn " + Gemelch.turnNumber);
            }
        });

        bBack = new TextButton("Back", DefaultUI);
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
        bCountry = new TextButton("Country Info", DefaultUI);
        bCountry.setSize(bWidth*0.4f, bHeight*0.6f);
        bCountry.setPosition(Core.gameWidth-bBack.getWidth()-bCountry.getWidth(), Core.gameHeight-bCountry.getHeight());
        bCountry.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                wCountryInfo.setVisible(true);
            }
        });
    
        lTurnNumber = new Label("Turn " + Gemelch.turnNumber, DefaultUI);
        lTurnNumber.setDebug(true);
        lTurnNumber.setScale(1.5f);
        lTurnNumber.setFontScale(1.5f);
        lTurnNumber.setPosition(Core.gameWidth-lTurnNumber.getWidth()*2f, bTurn.getHeight());
        stage.addActor(lTurnNumber);

        stage.addActor(wProvinceInfo);
        stage.addActor(bTurn);
        stage.addActor(bBack);
        stage.addActor(bCountry);


    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public void onTurn() {
        lTurnNumber.setText("Turn " + Gemelch.turnNumber);
        gemelch.onTurn();
        if (selectedProvince != null)
            setProvinceInfo();
    }

    public void setProvinceInfo() {
        lProvName.setText(selectedProvince.name);
        lProvCountry.setText(selectedProvince.owner.name);
        lProvPopulation.setText(selectedProvince.population);

        lPopulationProgress.setText(selectedProvince.foodPoints + " / " + selectedProvince.neededFoodPoints);
        lProductionProgress.setText(selectedProvince.productionPoints + " / " + selectedProvince.neededProductionPoints);
        lScienceProgress.setText(selectedProvince.owner.sciencePoints + " / " + selectedProvince.owner.neededSciencePoints);

        populationProgress.setRange(0, (float) selectedProvince.neededFoodPoints);
        populationProgress.setValue(selectedProvince.foodPoints);

        productionProgress.setRange(0, (float) selectedProvince.neededProductionPoints);
        productionProgress.setValue((float) selectedProvince.productionPoints);

        scienceProgress.setRange(0, selectedProvince.owner.neededSciencePoints);
        scienceProgress.setValue((float) selectedProvince.owner.sciencePoints);
    }

    public void setCountryInfo(){

    }

    public void setDebug(boolean debug) {
        if (debug) {
            stage.setDebugAll(true);
        }
    }
}
