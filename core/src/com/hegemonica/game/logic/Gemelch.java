package com.hegemonica.game.logic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hegemonica.game.LogManager;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public int turnNumber;
    public int mapHeight;
    public int mapWidth;

    //production
    public int citizenProduction = 1;
    public int mineProduction = 3;
    public int shipyardProduction = 3;

    //food production
    public int startFoodProduction = 2;
    public int farmFoodProduction = 3;
    public int shipyardFoodProduction = 2;

    public Country test;
    public Country test1;

    public int provCountWidth;
    public int provCountHeight;
    public Province[] provinces;

    public Gemelch(int provCountWidth, int provCountHeight) {
        this.provCountWidth = provCountWidth;
        this.provCountHeight = provCountHeight;
        LogManager.log(LogManager.Tags.MAP,"prov in width = " + provCountWidth);
        LogManager.log(LogManager.Tags.MAP,"prov in height = " + provCountHeight);

        turnNumber = 1;
        test = new Country("Test1", 0);
        test1 = new Country("Test2", 1);

        provinces = new Province[provCountWidth * provCountHeight];
        for (int i = 0; i < provCountHeight; i++) {
            for (int j = 0; j < provCountWidth; j++) {
                int iterator = i * provCountWidth + j;
                System.out.println(iterator);
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

    public int[] getNeighborsIdList(Province province) {
        int id = province.id;

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

    public boolean[] getBooleanNeighborsList(Province province) {
        int neighborsQuantity = getNeighborsIdList(province).length;
        int[] neighborsIntList = new int[neighborsQuantity];
        boolean[] neighborsBooleanList = new boolean[mapWidth * mapHeight];
        for (int i = 0; i < mapWidth * mapHeight; i++) {
            neighborsBooleanList[i] = false;
        }
        for (int i = 0; i < neighborsQuantity; i++) {
            neighborsBooleanList[neighborsIntList[i]] = true;
        }
        return neighborsBooleanList;
    }
}