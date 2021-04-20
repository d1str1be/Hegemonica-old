package com.hegemonica.game.logic.buildings;

import com.hegemonica.game.logic.Technologies;
import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.country.Province;
import com.hegemonica.game.logic.resource.Resource;

public class Building {
    public int id;
    public int productionCost;
    public Technology requiredTechnology;
    public Province province;

    public Building(int id){
        switch (id) {
            case ID.FARM:
                productionCost = 15;
                requiredTechnology = null;
            case ID.MINE:
                productionCost = 15;
                requiredTechnology = null;
            case ID.LIBRARY:
                productionCost = 50;
                requiredTechnology = province.owner.technologies.paper;
            case ID.UNIVERSITY:
                productionCost = 120;
                requiredTechnology = province.owner.technologies.education;
            case ID.WORKSHOP:
                productionCost = 60;
                requiredTechnology = province.owner.technologies.engineering;
            case ID.SHIPYARD:
                productionCost = 40;
                requiredTechnology = province.owner.technologies.updatedShipbuilding;
        }
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
