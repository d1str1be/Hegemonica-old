package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.Technology;

public class WarUnit {
    public int id;
    public int cost;
    public int attackStrength;
    public int defenseStrength;
    public int movementPoints;
    public int upgradeLevel;
    public int health;

    public String name;

    public int number;
    public Province homeProvince;
    public Province province;

    public Country owner;

    public WarUnit(int id, Country owner, int cost, int attackStrength, int defenseStrength, int movementPoints, Province homeProvince, int number, int upgradeLevel, String name) {
        this.id = id;
        this.owner = owner;
        this.cost = cost;
        this.attackStrength = attackStrength;
        this.defenseStrength = defenseStrength;
        this.movementPoints = movementPoints;
        this.homeProvince = homeProvince;
        this.province = homeProvince;
        this.number = number;
        this.upgradeLevel = upgradeLevel;
        this.name = name;
    }


    public class ID { // https://media.discordapp.net/attachments/774236986406862870/780117623575805992/YpJz5_SFXKI.png отсюда добавить
        public final static int WARRIOR = 0;
        public final static int ARCHER = 1;
        public final static int SHIELDER = 2;
        public final static int CROSSBOWS = 3;
        public final static int SWORDSMAN = 4;
    }

    public class COST {
        public final static int WARRIOR = 10;
        public final static int ARCHER = 10;
        public final static int SHIELDER = 10;
        public final static int CROSSBOWS = 10;
        public final static int SWORDSMAN = 10;
    }

    public class ATTACKSTRENGTH {
        public final static int WARRIOR = 10;
        public final static int ARCHER = 10;
        public final static int SHIELDER = 10;
        public final static int CROSSBOWS = 10;
        public final static int SWORDSMAN = 10;
    }

    public class DEFENSESTRENGTH {
        public final static int WARRIOR = 10;
        public final static int ARCHER = 10;
        public final static int SHIELDER = 10;
        public final static int CROSSBOWS = 10;
        public final static int SWORDSMAN = 10;
    }

    public class MOVEMENTPOINTS {
        public final static int WARRIOR = 1;
        public final static int ARCHER = 1;
        public final static int SHIELDER = 1;
        public final static int CROSSBOWS = 1;
        public final static int SWORDSMAN = 1;
    }

}
