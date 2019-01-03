package com.penguins.animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class TileFlipAnimation extends ComputedAnimation {
    private final Image tileImage;

    public TileFlipAnimation(Image tileImage, int numFrames, int duration) {
        super(numFrames, duration, tileImage.getWidth(), tileImage.getHeight());
        this.tileImage = tileImage;

        setLooping(false);
        setAutoUpdate(true);
        stop();
    }

    @Override
    public void drawComputed(float x, float y, float width, float height, Color col) {
        float animPercent = 1 - ((float) getFrame() + 1) / getFrameCount();
        float adjustedWidth = width * animPercent;
        tileImage.draw(x + (width - adjustedWidth) / 2, y, adjustedWidth, height);
    }
}
