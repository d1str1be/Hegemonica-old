package com.hegemonica.game.logic.buildings;

import com.hegemonica.game.logic.resource.Resource;

public class Building {
    public int id;
    public Resource[] neededResources = new Resource[4];
    public boolean canBuild;
    double startCost;

    public Building(){
        startCost = 0;
//        neededResources = [];
        canBuild = false;
    }

}
