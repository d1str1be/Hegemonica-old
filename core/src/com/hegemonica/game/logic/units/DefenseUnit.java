package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Province;

public class DefenseUnit extends WarUnit {

    public DefenseUnit(int id, Province province) {
        super(id, province);
    }

    public class UPGRADELEVEL {
        public final static int SHIELDMAN = 0;
    }
}
