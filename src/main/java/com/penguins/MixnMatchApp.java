package com.penguins;

import com.google.common.util.concurrent.Uninterruptibles;
import com.penguins.text.Zalgo;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;

import java.util.concurrent.TimeUnit;
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
            appgc.setShowFPS(true);
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
        font = unicodeFont;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        gameContainer.setTargetFrameRate(10);
        graphics.setFont(font);
        graphics.setColor(Color.red);
        String toBlit = Zalgo.convert("Hello world!");
        System.out.println(toBlit);
        graphics.drawString(toBlit, 100.0f, 100.0f);
        Uninterruptibles.sleepUninterruptibly(20, TimeUnit.MILLISECONDS);
    }
}