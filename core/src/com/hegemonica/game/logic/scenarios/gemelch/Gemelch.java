package com.hegemonica.game.logic.scenarios.gemelch;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hegemonica.game.Core;
import com.hegemonica.game.logic.country.Country;
import com.hegemonica.game.logic.country.Province;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    Core game;
    public static final int COUNT_OF_RESOURCES = 26;
    public static final int PROVINCE_COUNT = 6;
    public int turnNumber;
    public Country test;
    public Country test1;

    public Province[] provinces;

    public Gemelch(Core game) {
        this.game = game;
        onStart();
    }

    public void onStart() {
        turnNumber = 1;
        test = new Country("Test1", 0);
        test1 = new Country("Test2", 1);

        provinces = new Province[25];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int iterator = i * 5 + j;
                provinces[iterator] = new Province(iterator, "Province " + iterator, test, new boolean[]{true}, false, 50 * j, 50 * i, 50, 50);
            }
        }
    }

    public void onTurn() {
        test.onTurn();
        test1.onTurn();
        turnNumber++;
    }

    //    public class Provinces {
//        public Province levian;
//        public Province valinia;
//
//    }
    public void render(OrthographicCamera camera) {
        for (Province x : provinces) {
            x.render(camera);
        }
    }

    /**
     * Returns selected point. Should be used in tap() method of GestureListener
     *
     * @return Selected province
     */

    public Province whichPolygonContainsPoint(float x, float y) {
        for (int i = 0; i < provinces.length; i++) {
            if (provinces[i].contains(x, y))
                return provinces[i];
        }
        return null;
    }
}
