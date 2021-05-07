package com.hegemonica.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * The progress bar which reassembles the behaviour of the health bar.
 *
 * @author serhiy
 */
public class HegeProgressBar extends ProgressBar {

    /**
     * @param width  of the health bar
     * @param height of the health bar
     */
    public HegeProgressBar(float width, float height, int id) {
        super(0f, 1f, 0.01f, false, new ProgressBarStyle());
        switch (id) {
            case ID.FOOD:
                HegeLog.log("HUD", "case FOOD");
                getStyle().background = getColoredDrawable((int) width, (int) height, Color.GRAY);
                getStyle().knob = getColoredDrawable(0, (int) height, Color.GREEN);
                getStyle().knobBefore = getColoredDrawable((int) width, (int) height, Color.GREEN);
                break;
            case ID.PRODUCTION:
                HegeLog.log("HUD", "case PRODUCTION");
                getStyle().background = getColoredDrawable((int) width, (int) height, Color.GRAY);
                getStyle().knob = getColoredDrawable(0, (int) height, Color.BROWN);
                getStyle().knobBefore = getColoredDrawable((int) width, (int) height, Color.BROWN);
                break;
            case ID.SCIENCE:
                HegeLog.log("HUD", "case SCIENCE");
                getStyle().background = getColoredDrawable((int) width, (int) height, Color.GRAY);
                getStyle().knob = getColoredDrawable(0, (int) height, Color.BLUE);
                getStyle().knobBefore = getColoredDrawable((int) width, (int) height, Color.BLUE);
                break;
            default:
                HegeLog.log("HUD", "case default");
                getStyle().background = getColoredDrawable((int) width, (int) height, Color.RED);
                getStyle().knob = getColoredDrawable(0, (int) height, Color.GREEN);
                getStyle().knobBefore = getColoredDrawable((int) width, (int) height, Color.GREEN);
                break;
        }
        setWidth(width);
        setHeight(height);

        setAnimateDuration(0.0f);
        setValue(0.5f);

        setAnimateDuration(0.25f);
    }

    public static Drawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        return drawable;
    }

    public static class ID {
        public final static int FOOD = 0;
        public final static int PRODUCTION = 1;
        public final static int SCIENCE = 2;
    }
}
