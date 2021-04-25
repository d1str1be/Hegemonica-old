package com.hegemonica.game.logic.scenarios.gemelch;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hegemonica.game.localization.ProvinceLoc;
import com.hegemonica.game.logic.country.Country;
import com.hegemonica.game.logic.country.Province;

import java.util.ArrayList;

// ЭТО СЦЕНАРИЙ. ЗДЕСЬ ЗАДАЕМ ВСЕ СТРАНЫ ЭТОГО СЦЕНАРИЯ, КАРТУ "ПАНГЕЯ" И ВСЕ ОСТАЛЬНОЕ
public class Gemelch {
    public static final int COUNT_OF_RESOURCES = 26;
    public static final int PROVINCE_COUNT = 6;
    public int turnNumber;
    public Country test;
    public Country test1;
    public Province[] provinces;
    public Province levian;
    public Province valinia;

    public Gemelch(){
        onStart();
    }
    public void onStart(){
        turnNumber = 1;
        test = new Country("Test", 0);
        test1 = new Country("Test Testov", 1);
        levian = new Province(0, ProvinceLoc.Names.Levian.toString(), test, ProvCoords.levianProv, new boolean[]{true, true}, true);
        valinia = new Province(1, ProvinceLoc.Names.Valinia.toString(), test1,ProvCoords.valiniaProv,  new boolean[]{true, true}, true);
        provinces = new Province[]{levian, valinia};
    }
    public void onTurn(){
        test.onTurn();
        test1.onTurn();
        turnNumber++;
    }

//    public class Provinces {
//        public Province levian;
//        public Province valinia;
//
//    }
    public void render(OrthographicCamera camera){
        for (Province x:provinces) {
            x.render(camera);
        }
    }

    /**
     * Returns selected point. Should be used in tap() method of GestureListener
     * @return Selected province
     */

    public Province whichPolygonContainsPoint(float x, float y){
        for(int i=0;i<provinces.length;i++){
            if(provinces[i].contains(x,y))
                return provinces[i];
        }
        return null;
    }
}
