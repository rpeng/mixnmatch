package com.penguins.animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * An animation that relies on a single image frame
 * <p>
 * In-between frames are rendered by a custom function
 */
public abstract class ComputedAnimation extends Animation {
    public ComputedAnimation(int numFrames, int duration) {
        this(numFrames, duration, 0, 0);
    }

    public ComputedAnimation(int numFrames, int duration, int width, int height) {
        try {
            Image image = new Image(width, height);
            for (int i = 0; i < numFrames; i++) {
                addFrame(image, duration);
            }

        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void draw(float x, float y, float width, float height, Color col) {
        updateNoDraw();
        drawComputed(x, y, width, height, col);
    }

    public abstract void drawComputed(float x, float y, float width, float height, Color col);
}
