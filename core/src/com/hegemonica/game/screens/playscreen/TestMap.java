package com.hegemonica.game.screens.playscreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hegemonica.game.Framerate;
import com.hegemonica.game.TestCamera;

import java.util.Random;

public class TestMap implements Disposable, GestureDetector.GestureListener {
    private ProvCoords provCoords;
    boolean coordsAreSame;
    public OrthographicCamera camera;
    public Viewport viewport;
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
    Framerate fps;
    BitmapFont font;
    SpriteBatch batch;
    float zoomMin = 3f;
    float zoomMax = 0.25f;
    public TestMap() {
        Gdx.input.setInputProcessor(new GestureDetector(this));
        provCoords = new ProvCoords();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        shapeRenderer = new ShapeRenderer();

        testVertices = new FloatArray(new float[]{651, 507,
                645, 505, 642, 504,
                639, 500, 644, 496,
                647, 490, 648, 479,
                654, 480, 659, 479,
                661, 474, 671, 479,
                672, 481, 675, 481,
                678, 477, 678, 472,
                673, 467, 673, 463,
                676, 461, 680, 641,
                691, 464, 698, 461,
                697, 457, 697, 452,
                702, 448, 706, 443,
                711, 437, 711, 453,
                718, 455, 719, 462,
                711, 464, 706, 474, 609,
                479, 693, 481, 690, 486,
                681, 490, 673, 493, 668,
                497, 663, 497, 661, 502,
                651, 502, 651, 507});

        pix = new Pixmap(1 , 1, Format.RGBA8888);
        pix.setColor(Color.RED);
        pix.fill();
        texture = new Texture(pix);
        textureReg = new TextureRegion(texture);
        polyReg = new PolygonRegion(textureReg, provCoords.levianProv.toArray(), triangulate(provCoords.levianProv).toArray());
        polySprite = new PolygonSprite(polyReg);
        polyBatch = new PolygonSpriteBatch();

        fps = new Framerate();
        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    private ShortArray triangulate(FloatArray polygonVertices){
        return triangulator.computeTriangles(polygonVertices);
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.WHITE);
//        shapeRenderer.polygon(testVertices);
//        shapeRenderer.end();
        camera.update();
        polyBatch.setProjectionMatrix(camera.combined);
        fps.render();
        polyBatch.begin();
        polySprite.draw(polyBatch);
        polyBatch.end();
        batch.begin();
        font.draw(batch, "zoom = " + camera.zoom, Gdx.graphics.getWidth()-250f, Gdx.graphics.getHeight()-250f);
        batch.end();
    }


    public void resize(int width, int height) {

    }
    @Override
    public void dispose() {
        texture.dispose();
        pix.dispose();
    }

//    @Override
//    public boolean keyDown(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        float x = Gdx.input.getDeltaX();
//        float y = Gdx.input.getDeltaY();
//
//        camera.translate(-x,y);
//        return true;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(float amountX, float amountY) {
//        return false;
//    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
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
        camera.translate(-deltaX*camera.zoom,deltaY*camera.zoom);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
//        if(camera.zoom >= zoomMax && camera.zoom <= zoomMin) {
            if (initialDistance >= distance) {
                camera.zoom += initialDistance*0.001f-distance*0.001f;
            } else {
                camera.zoom -= distance*0.001f-initialDistance*0.001f;
            }
//        }
//        if(camera.zoom>=zoomMax){
//            camera.zoom = zoomMax;
//        }
//        else if(camera.zoom <= zoomMin)
//            camera.zoom = zoomMin;
       return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

//всё что ниже - одна из попыток найти способ отрисовки заполненного цветом полигона - Богд

//    public void polygon(float[] vertices, int offset, int count) {
//        if (shapeType != ShapeRenderer.ShapeType.Filled && shapeType != ShapeType.Line)
//            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
//        if (count < 6)
//            throw new IllegalArgumentException("Polygons must contain at least 3 points.");
//        if (count % 2 != 0)
//            throw new IllegalArgumentException("Polygons must have an even number of vertices.");
//
//        check(shapeType, null, count);
//
//        final float firstX = vertices[0];
//        final float firstY = vertices[1];
//        if (shapeType == ShapeRenderer.ShapeType.Line) {
//            for (int i = offset, n = offset + count; i < n; i += 2) {
//                final float x1 = vertices[i];
//                final float y1 = vertices[i + 1];
//
//                final float x2;
//                final float y2;
//
//                if (i + 2 >= count) {
//                    x2 = firstX;
//                    y2 = firstY;
//                } else {
//                    x2 = vertices[i + 2];
//                    y2 = vertices[i + 3];
//                }
//
//                renderer.color(color);
//                renderer.vertex(x1, y1, 0);
//                renderer.color(color);
//                renderer.vertex(x2, y2, 0);
//
//            }
//        } else {
//            ShortArray arrRes = ear.computeTriangles(vertices);
//
//            for (int i = 0; i < arrRes.size - 2; i = i + 3) {
//                float x1 = vertices[arrRes.get(i) * 2];
//                float y1 = vertices[(arrRes.get(i) * 2) + 1];
//
//                float x2 = vertices[(arrRes.get(i + 1)) * 2];
//                float y2 = vertices[(arrRes.get(i + 1) * 2) + 1];
//
//                float x3 = vertices[arrRes.get(i + 2) * 2];
//                float y3 = vertices[(arrRes.get(i + 2) * 2) + 1];
//
//                this.triangle(x1, y1, x2, y2, x3, y3);
//            }
//        }
//    }
}