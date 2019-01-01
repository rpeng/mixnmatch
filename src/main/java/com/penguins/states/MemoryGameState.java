package com.penguins.states;

import com.penguins.GameController;
import com.penguins.text.Zalgo;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static java.awt.Font.PLAIN;

public class MemoryGameState extends BasicGameState {
    public static final int ID = 0;
    private final GameController gc;
    private Font font;
    private Music music;

    public MemoryGameState(GameController controller) {
        this.gc = controller;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        font = loadUnicodeFonts(new java.awt.Font("Arial", PLAIN, 20));
        music = new Music("normal_menu.ogg");
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        music.loop();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        music.stop();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setFont(font);
        g.setColor(Color.red);
        g.setLineWidth(5.0f);

        char[][] tiles = gc.getBoard().getTiles();
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                int x = 200 + col * (80 + 10);
                int y = 100 + row * (80 + 10);
                g.drawRoundRect(x, y, 80, 80, 10);
                g.drawString(Zalgo.convert(String.valueOf(tiles[row][col])), x + 40, y + 40);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    private UnicodeFont loadUnicodeFonts(java.awt.Font arial) throws SlickException {
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
}
