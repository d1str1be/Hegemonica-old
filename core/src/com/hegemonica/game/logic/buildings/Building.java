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
    public boolean isNeedCity;
    public boolean isLimited;
    public int countOfBuildingInProvince;
    public Building(int id, Province province){
        this.province = province;
        countOfBuildingInProvince = 0;
        switch (id) {
            case ID.FARM:
                productionCost = PRODUCTIONCOST.FARM;
                isLimited = false;
                isNeedCity = false;
                requiredTechnology = null;
            case ID.MINE:
                productionCost = PRODUCTIONCOST.MINE;
                isNeedCity = false;
                isLimited = false;
                requiredTechnology = null;
            case ID.LIBRARY:
                productionCost = PRODUCTIONCOST.LIBRARY;
                isNeedCity = true;
                isLimited = true;
                requiredTechnology = province.owner.technologies.paper;
            case ID.UNIVERSITY:
                productionCost = PRODUCTIONCOST.UNIVERSITY;
                isNeedCity = true;
                isLimited = true;
                requiredTechnology = province.owner.technologies.education;
            case ID.WORKSHOP:
                productionCost = PRODUCTIONCOST.WORKSHOP;
                isNeedCity = true;
                isLimited = true;
                requiredTechnology = province.owner.technologies.engineering;
            case ID.SHIPYARD:
                productionCost = PRODUCTIONCOST.SHIPYARD;
                isNeedCity = true;
                isLimited = true;
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

    public class PRODUCTIONCOST {
        public final static int FARM = 15;
        public final static int MINE = 15;
        public final static int LIBRARY = 50;
        public final static int UNIVERSITY = 120;
        public final static int WORKSHOP = 60;
        public final static int SHIPYARD = 40;
    }

    public Province getProvince() {
        return province;
    }
}
