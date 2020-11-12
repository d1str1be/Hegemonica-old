package com.chiposlavia.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.Vector;
import javax.swing.Spring;

public class Chipa {
    private Vector2 position;
    private Vector2 velocity;
    private static Texture myTexture;

    public Chipa(Vector2 position, Vector2 velocity){
    this.position = position;
    this.velocity = velocity;
    }
    public static void setMyTexture(Texture myTexture) {
        Chipa.myTexture = myTexture;
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
