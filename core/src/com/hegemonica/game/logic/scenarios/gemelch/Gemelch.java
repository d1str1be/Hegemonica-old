package com.hegemonica.game.logic.scenarios.gemelch;

import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.country.Country;
// ЭТО СЦЕНАРИЙ ГЕГЕМОНИИ МЕЛЬХИОРА. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ ИЗ ЭТОЙ ИГРЫ
public class Gemelch {
    public class Technologies{
        public Technology engineering = new Technology(50, new Technology[]{});
        public Technology paper = new Technology(50, new Technology[]{});
        public Technology simplyChemistry = new Technology(50, new Technology[]{});
        public Technology machinery = new Technology(100, new Technology[]{engineering});
        public Technology apprientship = new Technology(100, new Technology[]{engineering, paper});
        public Technology education = new Technology(100, new Technology[]{paper, simplyChemistry});
        public Technology updatedShipbuilding = new Technology(75, new Technology[]{});
        public Technology oceanExploration = new Technology(150, new Technology[]{updatedShipbuilding, engineering, paper});
    }
    public Building library = new Building();
    public Building university = new Building();
    public Building shipyard = new Building();
    public Building workshop = new Building();
    public Building farm = new Building();
    public Building mine = new Building();
}
