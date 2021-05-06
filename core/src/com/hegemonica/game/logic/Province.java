package com.hegemonica.game.logic;

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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.hegemonica.game.Core;

public class Province {
    public int id;
    public String name;

    public int startFoodProduction;
    public int foodPoints;
    public int neededFoodPoints;
    public int population;
    public int neededFood;
    public int productionPoints;
    public int neededProductionPoints;
    public int gainedSciencePoints;

    public int mineProduction;
    public int farmProduction;
    public int workshopProduction;
    public int shipyardProduction;
    public int libraryProduction;
    public int universityProduction;

    public int numberOfLimitedBuildings;

    public Building buildingInProcess;


    public boolean isCity;

    public int numberOfLibraries;
    public int numberOfUniversities;
    public int numberOfWorkshops;
    public int numberOfShipyards;
    public int numberOfFarms;
    public int numberOfMines;

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

    public Button btn;

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
        numberOfLimitedBuildings = 0;

        library = new Building(Building.ID.LIBRARY, this);
        university = new Building(Building.ID.UNIVERSITY, this);
        shipyard = new Building(Building.ID.SHIPYARD, this);
        workshop = new Building(Building.ID.WORKSHOP, this);
        farm = new Building(Building.ID.FARM, this);
        mine = new Building(Building.ID.MINE, this);


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
        population = 1;
        numberOfShipyards = 0;
        numberOfWorkshops = 0;
        numberOfUniversities = 0;
        numberOfLibraries = 0;
        numberOfMines = 0;
        numberOfFarms = 0;
        numberOfLimitedBuildings = 0;

        library = new Building(Building.ID.LIBRARY, this);
        university = new Building(Building.ID.UNIVERSITY, this);
        shipyard = new Building(Building.ID.SHIPYARD, this);
        workshop = new Building(Building.ID.WORKSHOP, this);
        farm = new Building(Building.ID.FARM, this);
        mine = new Building(Building.ID.MINE, this);

        rectangle = new Rectangle(x, y, width, height);

        btn = new Button();
        this.setMathRender();
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

    //статус провинции
    public class Status {
        //климат
        public class Climate {
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
        public class Landscape {
        }

    }

    public void provinceGrow() {
        population += 1;
        neededFood += 1;
        foodPoints -= neededFoodPoints;
        neededFoodPoints += 1;
    }

    public void provinceDecrease() {
        population -= 1;
        neededFood -= 1;
        foodPoints += neededFoodPoints - 1;
        neededFoodPoints -= 1;
    }

    public void build(Building building) {
        switch (building.id) {
            case Building.ID.FARM:
                numberOfFarms += 1;
                productionPoints -= farm.productionCost;
            case Building.ID.MINE:
                numberOfMines += 1;
                productionPoints -= mine.productionCost;
            case Building.ID.LIBRARY:
                numberOfLibraries = 1;
                numberOfLimitedBuildings += 1;
                productionPoints -= library.productionCost;
            case Building.ID.UNIVERSITY:
                numberOfUniversities = 1;
                numberOfLimitedBuildings += 1;
                productionPoints -= university.productionCost;
            case Building.ID.WORKSHOP:
                numberOfWorkshops = 1;
                numberOfLimitedBuildings += 1;
                productionPoints -= workshop.productionCost;
            case Building.ID.SHIPYARD:
                numberOfShipyards = 1;
                numberOfLimitedBuildings += 1;
                productionPoints -= shipyard.productionCost;
            case Building.ID.CITY:
                isCity = true;
                productionPoints -= city.productionCost;
        }

    }

    public void onTurn() {
        foodPoints += numberOfFarms * farmProduction - neededFood + startFoodProduction;
        if (foodPoints > neededFoodPoints) {
            provinceGrow();
        } else if (foodPoints < 0) {
            provinceDecrease();
        }
        productionPoints += population + numberOfMines * mineProduction + numberOfWorkshops * workshopProduction + numberOfShipyards * shipyardProduction;
        if (productionPoints >= neededProductionPoints) {
            build(buildingInProcess);
            buildingInProcess = null;
        }
        gainedSciencePoints = population + numberOfLibraries * libraryProduction + numberOfUniversities * universityProduction;
    }

    public boolean isBuildingAvailable(Building building) {
        if (owner.checkRequiredTechnologiesForBuilding(building)) {
            return !building.isNeedCity || building.isNeedCity == isCity;
        } else {
            return false;
        }
    }

    //переписать
    public void chooseBuilding(Building building) {
        if (isBuildingAvailable(building)) {
            buildingInProcess = building;
        } else {
            chooseBuilding(building);
        }
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
        numberOfLimitedBuildings = 0;
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

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (provCoords != null) { // если отрисовка полигоном, то центрируем по полигону
            font.draw(batch, name, polygon.getBoundingRectangle().getX() + polygon.getBoundingRectangle().getWidth() / 2 - (polygon.getBoundingRectangle().getWidth() * 0.25f), polygon.getBoundingRectangle().getY() + polygon.getBoundingRectangle().getHeight() / 2 - font.getScaleY());
        } else { //иначе у нас отрисовка прямоугольником, центрируем по координатам прямоугольника
            font.draw(batch, name, x + (width / 2) - 18, y + (height / 2));
            if (Core.DEV_MODE) {
                font.draw(batch, "x: " + x, x + (width / 2) - 18, y + (height / 2) - Core.gameHeight * 0.015f);
                font.draw(batch, "y: " + y, x + (width / 2) - 18, y + (height / 2) - Core.gameHeight * 0.030f);
            }
        }
        batch.end();
    }

    public void setMathRender() {
        shapeRenderer = new ShapeRenderer();
//        pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pix.setColor(Color.RED);
//        pix.fill();
//        texture = new Texture(pix);
//        textureReg = new TextureRegion(texture);
//        polyReg = new PolygonRegion(textureReg, ProvCoords.levianProv.toArray(), triangulate(ProvCoords.levianProv).toArray());
//        polySprite = new PolygonSprite(polyReg);
//        polyBatch = new PolygonSpriteBatch();
        if (provCoords != null)
            polygon = new Polygon(provCoords.toArray());
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(0.5f);
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

}
