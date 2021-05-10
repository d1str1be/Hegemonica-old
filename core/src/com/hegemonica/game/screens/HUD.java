package com.hegemonica.game.screens;

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
import com.hegemonica.game.logic.Building;
import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Gemelch;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.units.WarUnit;
import com.hegemonica.game.ui.HegeBuildButton;
import com.hegemonica.game.ui.HegeProgressBar;
import com.hegemonica.game.ui.HegeTechButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HUD {
    Core game;
    public Gemelch gemelch;
    public boolean isReadyToMove;
    final float bWidth = Gdx.graphics.getWidth() / 5f;
    final float bHeight = Gdx.graphics.getHeight() / 8f;
    
    public Map<Integer, Building> buttonBuildingMap;
    public Map<Integer, WarUnit> buttonUnitMap;
    
    
    public Stage stage;
    public Skin DefaultUI;
    public Skin GlassyUI;
    TextButton bCountry;
    TextButton bBack;
    public TextButton bTurn;
    
    Country turnCountry;
    Province startMovingProv;
    Province selectedProvince;
    
    Label lTurnNumber;
    Label lCountryTurn;
    Label lNewTurn;
    Label lStartHint;
    Label lMoveUnit;
    Label lUnitAttack;
    
    ScrollPane scrollPane;
    Table scrollTable1;
    Window wProvinceInfo;
    Label lP1;
    Label lP2;
    Label lP3;
    public Label lProvName;
    Label lProvCountry;
    Label lProvPopulation;
    Label lP4;
    Label lPopulationProgress;
    HegeProgressBar populationProgress;
    Label lP5;
    Label lProductionProgress;
    HegeProgressBar productionProgress;
    Label lP6;
    Label lFoodIncome;
    Label lP7;
    Label lProductionIncome;
    Label lP8;
    Label lUnitThere;
    TextButton bMoveUnit;
    Label lMovementPoints;
    
    Window wChooseProject;
    Label lCB1;
    Label lCB2;
    ArrayList<Label> lBuildingProjects;
    ArrayList<HegeBuildButton> bBuildingBuild;
    ArrayList<Label> lUnitProjects;
    ArrayList<Label> lProdCost;
    ArrayList<HegeBuildButton> bUnitBuild;
    
    
    Window wCountryInfo;
    Label lC1;
    Label lCountryName;
    Label lC2;
    Label lCountryPopulation;
    Label lC3;
    Label lScienceProgress;
    HegeProgressBar scienceProgress;
    TextButton bCHide;
    
    Window wChooseTech;
    Label lT1;
    Label lT2;
    ArrayList<Label> lTechName;
    ArrayList<Label> lTechCost;
    ArrayList<HegeTechButton> bTech;
    Map<Integer, Technology> buttonTechMap;
    
    Window wUnits;
    
    public HUD(Core game, Gemelch gemelch) {
        this.game = game;
        this.gemelch = gemelch;
        this.turnCountry = gemelch.turnCountry;
        this.show();
        this.isReadyToMove = false;
        //setDebug(Core.DEV_MODE);
    }
    
    public void setSelectedProvince(Province selectedProvince) {
        if (selectedProvince != null && selectedProvince.owner == turnCountry) {
            this.selectedProvince = selectedProvince;
            game.audio.playSound(AudioManager.Sounds.UI_CLICK);
            setProvinceInfo();
            HegeLog.log(HegeLog.HUD, "Selected " + selectedProvince.name);
            if (selectedProvince.owner.id == Country.ID.NOTHING) {
                hideAllWindows();
                return;
            }
            wProvinceInfo.setVisible(true);
        } else {
            HegeLog.log(HegeLog.HUD, "Selected not country`s province or null");
            hideAllWindows();
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
        wProvinceInfo.setSize(Core.gameWidth * 0.15f / Core.uiFactor, Core.gameWidth * 0.15f / Core.uiFactor);
        wProvinceInfo.setVisible(false);
        wProvinceInfo.setResizable(true);
        lP1 = new Label("Name:", GlassyUI);
        lP2 = new Label("Controlled by:", GlassyUI);
        lP3 = new Label("Population:", GlassyUI);
        lProvName = new Label("Null", DefaultUI);
        lProvCountry = new Label("Null", DefaultUI);
        lProvPopulation = new Label("Null", DefaultUI);
        lP4 = new Label("Food points:", GlassyUI);
        lPopulationProgress = new Label("Null", DefaultUI);
        lP5 = new Label("Production points:", GlassyUI);
        lProductionProgress = new Label("Null", DefaultUI);
        lP6 = new Label("Food Income:", GlassyUI);
        lFoodIncome = new Label("Null", DefaultUI);
        lP7 = new Label("Production Income:", GlassyUI);
        lProductionIncome = new Label("Null", DefaultUI);
        lP8 = new Label("Unit in province:", GlassyUI);
        lUnitThere = new Label("Null", DefaultUI);
        
        populationProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.FOOD);
        productionProgress = new HegeProgressBar(wProvinceInfo.getWidth() * 0.15f, wProvinceInfo.getWidth() * 0.02f, HegeProgressBar.ID.PRODUCTION);
        
        bMoveUnit = new TextButton("Move Unit", DefaultUI);
        bMoveUnit.setSize(wProvinceInfo.getWidth() * 0.4f, wProvinceInfo.getHeight() * 0.3f);
        bMoveUnit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                hideAllWindows();
                lMoveUnit.setVisible(true);
                isReadyToMove = true;
                startMovingProv = selectedProvince;
            }
        });
        lMovementPoints = new Label("Null", DefaultUI);
        lMovementPoints.setVisible(false);
        
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
        wProvinceInfo.add(lP6);
        wProvinceInfo.add(lFoodIncome);
        wProvinceInfo.row();
        wProvinceInfo.add(lP7);
        wProvinceInfo.add(lProductionIncome);
        wProvinceInfo.row();
        wProvinceInfo.add(lP8);
        wProvinceInfo.add(lUnitThere);
        wProvinceInfo.row();
        wProvinceInfo.add(bMoveUnit);
        wProvinceInfo.add(lMovementPoints);
        
        wCountryInfo = new Window("Country Info", DefaultUI);
        wCountryInfo.setMovable(true);
        wCountryInfo.setPosition(Core.gameWidth * 0.3f, Core.gameHeight * 0.6f);
        wCountryInfo.setSize(Core.gameWidth * 0.15f / Core.uiFactor, Core.gameWidth * 0.125f / Core.uiFactor);
        wCountryInfo.setVisible(false);
        wCountryInfo.align(Align.top);
        
        lC1 = new Label("Country Name:", GlassyUI);
        lCountryName = new Label("Null", GlassyUI);
        lC2 = new Label("Population:", GlassyUI);
        lCountryPopulation = new Label("Null", GlassyUI);
        lC3 = new Label("Science points:", GlassyUI);
        lScienceProgress = new Label("Null", DefaultUI);
        scienceProgress = new HegeProgressBar(wCountryInfo.getWidth() * 0.15f, wCountryInfo.getWidth() * 0.02f, HegeProgressBar.ID.SCIENCE);
        
        bCHide = new TextButton("Hide", DefaultUI);
        bCHide.setSize(wCountryInfo.getWidth(), wCountryInfo.getHeight() * 0.3f);
        bCHide.setRound(true);
        bCHide.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                wCountryInfo.setVisible(false);
            }
        });
        
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
        wCountryInfo.row();
        wCountryInfo.add(bCHide);
        
        
        wChooseTech = new Window("Choose technology", DefaultUI);
        wChooseTech.setMovable(true);
        wChooseTech.setResizable(true);
        wChooseTech.setSize(Core.gameWidth * 0.15f / Core.uiFactor, Core.gameWidth * 0.125f / Core.uiFactor);
        wChooseTech.setPosition(wCountryInfo.getX(), wCountryInfo.getY() - wChooseTech.getHeight());
        wChooseTech.align(Align.top);
        wChooseTech.setVisible(false);
        
        lT1 = new Label("Tech name", GlassyUI);
        lT2 = new Label("Cost", GlassyUI);
        
        
        wChooseProject = new Window("Choose project", DefaultUI);
        wChooseProject.setMovable(true);
        wChooseProject.setResizable(true);
        wChooseProject.setSize(Core.gameWidth * 0.15f / Core.uiFactor, Core.gameWidth * 0.125f / Core.uiFactor);
        wChooseProject.setPosition(wProvinceInfo.getX(), wProvinceInfo.getY() - wChooseProject.getHeight());
        wChooseProject.align(Align.top);
        
        lCB2 = new Label("Cost", GlassyUI);
        lCB1 = new Label("Project", GlassyUI);
        
        
        Label l = new Label(" ", GlassyUI);
        wChooseProject.add(lCB1).padRight(wChooseProject.getWidth() * 0.15f);
        wChooseProject.add(lCB2).padRight(wChooseProject.getWidth() * 0.15f);
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
                setCountryInfo();
                HegeLog.log("Possible buildings" + gemelch.turnCountry.name, turnCountry.capital.possibleBuildingsToString());
                HegeLog.log("Possible units" + gemelch.turnCountry.name, turnCountry.capital.possibleUnits.toString());
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
                if (wCountryInfo.isVisible()) {
                    wCountryInfo.setVisible(false);
                    wChooseTech.setVisible(false);
                } else {
                    wCountryInfo.setVisible(true);
                    wChooseTech.setVisible(true);
                    wChooseTech.setMovable(true);
                }
            }
        });
        
        lTurnNumber = new Label("Turn " + Gemelch.turnNumber, GlassyUI, "big");
        lTurnNumber.setPosition(Core.gameWidth * 0.925f - lTurnNumber.getWidth(), bTurn.getHeight());
        
        lNewTurn = new Label("New Turn!", GlassyUI, "big");
        lNewTurn.setPosition(Core.gameWidth / 2f - lNewTurn.getWidth(), Core.gameHeight * 0.05f);
        lNewTurn.setVisible(false);
        
        lCountryTurn = new Label("Turn: " + turnCountry.name, GlassyUI, "big");
        lCountryTurn.setPosition(Core.gameWidth * 0.925f - lCountryTurn.getWidth(), lTurnNumber.getY() + lTurnNumber.getHeight());
        
        lStartHint = new Label("Choose new project in your start province \n and technology in \"Country Info\" ", GlassyUI, "big");
        lStartHint.setPosition(Core.gameWidth * 0.15f, Core.gameHeight * 0.05f);
        lStartHint.setFontScale(Core.uiFactor);
        
        lUnitAttack = new Label("Unit X attacked unit Y", GlassyUI, "big");
        lUnitAttack.setPosition((Core.gameWidth/2f - lUnitAttack.getWidth()/2f), Core.gameHeight*0.9f);
        
        lMoveUnit = new Label("Move unit to...", GlassyUI, "big");
        lMoveUnit.setPosition((Core.gameWidth - lMoveUnit.getWidth()) / 2f, Core.gameHeight * 0.95f);
        lMoveUnit.setVisible(false);
        
        stage.addActor(lTurnNumber);
        stage.addActor(lNewTurn);
        stage.addActor(lCountryTurn);
        stage.addActor(lStartHint);
        stage.addActor(lMoveUnit);
        stage.addActor(lUnitAttack);
        stage.addActor(wProvinceInfo);
        stage.addActor(wChooseProject);
        stage.addActor(wCountryInfo);
        stage.addActor(wChooseTech);
        stage.addActor(bTurn);
        stage.addActor(bBack);
        stage.addActor(bCountry);
    }
    
    public void hideAllWindows() {
        wProvinceInfo.setVisible(false);
        wChooseProject.setVisible(false);
        wCountryInfo.setVisible(false);
        wChooseTech.setVisible(false);
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
        hideAllWindows();
        gemelch.onTurn();
        setSelectedCountry(gemelch.turnCountry);
        if (selectedProvince != null)
            setProvinceInfo();
    }
    
    public void newTurn() {
        lTurnNumber.setText("Turn " + Gemelch.turnNumber);
        lNewTurn.setVisible(true);
        HegeLog.log("HegeLogic", "Button Turn pressed. Now it`s turn " + Gemelch.turnNumber);
        lStartHint.setVisible(false);
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

        lFoodIncome.setText(selectedProvince.foodIncome);
        lProductionIncome.setText(selectedProvince.productionIncome);
        
        if (selectedProvince.unitThere != null) {
            lUnitThere.setText(selectedProvince.unitThere.name);
            lMovementPoints.setText(selectedProvince.unitThere.movementPoints);
            bMoveUnit.setVisible(true);
            lMovementPoints.setVisible(true);
        } else {
            lUnitThere.setText("No Unit");
            bMoveUnit.setVisible(false);
            lMovementPoints.setVisible(false);
        }
        
        
        if (!selectedProvince.isSomethingBuilding) {
            setBuildingsInfo();
        }
    }
    
    public void setCountryInfo() {
        lCountryName.setText(turnCountry.name);
        lCountryPopulation.setText(turnCountry.population);
        lScienceProgress.setText(turnCountry.sciencePoints + " / " + turnCountry.neededSciencePoints);
        scienceProgress.setRange(0, turnCountry.neededSciencePoints);
        scienceProgress.setValue((float) turnCountry.sciencePoints);
        
        if (!turnCountry.isSomethingResearching) {
            setTechInfo();
        }
    }
    
    public void setBuildingsInfo() {
        if (selectedProvince.owner.id == Country.ID.NOTHING) {
            wProvinceInfo.setVisible(false);
            wChooseProject.setVisible(false);
            return;
        }
        wChooseProject.clear();
        wChooseProject.add(lCB1);
        wChooseProject.add(lCB2);
        Label l = new Label(" ", GlassyUI);
        wChooseProject.add(l);
        buttonBuildingMap = new HashMap<Integer, Building>();
        //обработка доступных построек
        if (selectedProvince.possibleBuildings != null) {
            for (int i = 0; i < selectedProvince.possibleBuildings.size(); i++) {
                Label tmpLabel = new Label(selectedProvince.possibleBuildings.get(i).name, DefaultUI);
                Label tmpLabel1 = new Label(Integer.toString(selectedProvince.possibleBuildings.get(i).productionCost), DefaultUI);
                lBuildingProjects.add(tmpLabel);
                lProdCost.add(tmpLabel1);
                wChooseProject.row();
                wChooseProject.add(lBuildingProjects.get(i));
                wChooseProject.add(tmpLabel1);
                final HegeBuildButton tmpButton = new HegeBuildButton(i, "Make building", DefaultUI);
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
                        HegeLog.log("Input", selectedProvince.name + ", UnitBtn ID: " + tmpButton.getId());
                        selectedProvince.chooseBuilding(buttonBuildingMap.get(tmpButton.id));
                        HegeLog.log("Province Project", "Chose building " + buttonBuildingMap.get(tmpButton.id).name);
                        setProvinceInfo();
                        wChooseProject.setVisible(false);
                        return;
//                    isClicked = true;
//                    wChooseProject.setVisible(false);
                    }
                });
                bBuildingBuild.add(tmpButton);
                wChooseProject.add(bBuildingBuild.get(i));
            }
        }
        //обработка доступных юнитов
        buttonUnitMap = new HashMap<Integer, WarUnit>();
        if (selectedProvince.possibleUnits != null) {
            for (int i = 0; i < selectedProvince.possibleUnits.size(); i++) {
                Label tmpLabel = new Label(selectedProvince.possibleUnits.get(i).name, DefaultUI);
                Label tmpLabel1 = new Label(Integer.toString(selectedProvince.possibleUnits.get(i).productionCost), DefaultUI);
                lUnitProjects.add(tmpLabel);
                lProdCost.add(tmpLabel1);
                wChooseProject.row();
                wChooseProject.add(lUnitProjects.get(i));
                wChooseProject.add(tmpLabel1);
                final HegeBuildButton tmpButton = new HegeBuildButton(i + selectedProvince.possibleBuildings.size(), "Make unit", DefaultUI);
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
                        HegeLog.log("Input", selectedProvince.name + ", UnitBtn ID: " + tmpButton.getId());
                        selectedProvince.chooseUnit(buttonUnitMap.get(tmpButton.id));
                        HegeLog.log("Province Project", "Chose unit " + buttonUnitMap.get(tmpButton.id).name);
                        setProvinceInfo();
                        wChooseProject.setVisible(false);
                        //                    isClicked = true;
//                    wChooseProject.setVisible(false);
                    }
                });
                bBuildingBuild.add(tmpButton);
                wChooseProject.add(bBuildingBuild.get(i + selectedProvince.possibleBuildings.size()));
            }
        }
        wChooseProject.setVisible(true);
        wChooseProject.setMovable(true);
    }
    
    public void setTechInfo() {
        wChooseTech.clear();
        wChooseTech.add(lT1);
        wChooseTech.add(lT2);
        Label l = new Label(" ", GlassyUI);
        wChooseTech.add(l);
        if (turnCountry.possibleTechnologies != null || !turnCountry.possibleTechnologies.isEmpty()) {
            HegeLog.log("Turn Country", turnCountry.possibleTechnologies.toString());
            lTechName = new ArrayList<>();
            lTechCost = new ArrayList<>();
            
            bTech = new ArrayList<>();
            buttonTechMap = new HashMap<>();
            for (int i = 0; i < turnCountry.possibleTechnologies.size(); i++) {
                Label tmpLabel = new Label(turnCountry.possibleTechnologies.get(i).name, DefaultUI);
                Label tmpLabel1 = new Label(Float.toString(turnCountry.possibleTechnologies.get(i).cost), DefaultUI);
                
                lTechName.add(tmpLabel);
                lTechCost.add(tmpLabel1);
                wChooseTech.row();
                wChooseTech.add(lTechName.get(i));
                wChooseTech.add(tmpLabel1);
                final HegeTechButton tmpButton = new HegeTechButton(i, "Research", DefaultUI);
                buttonTechMap.put(i, turnCountry.possibleTechnologies.get(i));
                tmpButton.setRound(true);
                tmpButton.setSize(wChooseProject.getWidth() * 0.15f, wChooseProject.getHeight() * 0.1f);
                tmpButton.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                    
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        HegeLog.log("Input", "TechButton: " + tmpButton.getId());
                        HegeLog.log("Country choosing tech", "Chose " + buttonTechMap.get(tmpButton.id).toString());
                        turnCountry.chooseTechnology(buttonTechMap.get(tmpButton.id));
                        HegeLog.log("Province Project", "Chose tech " + buttonTechMap.get(tmpButton.id).name);
                        setCountryInfo();
                        wChooseTech.setVisible(false);
                    }
                });
                bTech.add(tmpButton);
                wChooseTech.add(bTech.get(i));
            }
        }
    }
    
    public void endUnitMovement() {
        lMoveUnit.setVisible(false);
        isReadyToMove = false;
    }
    
    public void attackUnit(WarUnit attackUnit, WarUnit defenseUnit) {
        lUnitAttack.setText("Unit " + attackUnit.name + "attacked unit " + defenseUnit.name + "!");
        lUnitAttack.setPosition((Core.gameWidth/2f - lUnitAttack.getWidth()/2f), Core.gameHeight*0.9f);
        lUnitAttack.setVisible(true);
    }
    
    public void setDebug(boolean debug) {
        if (debug) {
            stage.setDebugAll(true);
        }
    }
    
}
