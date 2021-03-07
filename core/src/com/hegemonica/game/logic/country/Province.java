package com.hegemonica.game.logic.country;

import com.hegemonica.game.logic.buildings.Building;

import java.util.HashMap;

public class Province {
    public String name;

    public int id;

    public double population;
    public double neededFood;

    public Country owner;

    HashMap <Building, int> buidings;

    public int maxBuildings(double population) {
        return (1 + (int)(Math.ceil(population) - 1) / 5); 
    }

    //статус провинции
    public class Status {
    }
    //погода провинции
    public class Climate {
        final public String HOT = "Жаркий";

    }
    //рельеф
    public class Landscape {}
}
