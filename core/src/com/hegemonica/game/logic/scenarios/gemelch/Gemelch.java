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
    public int mapHeight;
    public int mapWidth;
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

    public int[] getNeighborsIdList(int id) {

        //угловые

        if (id == 0) {
            int[] neighbors = new int[4];
            neighbors[0] = 0;
            neighbors[1] = 1;
            neighbors[2] = mapWidth;
            neighbors[3] = mapWidth + 1;
            return neighbors;
        } else if (id == mapWidth - 1) {
            int[] neighbors = new int[4];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id + mapWidth - 1;
            neighbors[3] = id + mapWidth;
            return neighbors;
        } else if (id == mapWidth * (mapHeight - 1)) {
            int[] neighbors = new int[4];
            neighbors[0] = id;
            neighbors[1] = id - mapWidth;
            neighbors[2] = id - mapWidth + 1;
            neighbors[3] = id + 1;
            return neighbors;
        } else if (id == mapHeight * mapWidth - 1) {
            int[] neighbors = new int[4];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id - mapWidth;
            neighbors[3] = id - mapWidth - 1;
            return neighbors;

        //крайние по сторонам

        } else if (id < mapWidth) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id + 1;
            neighbors[3] = id + mapWidth - 1;
            neighbors[4] = id + mapWidth;
            neighbors[5] = id + mapWidth + 1;
            return neighbors;
        } else if (id >= mapWidth * (mapHeight - 1)) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id + 1;
            neighbors[3] = id - mapWidth - 1;
            neighbors[4] = id - mapWidth;
            neighbors[5] = id - mapWidth + 1;
            return neighbors;
        } else if (id % mapWidth == 0) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - mapWidth;
            neighbors[2] = id + mapWidth;
            neighbors[3] = id + 1;
            neighbors[4] = id - mapWidth + 1;
            neighbors[5] = id + mapWidth + 1;
            return neighbors;
        } else if ((id + 1) % mapWidth == 0) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - mapWidth;
            neighbors[2] = id + mapWidth;
            neighbors[3] = id - 1;
            neighbors[4] = id - mapWidth - 1;
            neighbors[5] = id + mapWidth - 1;
            return neighbors;

        //все остальные

        } else {
            int[] neighbors = new int[9];
            neighbors[0] = id;
            neighbors[1] = id + 1;
            neighbors[2] = id - 1;
            neighbors[3] = id + mapWidth;
            neighbors[4] = id - mapWidth;
            neighbors[5] = id - mapWidth - 1;
            neighbors[6] = id - mapWidth + 1;
            neighbors[7] = id + mapWidth - 1;
            neighbors[8] = id + mapWidth + 1;
            return neighbors;
        }
    }



//    public boolean isNeighbors(Province province1, Province province2) {
//        if (province1.id == province2.id) {
//            return true;
//
//        //угловые
//
//        } else if (province1.id == 0) {
//            if (province2.id == 1 || province2.id == mapWidth || province2.id == mapWidth + 1) {
//                return true;
//            } else {
//                return false;
//            }
//        } else if (province1.id == mapWidth - 1) {
//            if (province2.id == mapWidth - 2 || province2.id == 2 * mapWidth - 1 || province2.id == province1.id + mapWidth - 1) {
//                return true;
//            } else {
//                return false;
//            }
//        } else if (province1.id == mapWidth * (mapHeight - 1)) {
//            if (province2.id == province1.id + 1 || province2.id == province1.id - mapWidth || province2.id == province1.id - mapWidth + 1) {
//                return true;
//            } else {
//                return false;
//            }
//        } else if (province1.id == mapHeight * mapWidth - 1) {
//            if (province2.id == province1.id - 1 || province2.id == province1.id - mapWidth || province2.id == province1.id - mapWidth - 1) {
//                return true;
//            } else {
//                return false;
//            }
//
//        //крайние
//
//        } else if (province1.id < mapWidth) {
//
//        }
//
//    }
}
