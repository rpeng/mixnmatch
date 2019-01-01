package com.penguins.components;

import com.penguins.text.Zalgo;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;

public class TileComponent extends MouseOverComponent {
    private static Image[] frames;

    private final char symbol;
    private final Font font;
    private final Animation idleAnimation;

    public TileComponent(GUIContext container, char symbol, Font font) {
        super(container);
        this.symbol = symbol;
        this.font = font;
        this.idleAnimation = new Animation(frames, 120);
    }

    public static void init() throws SlickException {
        frames = new Image[8];
        for (int i = 0; i < 8; i++) {
            frames[i] = new Image("animations/flippedTiles/flippedTile_" + i + ".png");
        }
    }

    @Override
    public Shape getShapeBounds() {
        return null;
    }

    @Override
    public void renderImpl(GUIContext container, Graphics g) {
//        g.drawAnimation();
        g.setFont(font);
        g.setColor(Color.red);
        g.setLineWidth(5.0f);

        g.drawRoundRect(x, y, 80, 80, 10);
        g.drawString(Zalgo.convert(String.valueOf(symbol)), x + 40, y + 40);
    }
}
