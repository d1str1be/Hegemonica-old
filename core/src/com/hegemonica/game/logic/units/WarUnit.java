package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.Technology;

class WarUnit {
    public int id;
    public int cost;
    public int attackStrength;
    public int defenseStrength;

    public int number;
    public Province homeProvince;
    public Province province;

    public Country owner;

    public Technology[] requiredTechnologies;

    public WarUnit(int id, int cost, int attackStrength, int defenseStrength, Province homeProvince, int number, Technology[] requiredTechnologies) {
        this.id = id;
        this.cost = cost;
        this.attackStrength = attackStrength;
        this.defenseStrength = defenseStrength;
        this.homeProvince = homeProvince;
        this.province = homeProvince;
        this.number = number;
        this.requiredTechnologies = requiredTechnologies;
    }


    class ID { // https://media.discordapp.net/attachments/774236986406862870/780117623575805992/YpJz5_SFXKI.png отсюда добавить
        public final static int WARRIOR = 0;
        public final static int ARCHER = 1;
        public final static int SHIELDER = 2;
        public final static int CROSSBOWS = 3;
        public final static int SWORDSMAN = 4;
    }
}
