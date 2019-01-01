package com.penguins.states;

import com.penguins.GameController;
import com.penguins.components.MenuButton;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;

public class MainMenuState extends BasicGameState {
    public static final int ID = 0;
    public static final int MENU_OPTION_WIDTH = 30;

    private final GameController gc;
    private Font titleFont;
    private UnicodeFont optionsFont;
    private Music music;
    private Image background;
    private float x1, x2;

    private MenuButton newGame;
    private MenuButton settings;
    private MenuButton credits;
    private MenuButton exit;

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
            optionsFont = new UnicodeFont(font.deriveFont(20f));
            optionsFont.addAsciiGlyphs();
            optionsFont.getEffects().add(new OutlineEffect(2, java.awt.Color.black));
            optionsFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            optionsFont.loadGlyphs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        music = new Music("normal_menu.ogg");

        newGame = new MenuButton(container, "New game", optionsFont);
        settings = new MenuButton(container, "Settings", optionsFont);
        credits = new MenuButton(container, "Credits", optionsFont);
        exit = new MenuButton(container, "Exit", optionsFont);

        newGame.addListener((src) -> game.enterState(MemoryGameState.ID));
        exit.addListener((src) -> container.exit());

        newGame.setLocation(180, 400);
        settings.setLocation(180, 400 + MENU_OPTION_WIDTH);
        credits.setLocation(180, 400 + MENU_OPTION_WIDTH * 2);
        exit.setLocation(180, 400 + MENU_OPTION_WIDTH * 3);
        background = new Image("background.png");

        x1 = 0;
        x2 = 799;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
//        music.loop();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
//        music.stop();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setFont(titleFont);
        g.drawImage(background, x1, 0);
        g.drawImage(background, x2, 0);
        g.drawString("Mix-n-Match!", 180, 100);

        g.setFont(optionsFont);
        newGame.render(container, g);
        settings.render(container, g);
        credits.render(container, g);
        exit.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        x1--;
        x2--;
        if (x1 <= -799) {
            x1 = 799;
        }else if (x2 <= -799) {
            x2 = 799;
        }
    }
}
