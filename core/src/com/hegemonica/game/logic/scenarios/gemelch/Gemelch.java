package com.hegemonica.game.logic.scenarios.gemelch;

import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.country.Country;
import com.hegemonica.game.logic.resource.Resource;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public static final int PROVINCE_COUNT = 6;
    public int turnNumber;
    public Country test;
    public Gemelch(){
        onStart();
    }
    public void onStart(){
        turnNumber = 1;
        test = new Country("Test", 1);


    }
    public void onTurn(){
        test.onTurn();
        turnNumber++;
    }
}
