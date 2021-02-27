package com.hegemonica.game.logic.unit;

public class Crossbows extends WarUnit {
   // public int buyCost = 7;
   // public double turnCost = 1.5;
   // public double strength = 1.5;
    //public double neededMP = 1;
   // public boolean isSeaUnit = false;

    public Crossbows(int id, int buyCost, double turnCost, double strength, double neededMP, boolean isSeaUnit) {
        super(IDs.CROSSBOWS, 7, 1.5, 1, 1, false);
    }

//    public Crossbows() {
//        buyCost = 7;
//        turnCost = 1.5;
//        strength = 1.5;
//        neededMP = 1;
//        isSeaUnit = false;
//        super(IDs.CROSSBOWS, this.buyCost, turnCost, strength, neededMP, this.isSeaUnit);
//
//    }
}
