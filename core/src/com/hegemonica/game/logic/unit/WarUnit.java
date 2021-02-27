package com.hegemonica.game.logic.unit;

class WarUnit {
    public static int id;
    public static int buyCost;
    public static double turnCost;
    public static double strength;
    public static double neededMP; // МП - менпауер
    public static boolean isSeaUnit;

    public WarUnit(){
        id = IDs.WARUNIT;
        buyCost = 0;
        turnCost = 0;
        strength = 0;
        neededMP = 0;
        isSeaUnit = false;
    }
    public WarUnit (int id, int buyCost, double turnCost, double strength, double neededMP, boolean isSeaUnit) {
        this.id = id;
        this.buyCost = buyCost;
        this.turnCost = turnCost;
        this.strength = strength;
        this.neededMP = neededMP; // МП - менпауер, людские ресурсы для найма юнита
        this.isSeaUnit = isSeaUnit;
    }



    protected static class IDs { // https://media.discordapp.net/attachments/774236986406862870/780117623575805992/YpJz5_SFXKI.png отсюда добавить
        final static public int WARUNIT = 0;
        final static public int ARCHERS = 1;
        final static public int CROSSBOWS = 2;
        final static public int SWORDSMEN = 3;
        final static public int SPEARMEN = 4;
        final static public int HEAVYSWORDMEN = 5;
        final static public int HORSEMEN = 6;
    }
}
