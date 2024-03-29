package com.hegemonica.game.logic.units;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hegemonica.game.HegeLog;
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
    public static final int maxHealth = 100;
    public boolean isHealing;
    
    boolean isReadyToCapture;
    
    public WarUnitGFX warUnitGFX;
    public boolean isRendered;
    public SpriteBatch batch;
    
    public float xCoords;
    public float yCoords;
    
    public WarUnit(int id, Province province) {
        this(id, province, false);
    }
    
    public WarUnit(int id, Province province, boolean isRendered) {
        this.id = id;
        owner = province.owner;
        this.homeProvince = province;
        this.province = homeProvince;
        health = maxHealth;
        switch (id) {
            case ID.WARRIOR:
                productionCost = PRODUCTIONCOST.WARRIOR;
                startAttackStrength = ATTACKSTRENGTH.WARRIOR;
                startDefenseStrength = DEFENSESTRENGTH.WARRIOR;
                movementPoints = MOVEMENTPOINTS.WARRIOR;
                upgradeLevel = MeleeUnit.UPGRADELEVEL.WARRIOR;
                name = "Warrior";
                break;
            case ID.ARCHER:
                productionCost = PRODUCTIONCOST.ARCHER;
                startAttackStrength = ATTACKSTRENGTH.ARCHER;
                startDefenseStrength = DEFENSESTRENGTH.ARCHER;
                movementPoints = MOVEMENTPOINTS.ARCHER;
                upgradeLevel = RangedUnit.UPGRADELEVEL.ARCHER;
                name = "Archer";
                break;
            case ID.SHIELDMAN:
                productionCost = PRODUCTIONCOST.SHIELDMAN;
                startAttackStrength = ATTACKSTRENGTH.SHIELDMAN;
                startDefenseStrength = DEFENSESTRENGTH.SHIELDMAN;
                movementPoints = MOVEMENTPOINTS.SHIELDMAN;
                upgradeLevel = DefenseUnit.UPGRADELEVEL.SHIELDMAN;
                name = "Shieldman";
                break;
            case ID.CROSSBOWS:
                productionCost = PRODUCTIONCOST.CROSSBOWS;
                startAttackStrength = ATTACKSTRENGTH.CROSSBOWS;
                startDefenseStrength = DEFENSESTRENGTH.CROSSBOWS;
                movementPoints = MOVEMENTPOINTS.CROSSBOWS;
                upgradeLevel = RangedUnit.UPGRADELEVEL.CROSSBOWS;
                name = "Crossbows";
                break;
            case ID.SWORDSMAN:
                productionCost = PRODUCTIONCOST.SWORDSMAN;
                startAttackStrength = ATTACKSTRENGTH.SWORDSMAN;
                startDefenseStrength = DEFENSESTRENGTH.SWORDSMAN;
                movementPoints = MOVEMENTPOINTS.SWORDSMAN;
                upgradeLevel = MeleeUnit.UPGRADELEVEL.SWORDSMAN;
                name = "Swordsman";
                break;
        }
        
        setAttackStrength();
        setDefenseStrength();
        if (isRendered) {
            warUnitGFX = new WarUnitGFX(id, province);
            warUnitGFX.update(this);
        }
        batch = new SpriteBatch();
    }
    
    public void onTurn() {
        if (isHealing) {
            heal();
            warUnitGFX.setHealth(this);
            warUnitGFX.update(this);
        }
        switch (id) {
            case ID.WARRIOR:
                movementPoints = MOVEMENTPOINTS.WARRIOR;
                break;
            case ID.ARCHER:
                movementPoints = MOVEMENTPOINTS.ARCHER;
                break;
            case ID.SHIELDMAN:
                movementPoints = MOVEMENTPOINTS.SHIELDMAN;
                break;
            case ID.CROSSBOWS:
                movementPoints = MOVEMENTPOINTS.CROSSBOWS;
                break;
            case ID.SWORDSMAN:
                movementPoints = MOVEMENTPOINTS.SWORDSMAN;
                break;
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
        warUnitGFX.update(this);
    }
    
    public void move(Province province) {
        this.province.unitThere = null;
        province.unitThere = this;
        this.province = province;
        movementPoints--;
        capture(province);
        warUnitGFX.update(this);
        HegeLog.log("WarUnit", "Unit in " + province.name + " has " + movementPoints + " movementPoints now");
    }
    
    public void capture(Province province) {
        if (province.owner.id == Country.ID.NOTHING) {
            province.manualInitialization(false);
            province.setOwner(this.owner);
        }
        province.setOwner(this.owner);
        movementPoints = 0;
        HegeLog.log("WarUnit", "Unit " + name + " of country " + owner.name + " captured " + this.province.name);
        warUnitGFX.update(this);
    }
    
    public void attack(WarUnit unit) {
        int attackStrengthThis = attackStrength;
        int defenseStrengthThis = defenseStrength;
        int attackStrengthOther = unit.attackStrength;
        int defenseStrengthOther = unit.defenseStrength;
        unit.defense(this);
        if (unit.health <= 0) {
            this.move(unit.province);
            isReadyToCapture = true;
            setAttackStrength();
            setDefenseStrength();
        } else {
            health -= Math.round(30 * Math.pow(2.72, (defenseStrengthOther - attackStrengthThis) / 25f));
            setAttackStrength();
            setDefenseStrength();
            movementPoints = 0;
        }
        if (health <= 0) {
            destroy();
        }
        warUnitGFX.update(this);
    }
    
    public void defense(WarUnit unit) {
        int attackStrengthThis = attackStrength;
        int defenseStrengthThis = defenseStrength;
        int attackStrengthOther = unit.attackStrength;
        int defenseStrengthOther = unit.defenseStrength;
        health -= Math.round(30 * Math.pow(2.72, (attackStrengthOther - defenseStrengthThis) / 25f));
        setAttackStrength();
        setDefenseStrength();
        warUnitGFX.update(this);
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
        if (!province.createdUnits.isEmpty())
            province.createdUnits.set(number, null);
    }
    
    public boolean isMovableToProvince(Province province) {
        if (movementPoints <= 0) {
            HegeLog.log("WarUnit move", "No movement points");
            return false;
        } else if (province.unitThere != null) {
            if (owner == province.unitThere.owner) {
                HegeLog.log("WarUnit move", "You are trying to attack your own unit");
                return false;
            }
        }
        return this.province.isNeighbor(province);
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
        warUnitGFX.render(camera);
    }
    
    
    public static String toString(WarUnit unit){
        if(unit==null){
            return "null";
        }
        else
            return unit.name + ", " + unit.province.name;
    }
    
}
