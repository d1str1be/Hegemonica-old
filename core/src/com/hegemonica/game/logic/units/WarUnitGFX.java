package com.hegemonica.game.logic.units;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;
import com.hegemonica.game.ui.HegeProgressBar;


public class WarUnitGFX {
    public final int id;
    Sprite sprite;
    public Texture texture;
    public HegeProgressBar healthBar;
    Label hp;
    SpriteBatch batch;
    
    public WarUnitGFX(final int id, Province prov) {
        this.id = id;
        this.setTexture(prov.owner);
        sprite = new Sprite(texture);
        sprite.setSize(prov.width * 0.5f, prov.height * 0.5f);
        sprite.setPosition(prov.x + prov.width * 0.25f, prov.y + prov.height * 0.25f);
        healthBar = new HegeProgressBar(prov.width * 0.6f, prov.height * 0.1f);
        healthBar.setPosition(prov.x + prov.width * 0.2f, prov.y * 0.8f);
        healthBar.setRange(0, WarUnit.maxHealth);
        healthBar.setValue(WarUnit.maxHealth);
        healthBar.updateVisualValue();
        hp = new Label("0 / 100", prov.owner.gemelch.hud.DefaultUI);
        hp.setFontScale(0.4f);
        hp.setScale(0.4f);
        hp.setPosition(healthBar.getX()+healthBar.getWidth()/2f-hp.getWidth()/2f, healthBar.getY() + healthBar.getHeight()/2f - hp.getHeight()/2f);
        batch = new SpriteBatch();
    }
    public void setHealth(WarUnit unit){
        HegeLog.log("WarUnitGFX", "Set " + unit.owner.name +" Unit health to " + unit.health);
        healthBar.setValue(unit.health);
        healthBar.updateVisualValue();
    }
    public void setHealth(Float hp){
        healthBar.setValue(hp);
        healthBar.updateVisualValue();
    }
    public void setTexture(Country owner) {
        switch (id) {
            case WarUnit.ID.WARRIOR:
                switch (owner.id) {
                    case Country.ID.RED:
                        this.texture = new Texture("icons/units/warriorRed.png");
                        break;
                    case Country.ID.GREEN:
                        this.texture = new Texture("icons/units/warriorGreen.png");
                        break;
                    case Country.ID.BLUE:
                        this.texture = new Texture("icons/units/warriorBlue.png");
                        break;
                    case Country.ID.YELLOW:
                        this.texture = new Texture("icons/units/warriorYellow.png");
                        break;
                }
                break;
            case WarUnit.ID.ARCHER:
                switch (owner.id) {
                    case Country.ID.RED:
                        this.texture = new Texture("icons/units/archerRed.png");
                        break;
                    case Country.ID.GREEN:
                        this.texture = new Texture("icons/units/archerGreen.png");
                        break;
                    case Country.ID.BLUE:
                        this.texture = new Texture("icons/units/archerBlue.png");
                        break;
                    case Country.ID.YELLOW:
                        this.texture = new Texture("icons/units/archerYellow.png");
                        break;
                }
                break;
            case WarUnit.ID.SHIELDMAN:
                switch (owner.id) {
                    case Country.ID.RED:
                        this.texture = new Texture("icons/units/shielderRed.png");
                        break;
                    case Country.ID.GREEN:
                        this.texture = new Texture("icons/units/shielderGreen.png");
                        break;
                    case Country.ID.BLUE:
                        this.texture = new Texture("icons/units/shielderBlue.png");
                        break;
                    case Country.ID.YELLOW:
                        this.texture = new Texture("icons/units/shielderYellow.png");
                        break;
                }
                break;
            case WarUnit.ID.CROSSBOWS:
                switch (owner.id) {
                    case Country.ID.RED:
                        this.texture = new Texture("icons/units/crossbowRed.png");
                        break;
                    case Country.ID.GREEN:
                        this.texture = new Texture("icons/units/crossbowGreen.png");
                        break;
                    case Country.ID.BLUE:
                        this.texture = new Texture("icons/units/crossbowBlue.png");
                        break;
                    case Country.ID.YELLOW:
                        this.texture = new Texture("icons/units/crossbowYellow.png");
                        break;
                }
                break;
            case WarUnit.ID.SWORDSMAN:
                switch (owner.id) {
                    case Country.ID.RED:
                        this.texture = new Texture("icons/units/swordsmanRed.png");
                        break;
                    case Country.ID.GREEN:
                        this.texture = new Texture("icons/units/swordsmanGreen.png");
                        break;
                    case Country.ID.BLUE:
                        this.texture = new Texture("icons/units/swordsmanBlue.png");
                        break;
                    case Country.ID.YELLOW:
                        this.texture = new Texture("icons/units/swordsmanYellow.png");
                        break;
                }
                break;
        }
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public void update(WarUnit unit) {
        healthBar.setPosition(unit.province.x + unit.province.width * 0.2f, unit.province.y + unit.province.height * 0.8f);
        this.setHealth((float) unit.health);
        this.healthBar.updateVisualValue();
        sprite.setPosition(unit.province.x + unit.province.width * 0.25f, unit.province.y + unit.province.height * 0.25f);
    }
    
    public void render(OrthographicCamera camera){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        hp.draw(batch, 1f);
        healthBar.draw(batch, 1f);
        sprite.draw(batch);
        batch.end();
    }
    public void dispose(){
        batch.dispose();
    }
    
}
