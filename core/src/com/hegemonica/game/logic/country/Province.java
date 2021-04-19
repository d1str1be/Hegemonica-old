package com.hegemonica.game.logic.country;

import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.resource.Resource;

import java.util.HashMap;

public class Province {

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
    public int climate;
    public int landscape;
    public int status;

    public int sizeLimit;
    public int sizeUsed;

    public Resource resource;

    public int id;
    public String name;

    public double population;
    public double neededFood;

    public Country owner;

    HashMap <Building, Integer> buildings = new HashMap<Building, Integer>(){
    };

<<<<<<< HEAD
    //public int maxBuildings(double population) {
    //    return (1 + (int)(Math.ceil(population) - 1) / 5);
    //}
=======
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
>>>>>>> ba91c32eca08a09c80d747b9c9b6af7951ef8f63

        }
        switch (landscape){

        }
        switch (status){

        }
    }
    //статус провинции
    public class Status {

        //рельеф
        public class Landscape {}

    }
<<<<<<< HEAD
    //рельеф
    public class Landscape {}

    public Province () {
        
    }
=======

>>>>>>> ba91c32eca08a09c80d747b9c9b6af7951ef8f63
}
