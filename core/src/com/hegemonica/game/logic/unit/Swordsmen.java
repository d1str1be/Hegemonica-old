package com.hegemonica.game.logic.unit;


public class Swordsmen extends WarUnit {
    public int startBuyCost = 7;
    public int startTurnCost = 1;
    public int startStrength = 1;
    public int startNeededMP = 1;
    public boolean isSeaUnit = false;

    public Swordsmen(int id, int startBuyCost, double startTurnCost, double startStrength, double startNeededMP, boolean isSeaUnit) {
        super(IDs.SWORDSMEN, 7, 1, 1, 1, false);
    }

    public Swordsmen () {
        this(IDs.SWORDSMEN, startBuyCost, startTurnCost, startStrength, startNeededMP, isSeaUnit);
    }
}
