package com.hegemonica.game.noneed.funscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ChipoIcon {
    private Vector2 position;
    private Vector2 velocity;
    private static Texture myTexture;

    public ChipoIcon(Vector2 position, Vector2 velocity){
    this.position = position;
    this.velocity = velocity;
    }
    public static void setMyTexture(Texture myTexture) {
        ChipoIcon.myTexture = myTexture;
    }
    public void render(SpriteBatch batch){
        batch.draw(myTexture, position.x, position.y);
    }
    public void update(){
        position.add(velocity);

        if (position.x + myTexture.getWidth() > Gdx.graphics.getWidth()){
            velocity.x=-velocity.x;
        }
        if (position.y + myTexture.getHeight() > Gdx.graphics.getHeight()){
            velocity.y=-velocity.y;
        }
        if (position.x<0){
            velocity.x=-velocity.x;
        }
        if (position.y<0){
            velocity.y=-velocity.y;
        }
    }
}
