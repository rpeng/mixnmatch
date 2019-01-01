package com.penguins.states;

import com.penguins.GameController;
import com.penguins.components.MenuButtonComponent;
import com.penguins.sound.Song;
import com.penguins.sound.SoundEffect;
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
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private final GameController gc;
    private Font titleFont;
    private UnicodeFont optionsFont;

    private Image background;
    private Image title;

    private float x1, x2;
    private float titleY;
    private float floatTick = 0;

    private MenuButtonComponent newGame;
    private MenuButtonComponent settings;
    private MenuButtonComponent credits;
    private MenuButtonComponent exit;

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

        newGame = new MenuButtonComponent(container, "New game", optionsFont);
        settings = new MenuButtonComponent(container, "Settings", optionsFont);
        credits = new MenuButtonComponent(container, "Credits", optionsFont);
        exit = new MenuButtonComponent(container, "Exit", optionsFont);

        newGame.addListener((src) -> game.enterState(MemoryGameState.ID));
        newGame.addListener((src) -> gc.getSoundController().playSoundEffect(SoundEffect.SELECT));

        exit.addListener((src) -> container.exit());
        exit.addListener((src) -> gc.getSoundController().playSoundEffect(SoundEffect.SELECT));

        newGame.setLocation(180, 400);
        settings.setLocation(180, 400 + MENU_OPTION_WIDTH);
        credits.setLocation(180, 400 + MENU_OPTION_WIDTH * 2);
        exit.setLocation(180, 400 + MENU_OPTION_WIDTH * 3);


        // Images
        background = new Image("background.png");
        title = new Image("menu_title.png");

        x1 = 0;
        x2 = 799;

        titleY = 80;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {

//        gc.getSoundController().playSoundtrack(Song.TITLE);

        container.getInput().addPrimaryListener(newGame);
        container.getInput().addPrimaryListener(exit);
        container.getInput().addPrimaryListener(settings);
        container.getInput().addPrimaryListener(credits);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        if (gc.getSoundController().getCurrentSong() != null)
            gc.getSoundController().getCurrentSong().fade(100, 0, true);
        container.getInput().removeAllListeners();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setFont(titleFont);
        g.drawImage(background, x1, 0);
        g.drawImage(background, x2, 0);
        title.drawCentered(WINDOW_WIDTH / 2, titleY);
//        g.drawString("Mix-n-Match!", 180, 100);

        g.setFont(optionsFont);
        newGame.render(container, g);
        settings.render(container, g);
        credits.render(container, g);
        exit.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        // Sliding background
        x1--;
        x2--;
        if (x1 <= -799) {
            x1 = 799;
        } else if (x2 <= -799) {
            x2 = 799;
        }

        // Title float
        float curWave = (float) (Math.sin(floatTick) * 10);
        floatTick += 0.1;

        titleY = 120 + curWave;
    }
}
