package com.hegemonica.game.logic.buildings;

import com.hegemonica.game.logic.resource.Resource;

public class Building {
    int id;
    boolean canBuild;
    Resource[] neededResources = new Resource[4]{null};
    double startCost;

    public Building(){
        startCost = 0;
        neededResources = [];
        canBuild = false;
    }

}
