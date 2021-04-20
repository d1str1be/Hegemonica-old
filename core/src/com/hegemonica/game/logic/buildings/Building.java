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

    public class ID {
        public final static int FARM = 1;
        public final static int MINE = 2;
        public final static int LIBRARY = 3;
        public final static int UNIVERSITY = 4;
        public final static int WORKSHOP = 5;
        public final static int SHIPYARD = 6;
    }

}
