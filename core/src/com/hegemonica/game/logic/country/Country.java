package com.hegemonica.game.logic.country;

import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.scenarios.gemelch.Gemelch;

import java.util.HashMap;

public class Country {
    public String name;
    public int id;
    public int cash;
    public int size;
    public int prestige;
    public int stability;
    public int maxStability;
    public int inflation;
    public boolean hasCrisis;
    public boolean inWar;

    //наличие персонажа в стране
    public boolean hasBohema;
    public boolean hasSeaman;
    public boolean hasMinister;
    public boolean hasBusinessman;
    public boolean hasRevoltman;
    public boolean hasGeneral;

    //HashMap resuorces<Resource, double>;

    public Gemelch gemelch;

    public Country (String name, ) {

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
