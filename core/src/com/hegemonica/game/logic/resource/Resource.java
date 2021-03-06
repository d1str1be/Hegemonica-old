package com.hegemonica.game.logic.resource;

import com.hegemonica.game.logic.country.Province;

public class Resource {
    boolean canObtain;
    boolean isObtained;
    Province province;

    public Resource() {
        canObtain = false;
    }

    public class Iron {}
    public class Gems {}
    public class Grain {}
    
}
