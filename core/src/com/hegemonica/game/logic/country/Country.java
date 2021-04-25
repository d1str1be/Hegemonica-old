package com.hegemonica.game.logic.country;

import com.hegemonica.game.logic.Technologies;
import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.resource.Resource;
import com.hegemonica.game.logic.scenarios.gemelch.Gemelch;

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



    public Country (String name, int id) {
        this.name = name;
        this.id = id;
        cash = 50;
        prestige = 1;
        stability = 1;
        inflation = 0f;
        technologies = new Technologies();

    }

    public void onTurn(){

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
