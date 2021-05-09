package com.hegemonica.game.logic.units;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.Technology;

public class WarUnit {
    public int id;
    public int number;
    public String name;
    public Province homeProvince;
    public Province province;
    public Country owner;
    public Technology requiredTechnology;
    public int productionCost;
    
    public int startAttackStrength;
    public int startDefenseStrength;
    public int attackStrength;
    public int defenseStrength;
    public int movementPoints;
    public int upgradeLevel;
    public int health;
    public boolean isHealing;
    
    boolean readyToCapture;
    
    public WarUnitGFX warUnitGFX;
    public boolean isRendered;
    public SpriteBatch batch;
    
    public WarUnit(int id, Country owner, int productionCost, int startAttackStrength, int startDefenseStrength, int movementPoints, Province homeProvince, int number, int upgradeLevel, String name) {
        this.id = id;
        this.owner = owner;
        this.productionCost = productionCost;
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
    
    public WarUnit(int id, Province homeProvince, boolean isRendered) {
        this.id = id;
        this.homeProvince = homeProvince;
        this.owner = homeProvince.owner;
        this.isRendered = isRendered;
        switch (id) {
            case ID.WARRIOR:
                this.name = "Warrior";
                requiredTechnology = null;
                productionCost = PRODUCTIONCOST.WARRIOR;
                break;
            case ID.ARCHER:
                this.name = "Archer";
                requiredTechnology = owner.technologies[Technology.ID.ENGINEERING];
                productionCost = PRODUCTIONCOST.ARCHER;
                break;
            case ID.SHIELDMAN:
                this.name = "Shieldman";
                requiredTechnology = owner.technologies[Technology.ID.ENGINEERING];
                productionCost = PRODUCTIONCOST.SHIELDMAN;
                break;
            case ID.CROSSBOWS:
                this.name = "Crossbows";
                requiredTechnology = owner.technologies[Technology.ID.MACHINERY];
                productionCost = PRODUCTIONCOST.CROSSBOWS;
                break;
            case ID.SWORDSMAN:
                this.name = "Swordsman";
                requiredTechnology = owner.technologies[Technology.ID.APPRENTICESHIP];
                productionCost = PRODUCTIONCOST.SWORDSMAN;
                break;
        }
        setAttackStrength();
        setDefenseStrength();
        if (isRendered) {
            warUnitGFX = new WarUnitGFX(id, homeProvince);
        }
        batch = new SpriteBatch();
    }
    
    public void onTurn() {
        if (isHealing) {
            heal();
        }
        switch (id) {
            case ID.WARRIOR:
                movementPoints = MOVEMENTPOINTS.WARRIOR;
            case ID.ARCHER:
                movementPoints = MOVEMENTPOINTS.ARCHER;
            case ID.SHIELDMAN:
                movementPoints = MOVEMENTPOINTS.SHIELDMAN;
            case ID.CROSSBOWS:
                movementPoints = MOVEMENTPOINTS.CROSSBOWS;
            case ID.SWORDSMAN:
                movementPoints = MOVEMENTPOINTS.SWORDSMAN;
        }
        setAttackStrength();
        setDefenseStrength();
    }
    
    public void setAttackStrength() {
        attackStrength = startAttackStrength - Math.round(COEFFICENTS.STRENGTHHEALTHCOEFFICENT * ((float) health / 100));
    }
    
    public void setDefenseStrength() {
        defenseStrength = startDefenseStrength - Math.round(COEFFICENTS.STRENGTHHEALTHCOEFFICENT * ((float) health / 100));
    }
    
    public void heal() {
        health += 20;
    }
    
    public void move(Province province) {
        this.province.unitThere = null;
        province.unitThere = this;
        this.province = province;
        movementPoints--;
    }
    
    public void capture() {
        province.setOwner(this.owner);
        movementPoints = 0;
    }
    
    public void attack(WarUnit unit) {
        unit.defense(this);
        if (unit.health <= 0) {
            this.move(unit.province);
            setAttackStrength();
            setDefenseStrength();
        } else {
            health -= Math.round(30 * Math.pow(2.72, (unit.defenseStrength - attackStrength) / 25));
            setAttackStrength();
            setDefenseStrength();
            movementPoints = 0;
        }
        if (health <= 0) {
            destroy();
        }
    }
    
    public void defense(WarUnit unit) {
        health -= Math.round(30 * Math.pow(2.72, (unit.attackStrength - defenseStrength) / 25f));
        setAttackStrength();
        setDefenseStrength();
    }
    
    public void upgrade() {
        upgradeLevel++;
        movementPoints = 0;
        switch (id) {
            case ID.WARRIOR:
                id = ID.SWORDSMAN;
                startAttackStrength = ATTACKSTRENGTH.SWORDSMAN;
                startDefenseStrength = DEFENSESTRENGTH.SWORDSMAN;
                break;
            case ID.ARCHER:
                id = ID.CROSSBOWS;
                startAttackStrength = ATTACKSTRENGTH.CROSSBOWS;
                startDefenseStrength = DEFENSESTRENGTH.CROSSBOWS;
        }
    }
    
    public void destroy() {
        province.unitThere = null;
        province.createdUnits.set(number, null);
    }
    
    
    public class ID { // https://media.discordapp.net/attachments/774236986406862870/780117623575805992/YpJz5_SFXKI.png отсюда добавить
        public final static int WARRIOR = 0;
        public final static int ARCHER = 1;
        public final static int SHIELDMAN = 2;
        public final static int CROSSBOWS = 3;
        public final static int SWORDSMAN = 4;
    }
    
    public class PRODUCTIONCOST {
        public final static int WARRIOR = 10;
        public final static int ARCHER = 10;
        public final static int SHIELDMAN = 10;
        public final static int CROSSBOWS = 10;
        public final static int SWORDSMAN = 10;
    }
    
    public class UPGRADECOST {
        public final static int CROSSBOWS = 5;
        public final static int SWORDSMAN = 5;
    }
    
    public class ATTACKSTRENGTH {
        public final static int WARRIOR = 10;
        public final static int ARCHER = 10;
        public final static int SHIELDMAN = 10;
        public final static int CROSSBOWS = 10;
        public final static int SWORDSMAN = 10;
    }
    
    public class DEFENSESTRENGTH {
        public final static int WARRIOR = 10;
        public final static int ARCHER = 10;
        public final static int SHIELDMAN = 10;
        public final static int CROSSBOWS = 10;
        public final static int SWORDSMAN = 10;
    }
    
    public class MOVEMENTPOINTS {
        public final static int WARRIOR = 1;
        public final static int ARCHER = 1;
        public final static int SHIELDMAN = 1;
        public final static int CROSSBOWS = 1;
        public final static int SWORDSMAN = 1;
    }
    
    public class COEFFICENTS {
        public final static int STRENGTHHEALTHCOEFFICENT = 10;
    }
    
    public class ACTIONID {
        public final static int ATTACK = 0;
        public final static int CAPTURE = 1;
    }
    
    
    public void render(OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        warUnitGFX.getSprite().draw(batch);
        batch.end();
    }
    
}
