package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Province;

public class RangedUnit extends WarUnit {

    public RangedUnit(int id, Province province) {
        super(id, province);
    }
    
    public RangedUnit(int id, Province province, boolean isRendered) {
        super(id, province, isRendered);
    }
    
    @Override
    public void attack(WarUnit unit) {
        unit.defense(this);
        movementPoints = 0;
    }

    public class UPGRADELEVEL {
        public final static int ARCHER = 0;
        public final static int CROSSBOWS = 1;
    }
}
