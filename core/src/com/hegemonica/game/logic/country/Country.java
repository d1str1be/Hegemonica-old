package com.hegemonica.game.logic.country;

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


    public static class Ethnoses {
        final static int SVADIANS = 1;
    }

    public static class СultureGroups {
        final static public int WEST = 1;
    }
    public static class Religions {
        final static  int KANON = 1;
    }

    public void update(){ //обновление данных страны при каждом ходе
        if(hasBohema){
        }
    }

}
