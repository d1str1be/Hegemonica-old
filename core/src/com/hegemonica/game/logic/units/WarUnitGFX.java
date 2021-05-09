package com.hegemonica.game.logic.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hegemonica.game.logic.Country;
import com.hegemonica.game.logic.Province;

public class WarUnitGFX {
    public final int id;
    Sprite sprite;
    public Texture texture;
    
    public WarUnitGFX(final int id, Province prov) {
        this.id = id;
        this.setTexture(prov.owner);
        sprite = new Sprite(texture);
        sprite.setSize(prov.width * 0.5f, prov.height * 0.5f);
        sprite.setPosition(prov.x + prov.width * 0.25f, prov.y + prov.height * 0.25f);
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
}
