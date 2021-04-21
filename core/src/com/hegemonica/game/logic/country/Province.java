package com.hegemonica.game.logic.country;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.resource.Resource;
import com.hegemonica.game.logic.scenarios.gemelch.Gemelch;

import java.util.HashMap;

public class Province {
    public int startFoodproduction;
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
    public int numberOfBuildings;

    public Building buildingInProcess;

    public int id;
    public String name;
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
    public boolean neighbourProvinces[];

    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();
    public FloatArray provCoords;
    private ShapeRenderer shapeRenderer;
    private Texture texture;
    private TextureRegion textureReg;
    private PolygonRegion polyReg;
    private PolygonSprite polySprite;
    private PolygonSpriteBatch polyBatch;
    private Pixmap pix;




    public Province(int id, String name, Country owner, boolean[] neighbours) {
        this.id = id;
        this.population = population;
        this.name = name;
        this.owner = owner;
        this.neighbourProvinces = neighbours;
    }
    public void update(){

        switch (climate){

        }
        switch (landscape){

        }
        switch (status){

        }
    }
    //статус провинции
    public class Status {
        //климат
        public class Climate{
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
        public class Landscape {}

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
                numberOfBuildings += 1;
                productionPoints -= owner.farm.productionCost;
            case Building.ID.MINE:
                numberOfMines += 1;
                numberOfBuildings += 1;
                productionPoints -= owner.mine.productionCost;
            case Building.ID.LIBRARY:
                numberOfLibraries = 1;
                numberOfBuildings += 1;
                productionPoints -= owner.library.productionCost;
            case Building.ID.UNIVERSITY:
                numberOfUniversities = 1;
                numberOfBuildings += 1;
                productionPoints -= owner.university.productionCost;
            case Building.ID.WORKSHOP:
                numberOfWorkshops = 1;
                numberOfBuildings += 1;
                productionPoints -= owner.workshop.productionCost;
            case Building.ID.SHIPYARD:
                numberOfShipyards = 1;
                numberOfBuildings += 1;
                productionPoints -= owner.shipyard.productionCost;
        }

    }

    public void onTurn() {
        foodPoints += numberOfFarms * farmProduction - neededFood + startFoodproduction;
        if (foodPoints > neededFoodPoints) {
            provinceGrow();
        }
        else if (foodPoints < 0) {
            provinceDecrease();
        }
        productionPoints += population + numberOfMines * mineProduction + numberOfWorkshops * workshopProduction + numberOfShipyards * shipyardProduction;
        //дописать neededProductionPoints
        gainedSciencePoints = population + numberOfLibraries * libraryProduction + numberOfUniversities * universityProduction;
    }

    public boolean isBuildingAvailible(Building building) {
        if (owner.checkRequiredTechnologiesForBuilding(building) && numberOfBuildings < population) {
            if (building.isNeedCity == false || building.isNeedCity == isCity) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return  false;
        }
    }

    public void choseBuilding(Building building) {
        if (isBuildingAvailible(building)) {
            buildingInProcess = building;

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
        numberOfBuildings = 0;
    }

    //math and graphics
    public void render(){

    }
    private ShortArray triangulate(FloatArray polygonVertices){
        return triangulator.computeTriangles(polygonVertices);
    }
    public int[] getXcoords(){
        int Xcoords[] = new int[provCoords.size/2];
        Xcoords[0] = (int) provCoords.items[0];
        for(int i=2; i<provCoords.size;i+=2){
            Xcoords[i-1] = (int) provCoords.items[i];
        }
        return Xcoords;
    }
    public int[] getYcoords(){
        int Ycoords[] = new int[provCoords.size/2];
        Ycoords[0] = (int) provCoords.items[1];
        for(int i=1; i<provCoords.size;i+=2){
            Xcoords[i-1/2] = (int) provCoords.items[i];
        }
    }
}
