package com.chiposlavia.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.Spring;

public class Chipa {
    private float x = 0.0f;//координаты в виде переменных оставляю на время - потом заменим на вектор
    private float y = 0.0f;
    private float vx = 1.0f;
    private float vy = 1.0f;
    private static Texture myTexture;

    public Chipa(float x, float y, float vx, float vy){
    this.x =  x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    }
//геттеры, сеттеры

    public static void setMyTexture(Texture myTexture) {
        Chipa.myTexture = myTexture;
    }

    public float getX () {
    return x;
}
    public float getY () {
    return y;
}

    public float getVx () {
    return vx;
}
    public float getVy () {
    return vy;
}

    public void setX ( float x){
    this.x = x;
}
    public void setY ( float y){
    this.y = y;
}

    public void setVx ( float vx){
    this.vx = vx;
}
    public void setVy ( float vy){
    this.vy = vy;
}
//конец геттеров, сеттеров
    public void render(SpriteBatch batch){
        batch.draw(myTexture,x,y);
    }
    public void update(){
        x += vx;
        y += vy;

        if (x+144 > Gdx.graphics.getWidth()){
            vx=-vx;
        }
        if (y+144>Gdx.graphics.getHeight()){
            vy=-vy;
        }
        if (x<0){
            vx=-vx;
        }
        if (y<0){
            vy=-vy;
        }
    }
}
