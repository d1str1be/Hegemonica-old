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
    public int territorySize;

    public int id;
    public String name;

    public double population;
    public double neededFood;

    public Country owner;

    HashMap <Building, Integer> buidings = new HashMap<Building, Integer>(){
    };

    public Province(int climateType) {

    }
    public int maxBuildings(double population) {
        return (1 + (int)(Math.ceil(population) - 1) / 5);
    }
    //статус провинции
    public class Status {

        //рельеф
        public class Landscape {}
        public void update(){
            switch (climate){

            }
            switch (landscape){

            }
            switch (status){

            }
        }
}




}
