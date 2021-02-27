package com.hegemonica.game.logic.country.persons;

import com.hegemonica.game.logic.country.Country;

public class Person {
    Country country;
    public String name;
    public int age;
    public int skill; //перк
    public int skillLvl; //lvl of perk

    public Person(Country country, String name, int age, int skill, int skillLvl) {
        this.country = country;
        this.name = name;
        this.age = age;
        this.skill = skill;
        this.skillLvl = skillLvl;
    }
    public static class IDs {
        final static public int BOHEMA = 1;
    }
    public void update(){
    }
}
