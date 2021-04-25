package com.hegemonica.game.logic.unit;

import com.hegemonica.game.logic.country.Province;

class WarUnit {
    public int id;
    public int startBuyCost;
    public float startTurnCost;
    public float startStrength;
    public float startNeededMP; // МП - менпауер
    public int BuyCost;
    public float TurnCost;
    public float Strength;
    public float NeededMP;
    public boolean isSeaUnit;

    public Province province;

    public WarUnit(){
        id = IDs.WARUNIT;
        startBuyCost = 0;
        startTurnCost = 0;
        startStrength = 0;
        startNeededMP = 0;
        isSeaUnit = false;
    }
    public WarUnit (int id, int startBuyCost, float startTurnCost, float startStrength, float startNeededMP, boolean isSeaUnit) {
        this.id = id;
        this.startBuyCost = startBuyCost;
        this.startTurnCost = startTurnCost;
        this.startStrength = startStrength;
        this.startNeededMP = startNeededMP; // МП - менпауер, людские ресурсы для найма юнита
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
