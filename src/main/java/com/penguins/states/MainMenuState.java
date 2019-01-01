package com.penguins.states;

import com.penguins.GameController;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;

public class MainMenuState extends BasicGameState {
    public static final int ID = 0;
    private final GameController gc;
    private Font titleFont;
    private Music music;

    public MainMenuState(GameController gameController) {
        this.gc = gameController;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        InputStream fontFile = ResourceLoader.getResourceAsStream("Chalkduster.ttf");
        try {
            java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontFile);
            titleFont = new TrueTypeFont(font.deriveFont(40f), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        g.setFont(titleFont);
        g.drawString("Mix-n-Match!", 180, 100);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
}
