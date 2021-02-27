package com.hegemonica.game.logic.unit;

import com.hegemonica.game.logic.country.Province;

public class WarUnit {
    public int id;
    public int buyCost;
    public double turnCost;
    public double strength;
    public double neededMP; // МП - менпауер
    public boolean isSeaUnit;
    public Province location;

    public static WarUnit (int id, int buyCost, double turnCost, double strength, double neededMP, boolean isSeaUnit) {
        this.id = id;
        this.buyCost = buyCost;
        this.turnCost = turnCost;
        this.strength = strength;
        this.neededMP = neededMP; // МП - менпауер
        this.isSeaUnit = isSeaUnit;
    }



    public static class GemelchUnits { // https://media.discordapp.net/attachments/774236986406862870/780117623575805992/YpJz5_SFXKI.png отсюда добавить
        final static public int WARUNIT = 0;
        final static public int ARCHER = 1;
        final static public int CROSSBOW = 2;
        final static public int SWORDMAN = 3;
        final static public int SPEARMAN = 4;
        final static public int HEAVYSWORDMAN = 5;
        final static public int HORSEMAN = 6;
    }
}
