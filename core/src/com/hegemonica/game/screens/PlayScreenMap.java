package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.FPS;
import com.hegemonica.game.HegeLog;
import com.hegemonica.game.logic.Gemelch;
import com.hegemonica.game.logic.scenarios.gemelch.ProvCoords;

import java.util.Random;

public class PlayScreenMap implements Disposable, GestureDetector.GestureListener {
    Core game;
    InputMultiplexer inputMultiplexer;
    HUD hud;
    private Gemelch gemelch;


    private ProvCoords provCoords;
    boolean coordsAreSame;
    public OrthographicCamera camera;
    public Viewport viewport;
    //    private Viewport UIviewport;
    private ShapeRenderer shapeRenderer;
    private final float WORLD_HEIGHT = 100;
    private final float WORLD_WIDTH = 50;
    private FloatArray testVertices;
    //719, 431, 736, 427, 744, 428, 753, 432,
    //760, 433,
    private ShortArray testTriangleVertices;
    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();
    private Random random = new Random(System.currentTimeMillis());
    private Texture texture;
    private TextureRegion textureReg;
    private PolygonRegion polyReg;
    private PolygonSprite polySprite;
    private PolygonSpriteBatch polyBatch;
    private Pixmap pix;
    private Skin UIskin;
    private TextButton textButton;


    FPS fps;
    BitmapFont font;
    SpriteBatch batch;
    float zoomMin = 3f;
    float zoomMax = 0.25f;

    float realX;
    float realY;
    float cameraMovementX;
    float cameraMovementY;

    public PlayScreenMap(Core game, int provCountWidth, int provCountHeight) {
        this.game = game;
        hud = new HUD(game, this);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud.stage);
        inputMultiplexer.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(inputMultiplexer);
        gemelch = new Gemelch(provCountWidth, provCountHeight);
//        provCoords = new ProvCoords();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(0, 0);
        cameraMovementX = camera.viewportWidth / 2;
        cameraMovementY = -camera.viewportHeight / 2;
        camera.zoom = 0.5f;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        shapeRenderer = new ShapeRenderer();


        pix = new Pixmap(1, 1, Format.RGBA8888);
        pix.setColor(Color.RED);
        pix.fill();
        texture = new Texture(pix);
        textureReg = new TextureRegion(texture);
        polyReg = new PolygonRegion(textureReg, ProvCoords.levianProv.toArray(), triangulate(ProvCoords.levianProv).toArray());
        polySprite = new PolygonSprite(polyReg);
        polyBatch = new PolygonSpriteBatch();

        UIskin = new Skin(Gdx.files.internal("ui/default/skin/uiskin.json"));
        fps = new FPS();
        font = new BitmapFont();
        batch = new SpriteBatch();

    }

    private ShortArray triangulate(FloatArray polygonVertices) {
        return triangulator.computeTriangles(polygonVertices);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        fps.update();

        fps.render();
        hud.render(delta);
        gemelch.render(camera);

        if (Core.DEV_MODE) {
            batch.begin();
            font.draw(batch, "zoom = " + camera.zoom, Gdx.graphics.getWidth() - 250f, Gdx.graphics.getHeight() - 250f);
            batch.end();
        }
    }


    public void resize(int width, int height) {
        fps.resize(width, height);
    }

    @Override
    public void dispose() {
        fps.dispose();
        texture.dispose();
        pix.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        polyBatch.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        realX = (x - cameraMovementX) / 2;
        realY = (Core.gameHeight - y + cameraMovementY) / 2;
        HegeLog.log("Input", "Tapped on X: " + realX);
        HegeLog.log("Input", "Tapped on Y: " + realY);
        hud.setSelectedProvince(gemelch.whichPolygonContainsPoint(realX, realY));
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        cameraMovementX += deltaX;
        cameraMovementY += deltaY;
        camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
//        if(camera.zoom >= zoomMax && camera.zoom <= zoomMin) {
        if (camera.zoom < zoomMax) {
            camera.zoom = zoomMax;
            return true;
        } else if (camera.zoom > zoomMin) {
            camera.zoom = zoomMin;
            return true;
        }
        while (zoomMax <= camera.zoom && camera.zoom <= zoomMin) {
            if (initialDistance >= distance) {
                camera.zoom += (initialDistance - distance) * 0.00005f * camera.zoom;
                return true;
            } else {
                camera.zoom -= (distance - initialDistance) * 0.00005f * camera.zoom;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public void onTurn(int turnNumber) {
        gemelch.setTurnNumber(turnNumber);
        gemelch.onTurn();
    }
}
