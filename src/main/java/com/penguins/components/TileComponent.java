package com.penguins.components;

import com.penguins.text.Zalgo;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;

public class TileComponent extends MouseOverComponent {
    private static Image[] idleFrames;
    private static Font font;
    private final char symbol;
    private final Animation idleAnimation;

    public TileComponent(GUIContext container, char symbol) {
        super(container);
        this.symbol = symbol;
        this.idleAnimation = new Animation(idleFrames, 60);
        this.idleAnimation.setLooping(true);
        this.idleAnimation.stop();
    }

    public static void init() throws SlickException {
        idleFrames = new Image[8];

        for (int i = 0; i < 8; i++) {
            idleFrames[i] = new Image("animations/flippedTiles/flippedTile_" + i + ".png");
        }

        font = loadUnicodeFonts(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));
    }

    private static UnicodeFont loadUnicodeFonts(java.awt.Font arial) throws SlickException {
        UnicodeFont unicodeFont = new UnicodeFont(arial);
        unicodeFont.addAsciiGlyphs();
        unicodeFont.addNeheGlyphs();
        for (Character character : Zalgo.zalgo_up) {
            System.out.println("Loading zalgo " + character);
            unicodeFont.addGlyphs(character.toString());
        }
        for (Character character : Zalgo.zalgo_down) {
            System.out.println("Loading zalgo " + character);
            unicodeFont.addGlyphs(character.toString());
        }
        unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        unicodeFont.loadGlyphs();
        return unicodeFont;
    }

    @Override
    protected void onHoverStart() {
        this.idleAnimation.stopAt(-2);
        this.idleAnimation.start();
    }

    @Override
    protected void onHoverEnd() {
        this.idleAnimation.stopAt(0);
    }

    @Override
    public Shape getShapeBounds() {
        return new Rectangle(getX(), getY(), idleAnimation.getWidth(), idleAnimation.getHeight());
    }

    @Override
    public void renderImpl(GUIContext container, Graphics g) {
        g.drawAnimation(idleAnimation, x, y);
//        g.setFont(font);
//        g.setColor(Color.red);
//        g.setLineWidth(5.0f);
//
//        g.drawRoundRect(x, y, 80, 80, 10);
//        g.drawString(Zalgo.convert(String.valueOf(symbol)), x + 40, y + 40);
    }
}
