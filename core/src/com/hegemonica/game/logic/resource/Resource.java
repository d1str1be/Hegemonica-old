package com.hegemonica.game.logic.resource;

import com.hegemonica.game.logic.country.Province;

public class Resource {
    boolean canObtain;
    boolean isObtained;
    Province province;
    int id;

    public static final int GOLD = 1;

    public Resource(int id) {
        this.id = id;
        canObtain = false;
    }
    public void update(){ // эффект от ресурса на следующий ход
        switch(id){
            // case GOLD:
        }
    }


}
