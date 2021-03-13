package com.hegemonica.game.logic.scenarios.gemelch;

import com.hegemonica.game.logic.Technology;
import com.hegemonica.game.logic.country.Country;
// ЭТО СЦЕНАРИЙ ГЕГЕМОНИИ МЕЛЬХИОРА. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ ИЗ ЭТОЙ ИГРЫ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public class Technologies{
        Technology music = new Technology(2.28, 1500){
            @Override
            public void onResearch(Country country) {
                country.cash++;
            }
        };
    }
}
