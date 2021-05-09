package com.hegemonica.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Core;
import com.hegemonica.game.FPS;
import com.hegemonica.game.logic.Gemelch;

import java.util.Random;

public class PlayScreenMap implements Disposable, GestureDetector.GestureListener {
    public Core game;
    InputMultiplexer inputMultiplexer;
    
    private Gemelch gemelch;
    
    
    //    private ProvCoords provCoords;
//    boolean coordsAreSame;
    public OrthographicCamera camera;
    public Viewport viewport;
    //    private Viewport UIviewport;
    private ShapeRenderer shapeRenderer;
    //    private FloatArray testVertices;
//    //719, 431, 736, 427, 744, 428, 753, 432,
//    //760, 433,
//    private ShortArray testTriangleVertices;
    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();
    private Random random = new Random(System.currentTimeMillis());
//    private Texture texture;
//    private TextureRegion textureReg;
//    private PolygonRegion polyReg;
//    private PolygonSprite polySprite;
//    private PolygonSpriteBatch polyBatch;
//    private Pixmap pix;
    
    
    FPS fps;
    Stage stage;
    float zoomMin = 3f;
    float zoomMax = 0.25f;
    
    float realX;
    float realY;
    float cameraMovementX;
    float cameraMovementY;
    
    public PlayScreenMap(Core game, int provCountWidth, int provCountHeight) {
        this.game = game;
        
        
        fps = new FPS();

//        provCoords = new ProvCoords();
        
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(0, 0);
        cameraMovementX = camera.viewportWidth / 2;
        cameraMovementY = -camera.viewportHeight / 2;
        camera.zoom = 0.5f;
        
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);
        stage.getCamera().translate(-Core.gameWidth / 2, -Core.gameHeight / 2, 0);
        
        gemelch = new Gemelch(game, provCountWidth, provCountHeight, stage);
        shapeRenderer = new ShapeRenderer();


//        pix = new Pixmap(1, 1, Format.RGBA8888);
//        pix.setColor(Color.RED);
//        pix.fill();
//        texture = new Texture(pix);
//        textureReg = new TextureRegion(texture);
//        polyReg = new PolygonRegion(textureReg, ProvCoords.levianProv.toArray(), triangulate(ProvCoords.levianProv).toArray());
//        polySprite = new PolygonSprite(polyReg);
//        polyBatch = new PolygonSpriteBatch();


//        font = new BitmapFont();
//        batch = new SpriteBatch();
        
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gemelch.hud.stage);
        inputMultiplexer.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    
    private ShortArray triangulate(FloatArray polygonVertices) {
        return triangulator.computeTriangles(polygonVertices);
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        camera.update();
        fps.update();
        
        gemelch.render(camera, delta);
        fps.render();
        
    }
    
    
    public void resize(int width, int height) {
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        fps.resize(width, height);
    }
    
    @Override
    public void dispose() {
        fps.dispose();
//        texture.dispose();
//        pix.dispose();
        shapeRenderer.dispose();
//        polyBatch.dispose();
    }
    
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }
    
    @Override
    public boolean tap(float x, float y, int count, int button) {
        realX = (x - cameraMovementX) / 2;
        realY = (Core.gameHeight - y + cameraMovementY) / 2;
//        HegeLog.log("Input", "Tapped on X: " + realX);
//        HegeLog.log("Input", "Tapped on Y: " + realY);
        gemelch.hud.setSelectedProvince(gemelch.whichPolygonContainsPoint(realX, realY));
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
        game.discord.onPlaying(turnNumber);
        gemelch.onTurn();
    }
    
    
}
