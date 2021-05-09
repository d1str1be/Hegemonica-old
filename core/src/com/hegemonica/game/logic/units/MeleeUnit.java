package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Province;

public class MeleeUnit extends WarUnit {
    
    public MeleeUnit(int id, Province province, boolean isRendered) {
        super(id, province, isRendered);
    }
    
    public MeleeUnit(int id, Province province) {
        super(id, province);
    }

    public class UPGRADELEVEL {
        public final static int WARRIOR = 0;
        public final static int SWORDSMAN = 1;
    }
}
