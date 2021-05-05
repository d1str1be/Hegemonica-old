package com.hegemonica.game.logic.country.persons;

import com.hegemonica.game.logic.Country;

public class Bohema extends Person {
    public Bohema(Country country, String name, int age, int skill, int skillLvl) {
        super(country, name, age, skill, skillLvl);
        country.hasBohema = true;
    }

    @Override
    public void update() {

    }
}
