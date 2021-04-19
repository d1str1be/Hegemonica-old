package com.hegemonica.game.logic.scenarios.gemelch;

import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.country.Country;
import com.hegemonica.game.logic.resource.Resource;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
<<<<<<< HEAD

    public class Technologies{
        public Technology engineering = new Technology(50, new Technology[]{});
        public Technology paper = new Technology(50, new Technology[]{});
        public Technology simplyChemistry = new Technology(50, new Technology[]{});
        public Technology machinery = new Technology(100, new Technology[]{engineering});
        public Technology apprientship = new Technology(100, new Technology[]{engineering, paper});
        public Technology education = new Technology(100, new Technology[]{paper, simplyChemistry});
        public Technology updatedShipbuilding = new Technology(75, new Technology[]{});
        public Technology oceanExploration = new Technology(150, new Technology[]{updatedShipbuilding, engineering, paper});
=======
    public static final int COUNT_OF_RESOURCES = 26;
    public class Technologies{
        Technology music = new Technology(2.28, 1500){
            @Override
            public void onResearch(Country country) {
                country.cash++;
            }
        };

    public class Resources {
        public Resource Wood;
        public Resource Iron;
        public Resource Gold;
        public Resource Gem;
        public Resource Grain;
        public Resource Horses;
        public Resource Cows;
    }
>>>>>>> ba91c32eca08a09c80d747b9c9b6af7951ef8f63
    }
    public Building library = new Building();
    public Building university = new Building();
    public Building shipyard = new Building();
    public Building workshop = new Building();
    public Building farm = new Building();
    public Building mine = new Building();
}
