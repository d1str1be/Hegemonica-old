package com.hegemonica.game.noneed.funscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.hegemonica.game.noneed.funscreen.PlayScreen;

public class PointInPolygonTest extends PlayScreen {
    Vector2 a = new Vector2(), b = new Vector2(), c = new Vector2(), d = new Vector2();
    Vector2 a1 = new Vector2(), b1 = new Vector2(), c1 = new Vector2(), d1 = new Vector2();
    float r = 10;
    boolean intersect = false;
    boolean contain = false;

    public void update() {
        Gdx.gl.glLineWidth(2);
        intersect = false;
        contain = false;
        if (pointInPolygon(a1, a, b, c, d) || pointInPolygon(b1, a, b, c, d) || pointInPolygon(c1, a, b, c, d) || pointInPolygon(d1, a, b, c, d))
            intersect = true;
        if (pointInPolygon(a, a1, b1, c1, d1) || pointInPolygon(b, a1, b1, c1, d1) || pointInPolygon(c, a1, b1, c1, d1) || pointInPolygon(d, a1, b1, c1, d1))
            intersect = true;

        if (pointInPolygon(a1, a, b, c, d) && pointInPolygon(b1, a, b, c, d) && pointInPolygon(c1, a, b, c, d) && pointInPolygon(d1, a, b, c, d))
            contain = true;
        if (pointInPolygon(a, a1, b1, c1, d1) && pointInPolygon(b, a1, b1, c1, d1) && pointInPolygon(c, a1, b1, c1, d1) && pointInPolygon(d, a1, b1, c1, d1))
            contain = true;
    }

    @Override
    public void render(float delta) {
        update();
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeType.Line);
        debugLine(shapeRenderer);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Filled);
        debugFill(shapeRenderer);
        shapeRenderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        drawLabel(font, batch);
        batch.end();

    }

    private void debugLine(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(Color.RED);
        drawLine(shapeRenderer, a, b);
        drawLine(shapeRenderer, b, c);
        drawLine(shapeRenderer, c, d);
        drawLine(shapeRenderer, d, a);

        shapeRenderer.setColor(Color.BLUE);
        drawLine(shapeRenderer, a1, b1);
        drawLine(shapeRenderer, b1, c1);
        drawLine(shapeRenderer, c1, d1);
        drawLine(shapeRenderer, d1, a1);

    }

    private void debugFill(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        if (!a.isZero())
            shapeRenderer.circle(a.x, a.y, r);
        if (!b.isZero())
            shapeRenderer.circle(b.x, b.y, r);
        if (!c.isZero())
            shapeRenderer.circle(c.x, c.y, r);
        if (!d.isZero())
            shapeRenderer.circle(d.x, d.y, r);
        if (!a1.isZero())
            shapeRenderer.circle(a1.x, a1.y, r);
        if (!b1.isZero())
            shapeRenderer.circle(b1.x, b1.y, r);
        if (!c1.isZero())
            shapeRenderer.circle(c1.x, c1.y, r);
        if (!d1.isZero())
            shapeRenderer.circle(d1.x, d1.y, r);

    }

    private void drawLabel(BitmapFont font, SpriteBatch batch) {
        float offset = 3 * r;
        if (!a.isZero())
            font.draw(batch, "A", a.x, a.y + offset);
        if (!b.isZero())
            font.draw(batch, "B", b.x, b.y + offset);
        if (!c.isZero())
            font.draw(batch, "C", c.x, c.y + offset);
        if (!d.isZero())
            font.draw(batch, "D", d.x, d.y + offset);
        if (!a1.isZero())
            font.draw(batch, "A1", a1.x, a1.y + offset);
        if (!b1.isZero())
            font.draw(batch, "B1", b1.x, b1.y + offset);
        if (!c1.isZero())
            font.draw(batch, "C1", c1.x, c1.y + offset);
        if (!d1.isZero())
            font.draw(batch, "D1", d1.x, d1.y + offset);

        font.draw(batch, "Intersect : " + intersect, 20, 40);
        font.draw(batch, "Contain : " + contain, 20, 80);

    }

    private void drawLine(ShapeRenderer shapeRenderer, Vector2 start, Vector2 end) {
        if (start.isZero() || end.isZero()) {
            return;
        } else {
            shapeRenderer.line(start, end);
        }
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        viewport.unproject(touchPoint.set(x, y));
        if (a.isZero()) {
            a.set(touchPoint);
            return true;
        }
        if (b.isZero()) {
            b.set(touchPoint);
            return true;
        }
        if (c.isZero()) {
            c.set(touchPoint);
            return true;
        }
        if (d.isZero()) {
            d.set(touchPoint);
            return true;
        }
        if (a1.isZero()) {
            a1.set(touchPoint);
            return true;
        }
        if (b1.isZero()) {
            b1.set(touchPoint);
            return true;
        }
        if (c1.isZero()) {
            c1.set(touchPoint);
            return true;
        }
        if (d1.isZero()) {
            d1.set(touchPoint);
            return true;
        }

        return super.tap(x, y, count, button);
    }

    Vector2 temp = new Vector2();

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        viewport.unproject(touchPoint.set(x, y));
        if (hitPoint(touchPoint, a))
            temp = a;
        if (hitPoint(touchPoint, b))
            temp = b;
        if (hitPoint(touchPoint, c))
            temp = c;
        if (hitPoint(touchPoint, d))
            temp = d;
        if (hitPoint(touchPoint, a1))
            temp = a1;
        if (hitPoint(touchPoint, b1))
            temp = b1;
        if (hitPoint(touchPoint, c1))
            temp = c1;
        if (hitPoint(touchPoint, d1))
            temp = d1;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        viewport.unproject(touchPoint.set(screenX, screenY));
        temp.set(touchPoint);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(touchPoint.set(screenX, screenY));
        temp.set(0, 0);
        tap(screenX, screenY, pointer, button);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    boolean hitPoint(Vector2 touchPoint, Vector2 point) {
        return (touchPoint.dst(point.x, point.y) <= r);
    }

    boolean pointInPolygon(Vector2 point, Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
        if (point.isZero() || a.isZero() || b.isZero() || c.isZero() || d.isZero())
            return false;
        return pnpoly(getVerticeRect(a, b, c, d), point.x, point.y);
    }

    private float[] getVerticeRect(Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
        float[] vertice = new float[8];
        vertice[0] = a.x;
        vertice[1] = a.y;
        vertice[2] = b.x;
        vertice[3] = b.y;
        vertice[4] = c.x;
        vertice[5] = c.y;
        vertice[6] = d.x;
        vertice[7] = d.y;
        return vertice;

    }

    public boolean pnpoly(float[] vertx, float[] verty, float testx, float testy) {
        int nvert = vertx.length;
        int i, j;
        boolean c = false;
        for (i = 0, j = nvert - 1; i < nvert; j = i++) {
            if (((verty[i] > testy) != (verty[j] > testy)) && (testx < (vertx[j] - vertx[i]) * (testy - verty[i]) / (verty[j] - verty[i]) + vertx[i]))
                c = !c;
        }
        return c;
    }

    public boolean pnpoly(float[] vertices, float testx, float testy) {

        float[] vertx = new float[vertices.length / 2];
        float[] verty = new float[vertices.length / 2];
        for (int index = 0; index < vertices.length; index++) {
            if (index % 2 == 0)
                vertx[index / 2] = vertices[index];
            if (index % 2 == 1)
                verty[index / 2] = vertices[index];

        }
        return pnpoly(vertx, verty, testx, testy);
    }

}