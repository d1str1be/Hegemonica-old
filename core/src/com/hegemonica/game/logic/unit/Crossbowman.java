package com.hegemonica.game.logic.unit;

public class Crossbowman extends WarUnit {
    @Override
    public int buyCost = 7;
    public double turnCost = 1.5;
    public double strength = 1.5;
    public double neededMP = 1;
    public boolean isSeaUnit = false;
    public Crossbowman () {
        this(id, buyCost, turnCost, strength, neededMP, isSeaUnit);
    }
}
