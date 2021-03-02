package com.hegemonica.game.logic.unit;


public class Archer extends WarUnit {
    public int buyCost = 5;
    public double turnCost = 1;
    public double strength = 1;
    public double neededMP = 1;
    public boolean isSeaUnit = false;
    public Archer () {
        super(IDs.ARCHERS, 5, 1, 1, 1, false);
    }

    public Archer(int id, int buyCost, double turnCost, double strength, double neededMP, boolean isSeaUnit) { }
}
