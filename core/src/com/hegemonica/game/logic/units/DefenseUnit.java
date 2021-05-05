package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.Technology;

public class DefenseUnit extends WarUnit{

    public DefenseUnit(int id, Country owner, int cost, int attackStrength, int defenseStrength, int movementPoints, Province homeProvince, int number, int upgradeLevel, String name) {
        super(id, owner, cost, attackStrength, defenseStrength, movementPoints, homeProvince, number, upgradeLevel, name);
    }

    public class UPGRADELEVEL {
        public final static int SHIELDER = 0;
    }
}
