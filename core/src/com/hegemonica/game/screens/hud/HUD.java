package com.hegemonica.game.screens.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hegemonica.game.AudioManager;
import com.hegemonica.game.Core;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.HegeProgressBar;
import com.hegemonica.game.logic.Building;
import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Gemelch;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.units.WarUnit;
import com.hegemonica.game.screens.MainMenuScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HUD {
    Core game;
    public Gemelch gemelch;
    final float bWidth = Gdx.graphics.getWidth() / 5f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;
    
    public Map<Integer, Building> buttonBuildingMap;
    public Map<Integer, WarUnit> buttonUnitMap;
    
    
    public Stage stage;
    Skin DefaultUI;
    Skin GlassyUI;
    TextButton bCountry;
    TextButton bBack;
    public TextButton bTurn;
    
    Country turnCountry;
    Province selectedProvince;
    
    Label lTurnNumber;
    Label lCountryTurn;
    Label lNewTurn;
    
    ScrollPane scrollPane;
    Table scrollTable1;
    Window wProvinceInfo;
    Label lP1;
    Label lP2;
    Label lP3;
    Label lProvName;
    Label lProvCountry;
    Label lProvPopulation;
    Label lP4;
    Label lPopulationProgress;
    HegeProgressBar populationProgress;
    Label lP5;
    Label lProductionProgress;
    HegeProgressBar productionProgress;
    Label lP6;
    Label lScienceProgress;
    HegeProgressBar scienceProgress;
    
    
    Label lProjectName;
    Label lProductionCost;
    ArrayList<Label> lBuildingProjects;
    ArrayList<BuildButton> bBuildingBuild;
    ArrayList<Label> lUnitProjects;
    ArrayList<Label> lProdCost;
    ArrayList<BuildButton> bUnitBuild;
    
    Window wChooseProject;
    
    Window wCountryInfo;
    Label lC1;
    Label lCountryName;
    Label lC2;
    Label lCountryPopulation;
    Label lC3;
    
    Window wChooseTech;
    Label lT1;
    Label lT2;
    ArrayList<Label> lTechName;
    ArrayList<Label> lTechCost;
    
    Window wUnits;
    
    public HUD(Core game, Gemelch gemelch) {
        this.game = game;
        this.gemelch = gemelch;
        this.turnCountry = gemelch.turnCountry;
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
            wChooseProject.setVisible(false);
        }
    }
    
    public void setSelectedCountry(Country turnCountry) {
        this.turnCountry = turnCountry;
        lCountryTurn.setText("Turn: " + turnCountry.name);
    }
    
    public void show() {
        stage = new Stage(new FitViewport(Core.gameWidth, Core.gameHeight));
        DefaultUI = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        GlassyUI = new Skin(Gdx.files.internal("ui/glassy/skin/glassy-ui.json"));
        
        
        wProvinceInfo = new Window("Province Info", DefaultUI);
        wProvinceInfo.setPosition(Core.gameWidth * 0.05f, Core.gameHeight * 0.4f);
        wProvinceInfo.setSize(Core.gameWidth * 0.15f, Core.gameWidth * 0.125f);
        wProvinceInfo.setVisible(false);
        wProvinceInfo.setResizable(true);
        lP1 = new Label("Name:", GlassyUI);
        lP2 = new Label("Controlled by:", GlassyUI);
        lP3 = new Label("Population:", GlassyUI);
        lProvName = new Label("Null", DefaultUI);
        lProvCountry = new Label("Null", DefaultUI);
        lProvPopulation = new Label("Null", DefaultUI);
        lP4 = new Label("Food points", GlassyUI);
        lPopulationProgress = new Label("Null", DefaultUI);
        lP5 = new Label("Production points", GlassyUI);
        lProductionProgress = new Label("Null", DefaultUI);
        
        
        populationProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.FOOD);
        productionProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.PRODUCTION);
        
        
        wProvinceInfo.add(lP1);
        wProvinceInfo.add(lProvName);
        wProvinceInfo.row();
        wProvinceInfo.add(lP2);
        wProvinceInfo.add(lProvCountry);
        wProvinceInfo.row();
        wProvinceInfo.add(lP3);
        wProvinceInfo.add(lProvPopulation);
        wProvinceInfo.row();
        wProvinceInfo.add(lP4);
        wProvinceInfo.row();
        wProvinceInfo.add(populationProgress);
        wProvinceInfo.add(lPopulationProgress);
        wProvinceInfo.row();
        wProvinceInfo.add(lP5);
        wProvinceInfo.row();
        wProvinceInfo.add(productionProgress);
        wProvinceInfo.add(lProductionProgress);
        wProvinceInfo.row();
        wProvinceInfo.add(scrollPane);
        
        wCountryInfo = new Window("Country Info", DefaultUI);
        wCountryInfo.setMovable(true);
        wCountryInfo.setPosition(Core.gameWidth * 0.3f, Core.gameHeight * 0.6f);
        wCountryInfo.setSize(Core.gameWidth * 0.15f, Core.gameWidth * 0.125f);
        wCountryInfo.setVisible(false);
        wCountryInfo.align(Align.top);
        
        lC1 = new Label("Country Name", GlassyUI);
        lCountryName = new Label("Null", GlassyUI);
        lC2 = new Label("Population", GlassyUI);
        lCountryPopulation = new Label("Null", GlassyUI);
        lC3 = new Label("Science points", GlassyUI);
        lScienceProgress = new Label("Null", DefaultUI);
        scienceProgress = new HegeProgressBar(wCountryInfo.getWidth() * 0.15f, wCountryInfo.getWidth() * 0.02f, HegeProgressBar.ID.SCIENCE);
        
        wCountryInfo.add(lC1);
        wCountryInfo.add(lCountryName);
        wCountryInfo.row();
        wCountryInfo.add(lC2);
        wCountryInfo.add(lCountryPopulation);
        wCountryInfo.row();
        wCountryInfo.add(lC3);
        wCountryInfo.row();
        wCountryInfo.add(scienceProgress);
        wCountryInfo.add(lScienceProgress);
        
        
        wChooseProject = new Window("Choose project", DefaultUI);
        wChooseProject.setMovable(true);
        wChooseProject.setSize(Core.gameWidth * 0.15f, Core.gameWidth * 0.125f);
        wChooseProject.setPosition(wProvinceInfo.getX(), wProvinceInfo.getY() - wChooseProject.getHeight());
        wChooseProject.align(Align.top);
        
        lProductionCost = new Label("Cost", GlassyUI);
        lProjectName = new Label("Project Name", GlassyUI);
        
        
        Label l = new Label(" ", GlassyUI);
        wChooseProject.add(lProjectName).padRight(wChooseProject.getWidth() * 0.15f);
        wChooseProject.add(lProductionCost).padRight(wChooseProject.getWidth() * 0.15f);
        wChooseProject.add(l);
        wChooseProject.setVisible(false);
        lBuildingProjects = new ArrayList<>();
        bBuildingBuild = new ArrayList<>();
        lUnitProjects = new ArrayList<>();
        lProdCost = new ArrayList<>();
        bUnitBuild = new ArrayList<>();
        
        
        bTurn = new TextButton("Turn", DefaultUI);
        bTurn.setSize(bWidth, bHeight);
        bTurn.getLabel().setFontScale(1.5f);
        bTurn.setPosition(Core.gameWidth - bWidth, 0);
        bTurn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onTurn();
            }
        });
        
        bBack = new TextButton("Back", DefaultUI);
        bBack.getLabel().setFontScale(1.5f);
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
        bCountry.setSize(bWidth * 0.4f, bHeight * 0.6f);
        bCountry.getLabel().setFontScale(1.5f);
        bCountry.setPosition(Core.gameWidth - bBack.getWidth() - bCountry.getWidth(), Core.gameHeight - bCountry.getHeight());
        bCountry.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setCountryInfo();
                wCountryInfo.setVisible(true);
            }
        });
        
        lTurnNumber = new Label("Turn " + Gemelch.turnNumber, GlassyUI, "big");
        lTurnNumber.setPosition(Core.gameWidth * 0.925f - lTurnNumber.getWidth(), bTurn.getHeight());
        
        lNewTurn = new Label("New Turn!", GlassyUI, "big");
        lNewTurn.setPosition(Core.gameWidth / 2f - lNewTurn.getWidth(), Core.gameHeight * 0.05f);
        lNewTurn.setVisible(false);
        
        lCountryTurn = new Label("Turn: " + turnCountry.name, GlassyUI, "big");
        lCountryTurn.setPosition(Core.gameWidth * 0.925f - lCountryTurn.getWidth(), lTurnNumber.getY() + lTurnNumber.getHeight());
        
        
        stage.addActor(lTurnNumber);
        stage.addActor(lNewTurn);
        stage.addActor(lCountryTurn);
        stage.addActor(wProvinceInfo);
        stage.addActor(wCountryInfo);
        stage.addActor(wChooseProject);
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
        lNewTurn.setVisible(false);
        gemelch.onTurn();
        setSelectedCountry(gemelch.turnCountry);
        if (selectedProvince != null)
            setProvinceInfo();
    }
    
    public void newTurn() {
        lTurnNumber.setText("Turn " + Gemelch.turnNumber);
        lNewTurn.setVisible(true);
        HegeLog.log("HegeLogic", "Button Turn pressed. Now it`s turn " + Gemelch.turnNumber);
    }
    
    public void setProvinceInfo() {
        
        lProvName.setText(selectedProvince.name);
        lProvCountry.setText(selectedProvince.owner.name);
        lProvPopulation.setText(selectedProvince.population);
        
        lPopulationProgress.setText(selectedProvince.foodPoints + " / " + selectedProvince.neededFoodPoints);
        lProductionProgress.setText(selectedProvince.productionPoints + " / " + selectedProvince.neededProductionPoints);
        
        
        populationProgress.setRange(0, (float) selectedProvince.neededFoodPoints);
        populationProgress.setValue(selectedProvince.foodPoints);
        
        productionProgress.setRange(0, (float) selectedProvince.neededProductionPoints);
        productionProgress.setValue((float) selectedProvince.productionPoints);
        
        
        if (selectedProvince.productionPoints >= selectedProvince.neededProductionPoints) {
            setBuildingsInfo();
        }
    }
    
    public void setCountryInfo() {
        
        lCountryName.setText(selectedProvince.owner.name);
        lCountryPopulation.setText(selectedProvince.owner.population);
        lScienceProgress = new Label("Null", DefaultUI);
        lScienceProgress.setText(selectedProvince.owner.sciencePoints + " / " + selectedProvince.owner.neededSciencePoints);
        scienceProgress.setRange(0, selectedProvince.owner.neededSciencePoints);
        scienceProgress.setValue((float) selectedProvince.owner.sciencePoints);
    }
    
    public void setBuildingsInfo() {
        wChooseProject.clear();
        wChooseProject.add(lProjectName);
        wChooseProject.add(lProductionCost);
        Label l = new Label(" ", GlassyUI);
        wChooseProject.add(l);
        buttonBuildingMap = new HashMap<Integer, Building>();
        //обработка доступных построек
        for (int i = 0; i < selectedProvince.possibleBuildings.size(); i++) {
            Label tmpLabel = new Label(selectedProvince.possibleBuildings.get(i).name, DefaultUI);
            Label tmpLabel1 = new Label(Integer.toString(selectedProvince.possibleBuildings.get(i).productionCost), DefaultUI);
            lBuildingProjects.add(tmpLabel);
            lProdCost.add(tmpLabel1);
            wChooseProject.row();
            wChooseProject.add(lBuildingProjects.get(i));
            wChooseProject.add(tmpLabel1);
            final BuildButton tmpButton = new BuildButton(i, "Make building", DefaultUI);
            HegeLog.log("Code", "Added button with id: " + i + " and name Make building");
            buttonBuildingMap.put(tmpButton.id, selectedProvince.possibleBuildings.get(i));
            tmpButton.setRound(true);
            tmpButton.setSize(wChooseProject.getWidth() * 0.15f, wChooseProject.getHeight() * 0.1f);
            tmpButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    HegeLog.log("Input", "BuildBtn ID: " + tmpButton.getId());
                    HegeLog.log("Province choosing project", "Chose " + buttonBuildingMap.get(tmpButton.id).toString());
                    selectedProvince.chooseBuilding(buttonBuildingMap.get(tmpButton.id));
                    HegeLog.log("Province Project", "Chose building " + buttonBuildingMap.get(tmpButton.id).name);
                    setProvinceInfo();
//                    isClicked = true;
//                    wChooseProject.setVisible(false);
                }
            });
            bBuildingBuild.add(tmpButton);
            wChooseProject.add(bBuildingBuild.get(i));
        }
        //обработка доступных юнитов
        buttonUnitMap = new HashMap<Integer, WarUnit>();
        for (int i = 0; i < selectedProvince.possibleUnits.size(); i++) {
            Label tmpLabel = new Label(selectedProvince.possibleUnits.get(i).name, DefaultUI);
            Label tmpLabel1 = new Label(Integer.toString(selectedProvince.possibleUnits.get(i).productionCost), DefaultUI);
            lUnitProjects.add(tmpLabel);
            lProdCost.add(tmpLabel1);
            wChooseProject.row();
            wChooseProject.add(lUnitProjects.get(i));
            wChooseProject.add(tmpLabel1);
            final BuildButton tmpButton = new BuildButton(i + selectedProvince.possibleBuildings.size(), "Make unit", DefaultUI);
            HegeLog.log("Code", "Added button with id: " + (i + selectedProvince.possibleBuildings.size()) + " and name Make unit");
            buttonUnitMap.put(tmpButton.id, selectedProvince.possibleUnits.get(i));
            tmpButton.setRound(true);
            tmpButton.setSize(wChooseProject.getWidth() * 0.15f, wChooseProject.getHeight() * 0.1f);
            tmpButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
        
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    HegeLog.log("Input", "BuildBtn ID: " + tmpButton.getId());
                    HegeLog.log("Province choosing project", "Chose " + buttonUnitMap.get(tmpButton.id).toString());
                    selectedProvince.chooseUnit(buttonUnitMap.get(tmpButton.id));
                    HegeLog.log("Province Project", "Chose unit " + buttonUnitMap.get(tmpButton.id).name);
                    setProvinceInfo();
//                    isClicked = true;
//                    wChooseProject.setVisible(false);
                }
            });
            bBuildingBuild.add(tmpButton);
            wChooseProject.add(bBuildingBuild.get(i + selectedProvince.possibleBuildings.size()));
        }
        wChooseProject.setVisible(true);
    }
    
    public void setDebug(boolean debug) {
        if (debug) {
            stage.setDebugAll(true);
        }
    }
}
