package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;

public class RangedUnit extends WarUnit {

    public RangedUnit(int id, Country owner, int cost, int startAttackStrength, int startDefenseStrength, int movementPoints, Province homeProvince, int number, int upgradeLevel, String name) {
        super(id, owner, cost, startAttackStrength, startDefenseStrength, movementPoints, homeProvince, number, upgradeLevel, name);
    }

    @Override
    public void attack(WarUnit unit) {
        unit.defense(this);
    }

    public class UPGRADELEVEL {
        public final static int ARCHER = 0;
        public final static int CROSSBOWS = 1;
    }
}
