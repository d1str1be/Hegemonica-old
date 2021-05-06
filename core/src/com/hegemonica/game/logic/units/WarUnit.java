package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;

public class WarUnit {
    public int id;
    public int cost;
    public int startAttackStrength;
    public int startDefenseStrength;
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

    public WarUnit(int id, Country owner, int cost, int startAttackStrength, int startDefenseStrength, int movementPoints, Province homeProvince, int number, int upgradeLevel, String name) {
        this.id = id;
        this.owner = owner;
        this.cost = cost;
        this.startAttackStrength = startAttackStrength;
        this.startDefenseStrength = startDefenseStrength;
        this.attackStrength = startAttackStrength;
        this.defenseStrength = startDefenseStrength;
        this.movementPoints = movementPoints;
        this.homeProvince = homeProvince;
        this.province = homeProvince;
        this.number = number;
        this.upgradeLevel = upgradeLevel;
        this.name = name;
    }

    public void onTurn() {
        switch (id) {
            case ID.WARRIOR:
                movementPoints = MOVEMENTPOINTS.WARRIOR;
            case ID.ARCHER:
                movementPoints = MOVEMENTPOINTS.ARCHER;
            case ID.SHIELDER:
                movementPoints = MOVEMENTPOINTS.SHIELDER;
            case ID.CROSSBOWS:
                movementPoints = MOVEMENTPOINTS.CROSSBOWS;
            case ID.SWORDSMAN:
                movementPoints = MOVEMENTPOINTS.SWORDSMAN;
        }
    }

    public void setAttackStrength() {
        attackStrength = startAttackStrength - Math.round(COEFFICENTS.STRENGTHHEALTHCOEFFICENT * ((float) health / 100));
    }

    public void setDefenseStrength() {
        defenseStrength = startDefenseStrength - Math.round(COEFFICENTS.STRENGTHHEALTHCOEFFICENT * ((float) health / 100));
    }

    public void move(Province province) {
        this.province.unitThere = null;
        province.unitThere = this;
        this.province = province;
        movementPoints--;
    }

    public void capture() {
        province.owner = this.owner;
        movementPoints = 0;
    }

    public void attack(WarUnit unit) {
        unit.defense(this);
        if (unit.health <= 0) {
            unit.destroy();
            this.move(unit.province);
            setAttackStrength();
            setDefenseStrength();
        } else if (health <= 0) {
            unit.defense(this);
            destroy();
            unit.setAttackStrength();
            unit.setDefenseStrength();
        } else {
            health -= Math.round(30 * Math.pow(2.72, (unit.defenseStrength - attackStrength) / 25));
            unit.defense(this);
            setAttackStrength();
            setDefenseStrength();
            unit.setAttackStrength();
            unit.setDefenseStrength();
        }
    }

    public void defense(WarUnit unit) {
        health -= Math.round(30 * Math.pow(2.72, (unit.attackStrength - defenseStrength) / 25));
    }

    public void destroy() {
        province.unitThere = null;
        province.units.set(number, null);
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

    public class COEFFICENTS {
        public final static int STRENGTHHEALTHCOEFFICENT = 5;
    }

}
