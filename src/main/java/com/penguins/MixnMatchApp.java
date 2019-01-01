package com.penguins;

import com.penguins.text.Zalgo;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;

import java.util.logging.Logger;

import static java.awt.Font.PLAIN;

public class MixnMatchApp extends BasicGame {

    public static final Logger logger = Logger.getLogger(MixnMatchApp.class.getName());

    private Font font;

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new MixnMatchApp("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException ex) {
            logger.info(ex.toString());
        }

    }

    public MixnMatchApp(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        java.awt.Font arial = new java.awt.Font("Arial", PLAIN, 20);
        UnicodeFont unicodeFont = new UnicodeFont(arial);
        unicodeFont.addAsciiGlyphs();
        unicodeFont.addNeheGlyphs();
        unicodeFont.addGlyphs(0, 2000);
        unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        unicodeFont.loadGlyphs();
        font = unicodeFont;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setFont(font);
        graphics.setColor(Color.red);
        String toBlit = Zalgo.convert("Hello world!");
        System.out.println(toBlit);
        graphics.drawString(toBlit, 100.0f, 100.0f);
    }
}