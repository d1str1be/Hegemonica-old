package com.hegemonica.game.logic;

public class Technologies {
    public Technology engineering = new Technology(0, 50, new Technology[]{});
    public Technology paper = new Technology(1, 50, new Technology[]{});
    public Technology simplyChemistry = new Technology(2, 50, new Technology[]{});
    public Technology machinery = new Technology(3, 50, new Technology[]{engineering});
    public Technology apprientship = new Technology(4, 100, new Technology[]{engineering, paper});
    public Technology education = new Technology(5, 100, new Technology[]{paper, simplyChemistry});
    //public Technology updatedShipbuilding = new Technology(6, 75, new Technology[]{});
    //public Technology oceanExploration = new Technology(7, 150, new Technology[]{updatedShipbuilding, engineering, paper});

    public Technologies() {

    }

}
