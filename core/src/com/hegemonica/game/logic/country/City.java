package com.hegemonica.game.logic.country;

public class City extends Province {
    public City(int id, String name, int climateType, int landscape, Country owner, int resourceID) {
        super(id, name, climateType, landscape, owner, resourceID);
    }

    @Override
    public void update() {
        super.update();
    }
}
