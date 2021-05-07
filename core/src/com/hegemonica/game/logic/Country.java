package com.hegemonica.game.logic;


import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class Country {
    public String name;
    public int id;
    public int cash;
    public int size;
    public int prestige;
    public float stability;
    public int maxStability;
    public float inflation;
    public boolean hasCrisis;
    public boolean inWar;

    public Color color;

    public int gainedSciencePoints;

    public ArrayList<Province> provinces;

    public Technology technologyInProcess;

    //production
    public int citizenProduction;
    public int mineProduction;
    public int workshopProduction;
    public int shipyardProduction;

    //food production

    public int startFoodProduction;
    public int farmProduction;
    public int shipyardFoodProduction;

    //science production
    public int citizenScienceProduction;
    public int libraryProduction;
    public int universityProduction;

    public Technologies technologies;

    //наличие персонажа в стране
    public boolean hasBohema;
    public boolean hasSeaman;
    public boolean hasMinister;
    public boolean hasBusinessman;
    public boolean hasRevoltman;
    public boolean hasGeneral;

    //HashMap resuorces<Resource, double>;
    public Gemelch gemelch;


    public Country(String name, int id, Color color) {
        this.name = name;
        this.id = id;
        this.color = color;
        cash = 50;
        prestige = 1;
        stability = 1;
        inflation = 0f;
        technologies = new Technologies();

    }

    public void onTurn() {

    }


    public class Resources {
        public Resource Wood;
        public Resource Iron;
        public Resource Gold;
        public Resource Gem;
        public Resource Grain;
        public Resource Horses;
        public Resource Cows;
    }

    public void technologyResearch(Technology technology) {
        switch (technology.id) {
            case Technology.ID.ENGINEERING:
                technologies.engineering.isResearched = true;
                mineProduction++;
            case Technology.ID.PAPER:
                technologies.paper.isResearched = true;
            case Technology.ID.SIMPLYCHEMISTRY:
                technologies.simplyChemistry.isResearched = true;
                farmProduction++;
            case Technology.ID.MACHINERY:
                technologies.machinery.isResearched = true;
            case Technology.ID.APPRIENTSHIP:
                technologies.apprientship.isResearched = true;
                mineProduction++;
            case Technology.ID.EDUCATION:
                technologies.education.isResearched = true;
                libraryProduction++;
            case Technology.ID.UPDATEDSHIPBUILDING:
                technologies.updatedShipbuilding.isResearched = true;
            case Technology.ID.OCEANEXPLORATION:
                technologies.oceanExploration.isResearched = true;
                shipyardProduction += 2;
                shipyardFoodProduction += 2;
        }
    }

    public boolean checkRequiredTechnologiesForBuilding(Building building) {
        switch (building.id) {
            case Building.ID.FARM:
                return true;
            case Building.ID.MINE:
                return true;
            case Building.ID.LIBRARY:
                return technologies.paper.isResearched;
            case Building.ID.UNIVERSITY:
                return technologies.education.isResearched;
            case Building.ID.SHIPYARD:
                return technologies.updatedShipbuilding.isResearched;
            case Building.ID.WORKSHOP:
                return technologies.engineering.isResearched;
            default:
                return true;
        }
    }


//    public static class Ethnoses {
//        final static int SVADIANS = 1;
//    }
//
//    public static class СultureGroups {
//        final static public int WEST = 1;
//    }
//    public static class Religions {
//        final static  int KANON = 1;
//    }
//
//    public void update(){ //обновление данных страны при каждом ходе
//        if(hasBohema){
//        }
//    }

}
