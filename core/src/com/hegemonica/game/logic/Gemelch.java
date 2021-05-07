package com.hegemonica.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.HegeLog;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public GemelchGFX gfx;

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

    public Country red;
    public Country green;
    public Country blue;
    public Country yellow;
    public Country nothing;

    public int provCountWidth;
    public int provCountHeight;
    public int provincesCount;
    public Province[] provinces;



    public Gemelch(int provCountWidth, int provCountHeight, Viewport viewport) {
        this.provCountWidth = provCountWidth;
        this.provCountHeight = provCountHeight;

        provincesCount = provCountWidth * provCountHeight;
        HegeLog.log(HegeLog.MAP, "prov in width = " + provCountWidth);
        HegeLog.log(HegeLog.MAP, "prov in height = " + provCountHeight);

        turnNumber = 1;
        nothing = new Country("Nothing", 0, Color.WHITE);
        red = new Country("Red", 1, Color.RED);
        green = new Country("Green", 2, Color.GREEN);
        blue = new Country("Blue", 3, Color.BLUE);
        yellow = new Country("Yellow", 4, Color.YELLOW);


        provinces = new Province[provCountWidth * provCountHeight];
        for (int i = 0; i < provCountHeight; i++) {
            for (int j = 0; j < provCountWidth; j++) {
                int iterator = i * provCountWidth + j;
                provinces[iterator] = new Province(iterator, "Province " + iterator, nothing, new boolean[]{true}, false, 50 * j, 50 * i, 50, 50, viewport);
                provinces[iterator].buildingInProcess = provinces[iterator].farm;
            }
        }
        provinces[0].owner = red;
        HegeLog.log("Gemelch", "Owner of " + provinces[0].name + " is " + provinces[(provCountHeight - 1) * provCountWidth].owner.name);

        provinces[(provCountHeight - 1) * provCountWidth].owner = blue;
        HegeLog.log("Gemelch", "Owner of " + provinces[(provCountHeight - 1) * provCountWidth].name + " is " + provinces[(provCountHeight - 1) * provCountWidth].owner.name);

        provinces[provCountHeight * provCountWidth - 1].owner = yellow;
        HegeLog.log("Gemelch", "Owner of " + provinces[provCountHeight * provCountWidth - 1].name + " is " + provinces[(provCountHeight - 1) * provCountWidth].owner.name);

        provinces[provCountWidth - 1].owner = green;
        HegeLog.log("Gemelch", "Owner of " + provinces[provCountWidth - 1].name + " is " + provinces[(provCountHeight - 1) * provCountWidth].owner.name);

        gfx = new GemelchGFX();
    }

    public void onTurn() {
        for (Province province : provinces) {
            province.onTurn();
        }
        turnNumber++;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }


    public void render(OrthographicCamera camera) {
        for (Province x : provinces) {
            x.render(camera);
        }
    }

    public float[] getProvX() {
        float[] provX = new float[provincesCount];
        for (int i = 0; i < provincesCount; i++) {
            provX[i] = provinces[i].x;
        }
        return provX;
    }

    public float[] getProvY() {
        float[] provY = new float[provincesCount];
        for (int i = 0; i < provincesCount; i++) {
            provY[i] = provinces[i].y;
        }
        return provY;
    }

    /**
     * Returns polygon which contains tap point. Returns null if no province contains tap point
     *
     * @return Selected province
     */

    public Province whichPolygonContainsPoint(float x, float y) {
        for (Province province : provinces) {
            if (province.contains(x, y))
                return province;
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
        int[] neighborsIntList = getNeighborsIdList(province);
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
