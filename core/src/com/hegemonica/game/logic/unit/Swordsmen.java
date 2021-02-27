package com.hegemonica.game.logic.unit;

public class Swordsmen extends WarUnit {
    public int buyCost = 7;
    public double turnCost = 1;
    public double strength = 1;
    public double neededMP = 1;
    public boolean isSeaUnit = false;
    public Swordsmen () {
        this(id, buyCost, turnCost, strength, neededMP, isSeaUnit);
    }
}
