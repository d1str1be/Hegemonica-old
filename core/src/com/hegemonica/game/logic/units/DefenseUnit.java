package com.hegemonica.game.logic.units;

import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.logic.Technology;

public class DefenseUnit extends WarUnit{
    public int id;
    public int cost;
    public int attackStrength;
    public int defenseStrength;

    public int number;
    public Province homeProvince;
    public Province province;

    public Country owner;

    public Technology[] requiredTechnologies;

    public DefenseUnit(int id, int cost, int attackStrength, int defenseStrength, Province homeProvince, int number, Technology[] requiredTechnologies) {
        super(id, cost, attackStrength, defenseStrength, homeProvince, number, requiredTechnologies);
    }
}
