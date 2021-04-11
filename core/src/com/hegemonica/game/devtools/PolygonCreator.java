package com.hegemonica.game.devtools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.screens.playscreen.PlayScreen;

public class PolygonCreator extends PlayScreen implements Disposable {
    //Map<Integer, ArrayList<Vector2>> provinces = new Map<Integer, ArrayList<Vector2>>();
    //InputProcessor inputProcessor;
    boolean coordsAreSame;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private final float WORLD_HEIGHT = 100;
    private final float WORLD_WIDTH = 50;
    private float testVertices[] = {40, 40, 300, 370, 200, 150, 150, 150};

    public PolygonCreator() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        shapeRenderer = new ShapeRenderer();
    }
//    @Override
//    public void show() {
//        camera
//    }

    public void render(float delta) {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        drawPoint();
        drawLine(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        //drawLine(0, Gdx.graphics.getHeight(), 250, Gdx.graphics.getHeight()-250);
        drawLinePolygon(testVertices);
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void drawPoint() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.point(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 15);
        shapeRenderer.end();
    }

    public void drawFilled() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(40, 40, 30, 30);
        shapeRenderer.circle(70, Gdx.graphics.getHeight() - 700, 50);
        shapeRenderer.end();
    }

    public void drawLine(float x, float y, float x1, float y1) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(x, y, x1 + 1, y1 + 1);
        shapeRenderer.end();
    }

    public void drawLinePolygon(float vertices[]) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.polygon(vertices);
        shapeRenderer.end();
    }

    public void drawFilledPolygon(float vertices[]) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.polygon(vertices);
        shapeRenderer.end();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }


}
