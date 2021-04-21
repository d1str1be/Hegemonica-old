package com.hegemonica.game.logic.scenarios.gemelch;

import com.hegemonica.game.localization.ProvinceLoc;
import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.buildings.Building;
import com.hegemonica.game.logic.country.Country;
import com.hegemonica.game.logic.country.Province;
import com.hegemonica.game.logic.resource.Resource;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public static final int PROVINCE_COUNT = 6;
    public int turnNumber;
    public Country test;
    public Country test1;
    public Gemelch(){
        onStart();
    }
    public void onStart(){
        turnNumber = 1;
        test = new Country("Test", 0);
        test1 = new Country("Test Testov", 1);


    }
    public void onTurn(){
        test.onTurn();
        turnNumber++;
    }

    public class Provinces {
        public Province levian = new Province(0, ProvinceLoc.Names.Levian.toString(), test, ProvCoords.levianProv, new boolean[]{true, true}, true);
        public Province valinia = new Province(1, ProvinceLoc.Names.Valinia.toString(), test1,  new boolean[]{true, true}, true);
    }
}
