package com.hegemonica.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.logic.units.WarUnit;

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
    
    public int population;
    public int sciencePoints;
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
    
    
    public Technology[] technologies;
    public ArrayList<Technology> possibleTechnologies;
    public Technology engineering;
    public Technology paper;
    public Technology simplyChemistry;
    public Technology machinery;
    public Technology apprienticeship;
    public Technology education;
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
        workshopProduction = 5;
        shipyardProduction = 3;
        
        startFoodProduction = 2;
        farmProduction = 2;
        shipyardProduction = 2;
        
        citizenScienceProduction = 1;
        libraryProduction = 3;
        universityProduction = 6;
        
        technologies = new Technology[6];
        
        engineering = new Technology(Technology.ID.ENGINEERING, 50, new Technology[]{});
        paper = new Technology(Technology.ID.PAPER, 50, new Technology[]{});
        simplyChemistry = new Technology(Technology.ID.SIMPLYCHEMISTRY, 50, new Technology[]{});
        machinery = new Technology(Technology.ID.MACHINERY, 50, new Technology[]{engineering});
        apprienticeship = new Technology(Technology.ID.APPRENTICESHIP, 100, new Technology[]{engineering, paper});
        education = new Technology(Technology.ID.EDUCATION, 100, new Technology[]{paper, simplyChemistry});
        
        technologies[0] = engineering;
        technologies[1] = paper;
        technologies[2] = simplyChemistry;
        technologies[3] = machinery;
        technologies[4] = apprienticeship;
        technologies[5] = education;
        isSomethingResearching = false;
        
        possibleTechnologies = new ArrayList<>();
        possibleTechnologies.add(engineering);
        possibleTechnologies.add(paper);
        possibleTechnologies.add(simplyChemistry);
        //updatedShipbuilding = new Technology(Technology.ID.UPDATEDSHIPBUILDING, 75, new Technology[]{});
        //oceanExploration = new Technology(Technology.ID.OCEANEXPLORATION, 150, new Technology[]{updatedShipbuilding, engineering, paper});
        
        technologyInProcess = engineering;
        neededSciencePoints = technologyInProcess.cost;
        
    }
    
    public boolean onTurn() {
        population = 1;
        if (isTurnAvailable()) {
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
        switch (technology.id) {
            case Technology.ID.ENGINEERING:
                engineering.isResearched = true;
                mineProduction++;
            case Technology.ID.PAPER:
                paper.isResearched = true;
            case Technology.ID.SIMPLYCHEMISTRY:
                simplyChemistry.isResearched = true;
                farmProduction++;
            case Technology.ID.MACHINERY:
                machinery.isResearched = true;
            case Technology.ID.APPRENTICESHIP:
                apprienticeship.isResearched = true;
                mineProduction++;
            case Technology.ID.EDUCATION:
                education.isResearched = true;
                libraryProduction++;
                //case Technology.ID.UPDATEDSHIPBUILDING:
                //    updatedShipbuilding.isResearched = true;
                //case Technology.ID.OCEANEXPLORATION:
                //    oceanExploration.isResearched = true;
                //    shipyardProduction += 2;
                //    shipyardFoodProduction += 2;
        }
        HegeLog.log("Country", "Researched " + technologyInProcess.id);
        technologyInProcess = engineering;
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
                return engineering.isResearched;
            case WarUnit.ID.SHIELDMAN:
                return engineering.isResearched;
            case WarUnit.ID.CROSSBOWS:
                return machinery.isResearched;
            case WarUnit.ID.SWORDSMAN:
                return apprienticeship.isResearched;
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
