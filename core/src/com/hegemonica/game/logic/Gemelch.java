package com.hegemonica.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hegemonica.game.Core;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.screens.HUD;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public HUD hud;
    
    public GemelchGFX gfx;
    
    public static int turnNumber;
    
    public Country redCountry;
    public Country greenCountry;
    public Country blueCountry;
    public Country yellowCountry;
    public Country nothingCountry;
    
    public Country turnCountry;
    
    public int provCountWidth;
    public int provCountHeight;
    public int provincesCount;
    public Province[] provinces;
    public int highestX;
    public int highestY;
    
    public Province leftBottomProv;
    public Province rightBottomProv;
    public Province leftTopProv;
    public Province rightTopProv;
    
    
    public Gemelch(Core game, int provCountWidth, int provCountHeight, Stage stage) {
        this.provCountWidth = provCountWidth;
        this.provCountHeight = provCountHeight;
        
        provincesCount = provCountWidth * provCountHeight;
        
        HegeLog.log(HegeLog.MAP, "prov in width = " + provCountWidth);
        HegeLog.log(HegeLog.MAP, "prov in height = " + provCountHeight);
        
        gfx = new GemelchGFX(this);
        gfx.setStage(stage);
        
        turnNumber = 1;
        nothingCountry = new Country(this, "Nothing", Country.ID.NOTHING, Color.WHITE);
        redCountry = new Country(this, "Red", Country.ID.RED, Color.RED);
        greenCountry = new Country(this, "Green", Country.ID.GREEN, Color.GREEN);
        blueCountry = new Country(this, "Blue", Country.ID.BLUE, Color.BLUE);
        yellowCountry = new Country(this, "Yellow", Country.ID.YELLOW, Color.YELLOW);
        turnCountry = redCountry;
    
        hud = new HUD(game, this);
        
        provinces = new Province[provCountWidth * provCountHeight];
        for (int i = 0; i < provCountHeight; i++) {
            for (int j = 0; j < provCountWidth; j++) {
                int iterator = i * provCountWidth + j;
                provinces[iterator] = new Province(iterator, "Province " + iterator, nothingCountry, new boolean[]{true}, false, 50 * j, 50 * i, 50, 50);
            }
        }
        highestX = 50 * provCountWidth;
        highestY = 50 * provCountHeight;
        
        leftBottomProv = provinces[0];
        rightBottomProv = provinces[provCountWidth - 1];
        leftTopProv = provinces[(provCountHeight - 1) * provCountWidth];
        rightTopProv = provinces[provCountWidth * provCountHeight - 1];
        
        leftBottomProv.owner = redCountry;
        leftBottomProv.isCity = true;
        leftBottomProv.possibleBuildings.remove(2);
        leftBottomProv.lProvName = new Label(leftBottomProv.name, leftBottomProv.defaultSkin, "Red");
        leftBottomProv.lProvName.setSize(leftBottomProv.width * 0.2f, leftBottomProv.height * 0.2f);
        leftBottomProv.lProvName.setFontScale(0.5f);
        leftBottomProv.lProvName.setPosition(leftBottomProv.x + (leftBottomProv.width * 0.05f), leftBottomProv.y);
        
        leftTopProv.owner = blueCountry;
        leftTopProv.isCity = true;
        leftTopProv.possibleBuildings.remove(2);
        leftTopProv.lProvName = new Label(leftTopProv.name, leftTopProv.defaultSkin, "Blue");
        leftTopProv.lProvName.setFontScale(0.5f);
        leftTopProv.lProvName.setSize(leftTopProv.width * 0.2f, leftTopProv.height * 0.2f);
        leftTopProv.lProvName.setPosition(leftTopProv.x + (leftTopProv.width * 0.05f), leftTopProv.y);
        
        rightTopProv.owner = yellowCountry;
        rightTopProv.isCity = true;
        rightTopProv.possibleBuildings.remove(2);
        rightTopProv.lProvName = new Label(rightTopProv.name, rightTopProv.defaultSkin, "Yellow");
        rightTopProv.lProvName.setFontScale(0.5f);
        rightTopProv.lProvName.setSize(rightTopProv.width * 0.2f, rightTopProv.height * 0.2f);
        rightTopProv.lProvName.setPosition(rightTopProv.x + (leftBottomProv.width * 0.05f), rightTopProv.y);
        
        rightBottomProv.owner = greenCountry;
        rightBottomProv.isCity = true;
        rightBottomProv.possibleBuildings.remove(2);
        rightBottomProv.lProvName = new Label(rightBottomProv.name, rightBottomProv.defaultSkin, "Green");
        rightBottomProv.lProvName.setFontScale(0.5f);
        rightBottomProv.lProvName.setSize(rightBottomProv.width * 0.2f, rightBottomProv.height * 0.2f);
        rightBottomProv.lProvName.setPosition(rightBottomProv.x + (rightBottomProv.width * 0.05f), rightBottomProv.y);
        
        leftBottomProv.manualInitialization();
        rightBottomProv.manualInitialization();
        leftTopProv.manualInitialization();
        rightTopProv.manualInitialization();
        
        gfx.addProvincesToStage();
    }
    
    public void onTurn() {
        switch (turnCountry.id) {
            case Country.ID.RED:
                HegeLog.log("Gemelch", "Red turns");
                if(redCountry.onTurn())
                turnCountry = greenCountry;
                else{
                    HegeLog.log("Gemelch", "NO FUCKING TURN RED COUNTRY BAD");
                    return;
                }
                
                break;
            case Country.ID.GREEN:
                HegeLog.log("Gemelch", "Green turns");
                if(greenCountry.onTurn())
                    turnCountry = blueCountry;
                else{
                    HegeLog.log("Gemelch", "NO FUCKING TURN GREEN COUNTRY BAD");
                    return;
                }
                break;
            case Country.ID.BLUE:
                HegeLog.log("Gemelch", "Blue turns");
                if(blueCountry.onTurn())
                    turnCountry = yellowCountry;
                else{
                    HegeLog.log("Gemelch", "NO FUCKING TURN BLUE COUNTRY BAD");
                    return;
                }
                break;
            case Country.ID.YELLOW:
                HegeLog.log("Gemelch", "Yellow turns");
                if(yellowCountry.onTurn())
                    turnCountry = redCountry;
                else{
                    HegeLog.log("Gemelch", "NO FUCKING TURN YELLOW COUNTRY BAD");
                    return;
                }
                turnNumber++;
                hud.newTurn();
                break;
        }
    }
    
    
    public void render(OrthographicCamera camera, float delta) {
        gfx.render();
        for (Province x : provinces) {
            x.render(camera);
        }
        hud.render(delta);
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
    
    public Province whichProvinceContainsPoint(float x, float y) {
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
            neighbors[2] = provCountWidth;
            neighbors[3] = provCountWidth + 1;
            return neighbors;
        } else if (id == provCountWidth - 1) {
            int[] neighbors = new int[4];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id + provCountWidth - 1;
            neighbors[3] = id + provCountWidth;
            return neighbors;
        } else if (id == provCountWidth * (provCountHeight - 1)) {
            int[] neighbors = new int[4];
            neighbors[0] = id;
            neighbors[1] = id - provCountWidth;
            neighbors[2] = id - provCountWidth + 1;
            neighbors[3] = id + 1;
            return neighbors;
        } else if (id == provCountHeight * provCountWidth - 1) {
            int[] neighbors = new int[4];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id - provCountWidth;
            neighbors[3] = id - provCountWidth - 1;
            return neighbors;
            
            //крайние по сторонам
            
        } else if (id < provCountWidth) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id + 1;
            neighbors[3] = id + provCountWidth - 1;
            neighbors[4] = id + provCountWidth;
            neighbors[5] = id + provCountWidth + 1;
            return neighbors;
        } else if (id >= provCountWidth * (provCountHeight - 1)) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - 1;
            neighbors[2] = id + 1;
            neighbors[3] = id - provCountWidth - 1;
            neighbors[4] = id - provCountWidth;
            neighbors[5] = id - provCountWidth + 1;
            return neighbors;
        } else if (id % provCountWidth == 0) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - provCountWidth;
            neighbors[2] = id + provCountWidth;
            neighbors[3] = id + 1;
            neighbors[4] = id - provCountWidth + 1;
            neighbors[5] = id + provCountWidth + 1;
            return neighbors;
        } else if ((id + 1) % provCountWidth == 0) {
            int[] neighbors = new int[6];
            neighbors[0] = id;
            neighbors[1] = id - provCountWidth;
            neighbors[2] = id + provCountWidth;
            neighbors[3] = id - 1;
            neighbors[4] = id - provCountWidth - 1;
            neighbors[5] = id + provCountWidth - 1;
            return neighbors;
            
            //все остальные
            
        } else {
            int[] neighbors = new int[9];
            neighbors[0] = id;
            neighbors[1] = id + 1;
            neighbors[2] = id - 1;
            neighbors[3] = id + provCountWidth;
            neighbors[4] = id - provCountWidth;
            neighbors[5] = id - provCountWidth - 1;
            neighbors[6] = id - provCountWidth + 1;
            neighbors[7] = id + provCountWidth - 1;
            neighbors[8] = id + provCountWidth + 1;
            return neighbors;
        }
    }
    
    public boolean[] getBooleanNeighborsList(Province province) {
        HegeLog.log("Gemelch Neighbour", "getNeighborsIdList(province).length: " + getNeighborsIdList(province).length);
        int neighborsQuantity = getNeighborsIdList(province).length;
        int[] neighborsIntList = getNeighborsIdList(province);
        boolean[] neighborsBooleanList = new boolean[provCountWidth * provCountHeight];
        for (int i = 0; i < provCountWidth * provCountHeight; i++) {
            neighborsBooleanList[i] = false;
        }
        HegeLog.log("Gemelch Neighbour", "neighborsBooleanList.length(): " + neighborsBooleanList.length);
        for (int i = 0; i < neighborsQuantity; i++) {
            neighborsBooleanList[neighborsIntList[i]] = true;
        }
        return neighborsBooleanList;
    }
}
