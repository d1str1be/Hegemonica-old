package com.hegemonica.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GemelchGFX {
    Gemelch gemelch;
    Stage stage;

    public GemelchGFX(Gemelch gemelch) {
        this.gemelch = gemelch;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addProvincesToStage() {
        stage.clear();
        for (Province province : gemelch.provinces) {
            stage.addActor(province.lProvName);
        }
    }
    public void addProvinceToStage(Province prov){
    }
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
