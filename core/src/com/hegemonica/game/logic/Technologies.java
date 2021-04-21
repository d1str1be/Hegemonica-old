package com.hegemonica.game.logic;

public class Technologies {
    public Technology engineering = new Technology(50, new Technology[]{});
    public Technology paper = new Technology(50, new Technology[]{});
    public Technology simplyChemistry = new Technology(50, new Technology[]{});
    public Technology machinery = new Technology(100, new Technology[]{engineering});
    public Technology apprientship = new Technology(100, new Technology[]{engineering, paper});
    public Technology education = new Technology(100, new Technology[]{paper, simplyChemistry});
    public Technology updatedShipbuilding = new Technology(75, new Technology[]{});
    public Technology oceanExploration = new Technology(150, new Technology[]{updatedShipbuilding, engineering, paper});

    public Technologies(){

    }
}
