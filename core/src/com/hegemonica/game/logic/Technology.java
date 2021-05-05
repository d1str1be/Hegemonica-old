package com.hegemonica.game.logic;

/**
 * Класс технологий
 */
public class Technology {
    public boolean isResearched;
    public boolean canBeResearched;

    public double cost;
    public int year;

    public Technology[] requiredTechnologies;

    public void onResearch(Country country) {

    }

    public Technology(double cost, Technology[] requiredTechnologies) {
        this.cost = cost;
        this.requiredTechnologies = requiredTechnologies;
    }

}
