package com.hegemonica.game.logic.country;

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

    public Building chosenBuilding;

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



    public Province(int id, int population, String name, Country owner) {
        this.id = id;
        this.population = population;
        this.name = name;
        this.owner = owner;
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

    public void choseBuilding(Building chosenBuilding) {
        this.chosenBuilding = chosenBuilding;
        //сдедать switch для установления neededProductionPoints
    }

    public void onCreate() {
        neededFood = population;
        neededFoodPoints = 2 + population * 3;
        foodPoints = 0;
        gainedSciencePoints = 0;
    }
}
