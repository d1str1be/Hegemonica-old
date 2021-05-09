package com.hegemonica.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.logic.units.WarUnit;

import java.util.ArrayList;

import static com.hegemonica.game.logic.Technology.ID.APPRENTICESHIP;
import static com.hegemonica.game.logic.Technology.ID.EDUCATION;
import static com.hegemonica.game.logic.Technology.ID.ENGINEERING;
import static com.hegemonica.game.logic.Technology.ID.MACHINERY;
import static com.hegemonica.game.logic.Technology.ID.PAPER;
import static com.hegemonica.game.logic.Technology.ID.SIMPLYCHEMISTRY;

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
    
    public int population;
    public int sciencePoints;
    
    
    
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
    
    
    public Technology[] technologies;
    public ArrayList<Technology> possibleTechnologies;
    public Technology engineering;
    public Technology paper;
    public Technology simplyChemistry;
    public Technology machinery;
    public Technology apprienticeship;
    public Technology education;
    
    public Technology technologyInProcess;
    public boolean isSomethingResearching;
    //public Technology updatedShipbuilding;
    //public Technology oceanExploration;
    
    public float neededSciencePoints;
    
    //наличие персонажа в стране
    public boolean hasBohema;
    public boolean hasSeaman;
    public boolean hasMinister;
    public boolean hasBusinessman;
    public boolean hasRevoltman;
    public boolean hasGeneral;
    
    //HashMap resuorces<Resource, double>;
    public Gemelch gemelch;
    
    
    public Country(Gemelch gemelch, String name, int id, Color color) {
        this.gemelch = gemelch;
        this.name = name;
        this.id = id;
        this.color = color;
        cash = 50;
        prestige = 1;
        stability = 1;
        inflation = 0f;
        
        population = 1;
        sciencePoints = 0;
        citizenProduction = 1;
        mineProduction = 2;
        workshopProduction = 8;
        shipyardProduction = 3;
        
        startFoodProduction = 2;
        farmProduction = 2;
        shipyardProduction = 2;
        
        citizenScienceProduction = 1;
        libraryProduction = 4;
        universityProduction = 9;
        
        technologies = new Technology[6];
        
        technologies[ENGINEERING] = new Technology(ENGINEERING, 25, new Technology[]{});;
        technologies[PAPER] = new Technology(PAPER, 25, new Technology[]{});
        technologies[SIMPLYCHEMISTRY] = new Technology(SIMPLYCHEMISTRY, 25, new Technology[]{});
        technologies[MACHINERY] = new Technology(MACHINERY, 75, new Technology[]{technologies[ENGINEERING]});
        technologies[APPRENTICESHIP] = new Technology(APPRENTICESHIP, 75, new Technology[]{technologies[ENGINEERING], technologies[PAPER]});
        technologies[EDUCATION] = new Technology(EDUCATION, 75, new Technology[]{technologies[PAPER], technologies[SIMPLYCHEMISTRY]});
        isSomethingResearching = false;
        
        possibleTechnologies = new ArrayList<>();
        possibleTechnologies.add(technologies[ENGINEERING]);
        possibleTechnologies.add(technologies[PAPER]);
        possibleTechnologies.add(technologies[SIMPLYCHEMISTRY]);
        //updatedShipbuilding = new Technology(Technology.ID.UPDATEDSHIPBUILDING, 75, new Technology[]{});
        //oceanExploration = new Technology(Technology.ID.OCEANEXPLORATION, 150, new Technology[]{updatedShipbuilding, engineering, paper});
        
        
    }
    
    public boolean onTurn() {
        if (isTurnAvailable()) {
            population = 0;
            for (Province province : gemelch.provinces) {
                if (province.owner == this) {
                    province.onTurn();
                    population += province.population;
                }
            }
            
            //подсчет науки
            if (sciencePoints >= neededSciencePoints) {
                research(technologyInProcess);
                sciencePoints -= neededSciencePoints;
                isSomethingResearching = false;
            }
            setPossibleTechnologies();
            return true;
        } else
            HegeLog.log("Country", "Turn is not avaliable");
        return false;
    }
    
    
    public class Technologies {
    
    }
    
    public class Resources {
        public Resource wood;
        public Resource iron;
        public Resource gold;
        public Resource gem;
        public Resource grain;
        public Resource horses;
        public Resource cows;
    }
    
    
    public void research(Technology technology) {
        technologies[technology.id].isResearched = true;
        switch (technology.id) {
            case ENGINEERING:
                mineProduction++;
                break;
            case SIMPLYCHEMISTRY:
                farmProduction++;
                break;
            case APPRENTICESHIP:
                mineProduction++;
                break;
            case EDUCATION:
                libraryProduction += 2;
                break;
                //case Technology.ID.UPDATEDSHIPBUILDING:
                //    updatedShipbuilding.isResearched = true;
                //case Technology.ID.OCEANEXPLORATION:
                //    oceanExploration.isResearched = true;
                //    shipyardProduction += 2;
                //    shipyardFoodProduction += 2;
        }
        HegeLog.log("Country", "Researched " + technologyInProcess.id);
    }
    
    public void setPossibleTechnologies() {
        possibleTechnologies.clear();
        for (Technology technology : technologies) {
            if (checkRequiredTechnologiesForTechnology(technology) && !technology.isResearched) {
                possibleTechnologies.add(technology);
            }
        }
    }
    
    public boolean checkRequiredTechnologiesForBuilding(Building building) {
        switch (building.id) {
            case Building.ID.FARM:
                return true;
            case Building.ID.MINE:
                return true;
            case Building.ID.LIBRARY:
                return paper.isResearched;
            case Building.ID.UNIVERSITY:
                return education.isResearched;
            case Building.ID.WORKSHOP:
                return engineering.isResearched;
            default:
                return true;
        }
    }
    
    public boolean checkRequiredTechnologiesForUnit(WarUnit unit) {
        switch (unit.id) {
            case WarUnit.ID.WARRIOR:
                return true;
            case WarUnit.ID.ARCHER:
                return technologies[ENGINEERING].isResearched;
            case WarUnit.ID.SHIELDMAN:
                return technologies[ENGINEERING].isResearched;
            case WarUnit.ID.CROSSBOWS:
                return technologies[MACHINERY].isResearched;
            case WarUnit.ID.SWORDSMAN:
                return technologies[APPRENTICESHIP].isResearched;
            default:
                return true;
        }
    }
    
    public boolean checkRequiredTechnologiesForTechnology(Technology technology) {
        for (int i = 0; i < technology.requiredTechnologies.length; i++) {
            if (!technology.requiredTechnologies[i].isResearched) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isTurnAvailable() {
        if (isSomethingResearching) {
            for (Province province : gemelch.provinces) {
                if (province.owner == this && !province.isTurnAvailable()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public void chooseTechnology(Technology technology) {
        technologyInProcess = technology;
        neededSciencePoints = technology.cost;
        isSomethingResearching = true;
    }
    
    public class ID {
        public final static int NOTHING = 0;
        public final static int RED = 1;
        public final static int GREEN = 2;
        public final static int BLUE = 3;
        public final static int YELLOW = 4;
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
