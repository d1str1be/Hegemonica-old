package com.hegemonica.game.logic.buildings;

import com.hegemonica.game.logic.resource.Resource;

public class Building {
    public int id;
<<<<<<< HEAD
    public Resource[] neededResources = new Resource[4]{null};
=======
    public boolean canBuild;
    public Resource[] neededResources = {null};
>>>>>>> ba91c32eca08a09c80d747b9c9b6af7951ef8f63
    double startCost;

    public Building(){
        startCost = 0;
<<<<<<< HEAD
        neededResources = [];
=======
//        neededResources = [];
        canBuild = false;
>>>>>>> ba91c32eca08a09c80d747b9c9b6af7951ef8f63
    }

}
