package com.hegemonica.game.logic.units;

public class Crossbows extends WarUnit {
    public int startBuyCost = 7;
    public double startTurnCost = 1.5;
    public double startStrength = 1.5;
    public double startNeededMP = 1;
    public boolean isSeaUnit = false;

    public Crossbows() {
//        super(IDs.CROSSBOWS, 7, 1.5, 1, 1, false);
    }

    public Crossbows(int id, int startBuyCost, double startTurnCost, double startStrength, double startNeededMP, boolean isSeaUnit) {
//        super(IDs.CROSSBOWS, 7, 1.5, 1, 1, false);
    }
}
