package com.hegemonica.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;

public class Framerate implements Disposable {
    long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    public BitmapFont font;
    private final SpriteBatch batch;
    private OrthographicCamera cam;


    public Framerate() {
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        font = new BitmapFont();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void resize(int screenWidth, int screenHeight) {
        cam = new OrthographicCamera(screenWidth, screenHeight);
        cam.translate(screenWidth / 2f, screenHeight / 2f);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }

    public void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if (sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    public void render(int x, int y) {
        batch.begin();
        font.draw(batch, (int) frameRate + " fps", x, y);
        font.getData().setScale(2);
        batch.end();
    }

    public void render() {
        render(10, (int) (Gdx.graphics.getHeight() - font.getLineHeight()));
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}