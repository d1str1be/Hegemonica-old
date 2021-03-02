package com.hegemonica.game.logic.unit;


public class Archer extends WarUnit {
    public int startBuyCost = 5;
    public double startTurnCost = 1;
    public double startStrength = 1;
    public double startNeededMP = 1;
    public boolean isSeaUnit = false;

    public Archer () {
        super(IDs.ARCHERS, 5, 1, 1, 1, false);
    }

    public Archer(int id, int startBuyCost, double startTurnCost, double startStrength, double startNeededMP, boolean isSeaUnit) { }
}
