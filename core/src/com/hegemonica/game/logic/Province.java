package com.hegemonica.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.logic.units.DefenseUnit;
import com.hegemonica.game.logic.units.MeleeUnit;
import com.hegemonica.game.logic.units.RangedUnit;
import com.hegemonica.game.logic.units.WarUnit;

import java.util.ArrayList;


public class Province {
    public int id;
    public String name;

    public int foodPoints;
    public int neededFoodPoints;
    public int population;
    public int neededFood;
    public int productionPoints;
    public int neededProductionPoints;
    public int gainedSciencePoints;
    public int projectId;
    public boolean isSomethingBuilding;

    public int numberOfBuildings;

    public Building buildingInProcess;
    public WarUnit unitInProcess;
    public Building[] buildings;
    public WarUnit[] units;
    public ArrayList<Building> possibleBuildings;
    public ArrayList<WarUnit> possibleUnits;
    public WarUnit warrior;
    public WarUnit archer;
    public WarUnit shielder;
    public WarUnit crossbows;
    public WarUnit swordsman;


    public boolean isCity;

    public int numberOfLibraries;
    public int numberOfUniversities;
    public int numberOfWorkshops;
    public int numberOfShipyards;
    public int numberOfFarms;
    public int numberOfMines;

    //units
    public int unitCounter;
    public ArrayList<WarUnit> createdUnits;
    public WarUnit unitThere;

    public int climate;
    public int landscape;
    public int status;

    public int sizeLimit;
    public int sizeUsed;

    public Resource resource;


    public Country owner;
    public boolean[] neighbourProvinces;


    public float x;
    public float y;
    public float width;
    public float height;
    public Rectangle rectangle;

    public FloatArray provCoords;
    private Polygon polygon;
    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();


    private ShapeRenderer shapeRenderer;
    private Texture texture;
    private TextureRegion textureReg;
    private PolygonRegion polyReg;
    private PolygonSprite polySprite;
    private PolygonSpriteBatch polyBatch;
    private Pixmap pix;
    private BitmapFont font;
    private SpriteBatch batch;


    //постройки
    public Building library;
    public Building university;
    public Building shipyard;
    public Building workshop;
    public Building farm;
    public Building mine;
    public Building city;

    public Skin defaultSkin;
    public Label lProvName;

    public Province(int id, String name, Country owner, boolean[] neighbours, boolean isCity, FloatArray provCoords) {
        this.id = id;
        this.provCoords = provCoords;
        this.name = name;
        this.owner = owner;
        this.neighbourProvinces = neighbours;
        this.isCity = isCity;
        population = 1;
        numberOfShipyards = 0;
        numberOfWorkshops = 0;
        numberOfUniversities = 0;
        numberOfLibraries = 0;
        numberOfMines = 0;
        numberOfFarms = 0;
        numberOfBuildings = 0;

        library = new Building(Building.ID.LIBRARY, this);
        university = new Building(Building.ID.UNIVERSITY, this);
        //shipyard = new Building(Building.ID.SHIPYARD, this);
        workshop = new Building(Building.ID.WORKSHOP, this);
        farm = new Building(Building.ID.FARM, this);
        mine = new Building(Building.ID.MINE, this);
        city = new Building(Building.ID.CITY, this);

        unitCounter = 0;
        createdUnits = new ArrayList<WarUnit>();

        defaultSkin = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));

        this.setMathRender();
    }

    public Province(int id, String name, Country owner, boolean[] neighbours, boolean isCity, float x, float y, float width, float height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.owner = owner;
        this.neighbourProvinces = neighbours;
        this.isCity = isCity;
        foodPoints = 0;
        population = 1;
        numberOfShipyards = 0;
        numberOfWorkshops = 0;
        numberOfUniversities = 0;
        numberOfLibraries = 0;
        numberOfMines = 0;
        numberOfFarms = 0;
        numberOfBuildings = 0;
        neededFoodPoints = 10;

        library = new Building(Building.ID.LIBRARY, this);
        university = new Building(Building.ID.UNIVERSITY, this);
        //shipyard = new Building(Building.ID.SHIPYARD, this);
        workshop = new Building(Building.ID.WORKSHOP, this);
        farm = new Building(Building.ID.FARM, this);
        mine = new Building(Building.ID.MINE, this);
        city = new Building(Building.ID.CITY, this);
        buildings = new Building[6];
        buildings[Building.ID.FARM] = farm;
        buildings[Building.ID.MINE] = mine;
        buildings[Building.ID.LIBRARY] = library;
        buildings[Building.ID.UNIVERSITY] = university;
        buildings[Building.ID.WORKSHOP] = workshop;
        buildings[Building.ID.CITY] = city;
        possibleBuildings = new ArrayList<Building>();
        possibleBuildings.add(farm);
        possibleBuildings.add(mine);
        possibleBuildings.add(city);
        productionPoints = 0;
        isSomethingBuilding = false;

        warrior = new WarUnit(WarUnit.ID.WARRIOR, this);
        archer = new WarUnit(WarUnit.ID.ARCHER, this);
        shielder = new WarUnit(WarUnit.ID.SHIELDER, this);
        crossbows = new WarUnit(WarUnit.ID.CROSSBOWS, this);
        swordsman = new WarUnit(WarUnit.ID.SWORDSMAN, this);
        units = new WarUnit[5];
        units[WarUnit.ID.WARRIOR] = warrior;
        units[WarUnit.ID.ARCHER] = archer;
        units[WarUnit.ID.SHIELDER] = shielder;
        units[WarUnit.ID.CROSSBOWS] = crossbows;
        units[WarUnit.ID.SWORDSMAN] = swordsman;
        possibleUnits = new ArrayList<WarUnit>();
        possibleUnits.add(warrior);
        unitCounter = 0;
        createdUnits = new ArrayList<WarUnit>();

        rectangle = new Rectangle(x, y, width, height);

        defaultSkin = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        lProvName = new Label(name, defaultSkin);
        lProvName.setSize(width * 0.2f, height * 0.2f);
        lProvName.setColor(owner.color);
        lProvName.setFontScale(0.5f);
        if (provCoords != null) {
            // код для установки позиции лейбла названия провинции в центре полигона
        } else {   //иначе у нас вместо полигона прямоугольник, тогда лейбл устанавливаем по нему
            lProvName.setPosition(x + (width * 0.05f), y);
        }
        this.setMathRender();

        HegeLog.log("Province", "Needed Prod: " + neededProductionPoints);
    }

    public void setupBuildings() {

    }

    public void update() {
        onTurn();

        switch (climate) {

        }
        switch (landscape) {

        }
        switch (status) {

        }
    }

    public void setPossibleBuildings() {
        possibleBuildings.clear();
        for (Building building : buildings) {
            if (isBuildingAvailable(building)) {
                possibleBuildings.add(building);
            }
        }
    }

    public void setPossibleUnits() {
        possibleUnits.clear();
        for (WarUnit unit : units) {
            if (isUnitAvailable(unit)) {
                possibleUnits.add(unit);
            }
        }
    }

    public void onTurn() {
        foodPoints += numberOfFarms * owner.farmProduction - neededFood + owner.startFoodProduction + numberOfShipyards * owner.startFoodProduction;
        if (foodPoints > neededFoodPoints) {
            HegeLog.log("Province", name + " grew");
            provinceGrow();
        } else if (foodPoints < 0) {
            HegeLog.log("Province", name + " decreased");
            provinceDecrease();
        }
        productionPoints += population + numberOfMines * owner.mineProduction + numberOfWorkshops * owner.workshopProduction + numberOfShipyards * owner.shipyardProduction;
        if (productionPoints >= neededProductionPoints) {
            switch (projectId) {
                case PROJECTID.BUILDING:
                    build(buildingInProcess);
                    buildingInProcess = null;
                    isSomethingBuilding = false;
                    break;
                case PROJECTID.UNIT:
                    if (unitThere == null) {
                        createUnit(unitInProcess);
                        unitInProcess = null;
                    }
                    break;
                case PROJECTID.UNITUPGRADE:
                    unitThere.upgrade();
                    isSomethingBuilding = false;
                    break;
            }
            productionPoints -= neededProductionPoints;
        }
        gainedSciencePoints = population + numberOfLibraries * owner.libraryProduction + numberOfUniversities * owner.universityProduction;
        owner.sciencePoints += gainedSciencePoints;
        setPossibleBuildings();
        setPossibleUnits();
    }

    public void setOwner(Country newOwner) {
        this.owner = newOwner;
        lProvName.setColor(owner.color);
    }

    //статус провинции
    public static class Status {
        //климат
        public static class Climate {
            public static final int ARCTIC = -3; // арктический
            public static final int COLD = -2; // холодный
            public static final int CHILL = -1;// прохладный
            public static final int MODERATE = 0; // умеренный
            public static final int WARM = 1;
            public static final int HOT = 2;
            public static final int SCORCHING = 3; //знойный
            public static final int EQUATORIAL = 4; //экваториальный
        }

        //рельеф
        public static class Landscape {
        }

    }

    public void provinceGrow() {
        population += 1;
        neededFood += 1;
        foodPoints -= neededFoodPoints;
        neededFoodPoints += 2;
    }

    public void provinceDecrease() {
        population -= 1;
        neededFood -= 1;
        foodPoints += neededFoodPoints - 1;
        neededFoodPoints -= 2;
    }

    public void build(Building building) {
        switch (building.id) {
            case Building.ID.FARM:
                numberOfFarms += 1;
                numberOfBuildings += 1;
                break;
            case Building.ID.MINE:
                numberOfMines += 1;
                numberOfBuildings += 1;
                break;
            case Building.ID.LIBRARY:
                numberOfLibraries = 1;
                numberOfBuildings += 1;
                break;
            case Building.ID.UNIVERSITY:
                numberOfUniversities = 1;
                numberOfBuildings += 1;
                break;
            case Building.ID.WORKSHOP:
                numberOfWorkshops = 1;
                numberOfBuildings += 1;
                break;
            //case Building.ID.SHIPYARD:
            //    numberOfShipyards = 1;
            //    numberOfLimitedBuildings += 1;
            //    productionPoints -= shipyard.productionCost;
            case Building.ID.CITY:
                isCity = true;
                productionPoints -= city.productionCost;
                break;
        }
        HegeLog.log("Province", name + ": built " + buildingInProcess.name);
        buildingInProcess = farm;
    }

    public void createUnit(WarUnit unit) {
        switch (unit.id) {
            case WarUnit.ID.WARRIOR:
                createdUnits.add(new MeleeUnit(WarUnit.ID.WARRIOR, owner, WarUnit.COST.WARRIOR, WarUnit.ATTACKSTRENGTH.WARRIOR, WarUnit.DEFENSESTRENGTH.WARRIOR, WarUnit.MOVEMENTPOINTS.WARRIOR, this, unitCounter, MeleeUnit.UPGRADELEVEL.WARRIOR, "Warrior"));
                unitThere = createdUnits.get(unitCounter);
                unitCounter++;
                productionPoints -= WarUnit.COST.WARRIOR;
                break;
            case WarUnit.ID.ARCHER:
                createdUnits.add(new RangedUnit(WarUnit.ID.ARCHER, owner, WarUnit.COST.ARCHER, WarUnit.ATTACKSTRENGTH.ARCHER, WarUnit.DEFENSESTRENGTH.ARCHER, WarUnit.MOVEMENTPOINTS.ARCHER, this, unitCounter, RangedUnit.UPGRADELEVEL.ARCHER, "Archer"));
                unitThere = createdUnits.get(unitCounter);
                unitCounter++;
                productionPoints -= WarUnit.COST.ARCHER;
                break;
            case WarUnit.ID.SHIELDER:
                createdUnits.add(new DefenseUnit(WarUnit.ID.SHIELDER, owner, WarUnit.COST.SHIELDER, WarUnit.ATTACKSTRENGTH.SHIELDER, WarUnit.DEFENSESTRENGTH.SHIELDER, WarUnit.MOVEMENTPOINTS.SHIELDER, this, unitCounter, DefenseUnit.UPGRADELEVEL.SHIELDER, "Shielder"));
                unitThere = createdUnits.get(unitCounter);
                unitCounter++;
                productionPoints -= WarUnit.COST.SHIELDER;
                break;
            case WarUnit.ID.CROSSBOWS:
                createdUnits.add(new RangedUnit(WarUnit.ID.CROSSBOWS, owner, WarUnit.COST.CROSSBOWS, WarUnit.ATTACKSTRENGTH.CROSSBOWS, WarUnit.DEFENSESTRENGTH.CROSSBOWS, WarUnit.MOVEMENTPOINTS.CROSSBOWS, this, unitCounter, RangedUnit.UPGRADELEVEL.CROSSBOWS, "Crossbows"));
                unitThere = createdUnits.get(unitCounter);
                unitCounter++;
                productionPoints -= WarUnit.COST.CROSSBOWS;
                break;
            case WarUnit.ID.SWORDSMAN:
                createdUnits.add(new MeleeUnit(WarUnit.ID.SWORDSMAN, owner, WarUnit.COST.SWORDSMAN, WarUnit.ATTACKSTRENGTH.SWORDSMAN, WarUnit.DEFENSESTRENGTH.SWORDSMAN, WarUnit.MOVEMENTPOINTS.SWORDSMAN, this, unitCounter, MeleeUnit.UPGRADELEVEL.SWORDSMAN, "Swordsman"));
                unitThere = createdUnits.get(unitCounter);
                unitCounter++;
                productionPoints -= WarUnit.COST.SWORDSMAN;
                break;
        }
    }

    public boolean isBuildingAvailable(Building building) {
        if (owner.checkRequiredTechnologiesForBuilding(building) && numberOfBuildings < population) {
            return !building.isNeedCity || building.isNeedCity == isCity;
        } else {
            return false;
        }
    }

    public boolean isUnitAvailable(WarUnit unit) {
        if (owner.checkRequiredTechnologiesForUnit(unit) && isCity && unitThere != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUpgradeAvailable() {
        switch (unitThere.id) {
            case WarUnit.ID.WARRIOR:
                if (isUnitAvailable(swordsman)) {
                    return true;
                } else {
                    return false;
                }
            case WarUnit.ID.ARCHER:
                if (isUnitAvailable(crossbows)) {
                    return true;
                } else {
                    return false;
                }
            case WarUnit.ID.SHIELDER:
                return false;
            case WarUnit.ID.CROSSBOWS:
                return false;
            case WarUnit.ID.SWORDSMAN:
                return false;
            default:
                return false;
        }
    }

    public boolean isTurnAvailable() {
        if (isSomethingBuilding) {
            return true;
        } else {
            return false;
        }
    }

    public void chooseBuilding(Building building) {
        buildingInProcess = building;
        neededProductionPoints = building.productionCost;
        projectId = PROJECTID.BUILDING;
        isSomethingBuilding = true;
    }

    public void chooseUnit(WarUnit unit) {
        unitInProcess = unit;
        neededProductionPoints = unit.cost;
        projectId = PROJECTID.UNIT;
        isSomethingBuilding = true;
    }

    public void upgradeUnit() {
        switch (unitThere.id) {
            case WarUnit.ID.WARRIOR:
                neededProductionPoints = WarUnit.UPGRADECOST.SWORDSMAN;
                break;
            case WarUnit.ID.ARCHER:
                neededProductionPoints = WarUnit.UPGRADECOST.SWORDSMAN;
                break;
        }
        projectId = PROJECTID.UNITUPGRADE;
        isSomethingBuilding = true;
    }

    public Province(Country owner, boolean isCity) {
        this.owner = owner;
        this.isCity = isCity;
        population = 1;
        numberOfShipyards = 0;
        numberOfWorkshops = 0;
        numberOfUniversities = 0;
        numberOfLibraries = 0;
        numberOfMines = 0;
        numberOfFarms = 0;
        numberOfBuildings = 0;
    }

    //math and graphics
    public void render(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        if (provCoords != null)
            shapeRenderer.polygon(provCoords.toArray());
        else
            shapeRenderer.rect(x, y, width, height);
//                shapeRenderer.rect(polygon.getBoundingRectangle().getX(),polygon.getBoundingRectangle().getY(),polygon.getBoundingRectangle().getWidth(), polygon.getBoundingRectangle().getHeight());
        shapeRenderer.end();
    }


    public void setMathRender() {
        shapeRenderer = new ShapeRenderer();
        if (provCoords != null)
            polygon = new Polygon(provCoords.toArray());
        else
            rectangle = new Rectangle(x, y, width, height);
    }

    private ShortArray triangulate(FloatArray polygonVertices) {
        return triangulator.computeTriangles(polygonVertices);
    }

    public boolean contains(float x, float y) {
        if (provCoords != null) {
//            HegeLog.log("Province Math", "using polygon for " + name + " prov.contains()");
            return polygon.contains(x, y);
        } else {
//            HegeLog.log("Province Math", "using rectangle for " + name + " contains()");
            return rectangle.contains(x, y);
        }
    }

    public int[] getXcoords() {
        if (provCoords == null)
            return null;
        int[] Xcoords = new int[provCoords.size / 2];
        Xcoords[0] = (int) provCoords.items[0];
        for (int i = 2; i < provCoords.size; i += 2) {
            Xcoords[i / 2] = (int) provCoords.items[i];
        }
        return Xcoords;
    }

    public int[] getYcoords() {
        if (provCoords == null)
            return null;
        int[] Ycoords = new int[provCoords.size / 2];
        Ycoords[0] = (int) provCoords.items[1];
        for (int i = 1; i < provCoords.size; i += 2) {
            Ycoords[i - 1 / 2] = (int) provCoords.items[i];
        }
        return Ycoords;
    }

    public class PROJECTID {
        public final static int BUILDING = 0;
        public final static int UNIT = 1;
        public final static int UNITUPGRADE = 2;
    }


}
