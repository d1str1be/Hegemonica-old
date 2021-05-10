package com.hegemonica.game.logic;

public class Building {
    public String name;
    public int id;
    public int productionCost;
    public Technology requiredTechnology;
    public Province province;
    public boolean isNeedCity;
    public boolean isLimited;
    public int countOfBuildingInProvince;

    public Building(int id, Province province) {
        this.province = province;
        countOfBuildingInProvince = 0;
        switch (id) {
            case ID.FARM:
                name = "Farm";
                productionCost = PRODUCTIONCOST.FARM;
                isLimited = false;
                isNeedCity = false;
                requiredTechnology = null;
                break;
            case ID.MINE:
                name = "Mine";
                productionCost = PRODUCTIONCOST.MINE;
                isNeedCity = false;
                isLimited = false;
                requiredTechnology = null;
                break;
            case ID.LIBRARY:
                name = "Library";
                productionCost = PRODUCTIONCOST.LIBRARY;
                isNeedCity = true;
                isLimited = true;
                requiredTechnology = province.owner.technologies[Technology.ID.PAPER];
                break;
            case ID.UNIVERSITY:
                name = "University";
                productionCost = PRODUCTIONCOST.UNIVERSITY;
                isNeedCity = true;
                isLimited = true;
                requiredTechnology = province.owner.technologies[Technology.ID.EDUCATION];
                break;
            case ID.WORKSHOP:
                name = "Workshop";
                productionCost = PRODUCTIONCOST.WORKSHOP;
                isNeedCity = true;
                isLimited = true;
                requiredTechnology = province.owner.technologies[Technology.ID.APPRENTICESHIP];
                break;
            //case ID.SHIPYARD:
            //    name = "zsdrfg";
            //    productionCost = PRODUCTIONCOST.SHIPYARD;
            //    isNeedCity = true;
            //    isLimited = true;
            //    requiredTechnology = province.owner.updatedShipbuilding;
            case ID.CITY:
                name = "City";
                productionCost = PRODUCTIONCOST.CITY;
                isNeedCity = false;
                isLimited = true;
                requiredTechnology = null;
                break;
        }
    }

    public static String toString(Building building) {
        return building.name;
    }

    public class ID {
        public final static int FARM = 0;
        public final static int MINE = 1;
        public final static int LIBRARY = 2;
        public final static int UNIVERSITY = 3;
        public final static int WORKSHOP = 4;
        //public final static int SHIPYARD = 5;
        public final static int CITY = 5;
    }

    public class PRODUCTIONCOST {
        public final static int FARM = 8;
        public final static int MINE = 8;
        public final static int LIBRARY = 25;
        public final static int UNIVERSITY = 60;
        public final static int WORKSHOP = 30;
        public final static int SHIPYARD = 40;
        public final static int CITY = 75;
    }

    public Province getProvince() {
        return province;
    }
}
