package com.hegemonica.game.logic;

/**
 * Класс технологий
 */
public class Technology {
    int id;
    public boolean isResearched;
    public boolean canBeResearched;

    public float cost;
    public int year;

    public Technology[] requiredTechnologies;

    public void onResearch(Country country) {

    }

    public Technology(int id, float cost, Technology[] requiredTechnologies) {
        this.cost = cost;
        this.requiredTechnologies = requiredTechnologies;
    }

    class ID {
        public final static int ENGINEERING = 0;
        public final static int PAPER = 1;
        public final static int SIMPLYCHEMISTRY = 2;
        public final static int MACHINERY = 3;
        public final static int APPRIENTICESHIP = 4;
        public final static int EDUCATION = 5;
        public final static int UPDATEDSHIPBUILDING = 6;
        public final static int OCEANEXPLORATION = 7;
    }

}
