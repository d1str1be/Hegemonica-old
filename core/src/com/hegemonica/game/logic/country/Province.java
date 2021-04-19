package com.hegemonica.game.logic.country;

import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.resource.Resource;

import java.util.HashMap;

public class Province {
    public int foodScore;
    public int neededFoodScore;
    public int population;
    public int neededFood;

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



    public Province(int id, String name, int climateType, int landscape, Country owner, int resourceID) {
        this.id = id;
        this.name = name;
        this.climate = climateType;
        this.landscape = landscape;
        this.owner = owner;
        this.resource = new Resource(resourceID);
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
        foodScore -= neededFoodScore;
        neededFoodScore += 1;
    }

    public void provinceDecrease() {
        population -= 1;
        neededFood -= 1;
        foodScore += neededFoodScore - 1;
        neededFoodScore -= 1;
    }
    public void onTurn() {
        foodScore += numberOfFarms - neededFood;
    }



}
