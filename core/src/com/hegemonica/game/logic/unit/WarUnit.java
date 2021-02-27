package com.hegemonica.game.logic.unit;

public class WarUnit {
    public int id;
    public int buyCost;
    public int turnCost;
    public int strength;
    public int neededMP; // МП - менпауер
    public boolean isSeaUnit;

    public static class GemelchUnits { // https://media.discordapp.net/attachments/774236986406862870/780117623575805992/YpJz5_SFXKI.png отсюда добавить
        final static public int ARCHER = 1;
        final static public int CROSSBOW = 2;
        final static public int SWORDMAN = 3;
        final static public int SPEARMAN = 4;
        final static public int HEAVYSWORDMAN = 5;
        final static public int HORSEMAN = 6;
    }
}
