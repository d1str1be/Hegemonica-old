package com.hegemonica.game.logic.buildings;

import com.hegemonica.game.logic.resource.Resource;

public class Building {
    public int id;
    public boolean canBuild;
    public Resource[] neededResources = {null};
    double startCost;

    public Building(){
        startCost = 0;
//        neededResources = [];
        canBuild = false;
    }

}
