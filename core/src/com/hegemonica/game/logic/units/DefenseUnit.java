package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;

public class DefenseUnit extends WarUnit {

    public DefenseUnit(int id, Country owner, int cost, int startAttackStrength, int startDefenseStrength, int movementPoints, Province homeProvince, int number, int upgradeLevel, String name) {
        super(id, owner, cost, startAttackStrength, startDefenseStrength, movementPoints, homeProvince, number, upgradeLevel, name);
    }

    public class UPGRADELEVEL {
        public final static int SHIELDMAN = 0;
    }
}
