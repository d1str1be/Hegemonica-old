package com.hegemonica.game.logic.unit;

import com.hegemonica.game.logic.country.Province;

public class Archer extends WarUnit {
    @Override
    public int buyCost = 5;
    public double turnCost = 1;
    public double strength = 1;
    public double neededMP = 1;
    public boolean isSeaUnit = false;
    public Archer () {
        this(id, buyCost, turnCost, strength, neededMP, isSeaUnit);
    }
}
