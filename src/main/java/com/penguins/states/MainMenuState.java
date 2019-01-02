package com.penguins.states;

import com.penguins.GameController;
import com.penguins.components.MenuButtonComponent;
import com.penguins.sound.SoundController;
import com.penguins.sound.SoundEffect;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.font.effects.ShadowEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;

import static com.penguins.sound.SoundEffect.*;

public class MainMenuState extends BasicGameState {
    public static final int ID = 0;
    public static final int MENU_OPTION_WIDTH = 30;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private final GameController gc;
    private UnicodeFont optionsFont;
    private UnicodeFont highlightedFont;

    private Image background;
    private Image title;
    private Animation card[] = new Animation[4];

    private Image testCard;

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
        InputStream fontFile = ResourceLoader.getResourceAsStream("font.ttf");
        try {
            java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontFile);
//            java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 22);
            optionsFont = new UnicodeFont(font.deriveFont(28f));
            optionsFont.addAsciiGlyphs();
//            optionsFont.getEffects().add(new OutlineEffect(3, java.awt.Color.black));
            optionsFont.getEffects().add(new ShadowEffect(java.awt.Color.black, 4, 4, 0.7f));
            optionsFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            optionsFont.loadGlyphs();

            highlightedFont = new UnicodeFont(font.deriveFont(32f));
            highlightedFont.addAsciiGlyphs();
            highlightedFont.getEffects().add(new ShadowEffect(java.awt.Color.black, 4, 4, 0.7f));
            highlightedFont.getEffects().add(new ColorEffect(new java.awt.Color(255, 90, 255)));
            highlightedFont.loadGlyphs();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        newGame = createMenuButton(container, "New game", HOVER_C);
        settings = createMenuButton(container, "Settings", HOVER_D);
        credits = createMenuButton(container, "Credits", HOVER_E);
        exit = createMenuButton(container, "Exit", HOVER_C);

        newGame.addListener((src) -> game.enterState(MemoryGameState.ID));
        exit.addListener((src) -> container.exit());

        newGame.setLocation(180, 400);
        settings.setLocation(180, 400 + MENU_OPTION_WIDTH);
        credits.setLocation(180, 400 + MENU_OPTION_WIDTH * 2);
        exit.setLocation(180, 400 + MENU_OPTION_WIDTH * 3);


        // Images
        background = new Image("background_dark.png");
        title = new Image("menu_title.png");
        testCard = new Image("tiles/tileOtamatoneHappy.png");

        // Card
        Image frames[] = new Image[8];
        for (int i = 0; i < 8; i++) {
            frames[i] = new Image("animations/flippedTiles/flippedTile_" + i + ".png");
        }

        for (int i = 0; i < 4; i++) {
            card[i] = new Animation(frames, 120);
        }

        x1 = 0;
        x2 = 799;

        titleY = 80;
    }

    private MenuButtonComponent createMenuButton(GameContainer container, String title, SoundEffect hoverEffect) {
        SoundController sc = gc.getSoundController();
        MenuButtonComponent menuButton = new MenuButtonComponent(container, title, optionsFont, highlightedFont);

        menuButton.setOnHover(() -> sc.playSoundEffect(hoverEffect));

        menuButton.addListener((src) -> sc.playSoundEffect(SELECT));
        return menuButton;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
//        gc.getSoundController().playSoundtrack(TITLE);
        container.getInput().addPrimaryListener(newGame);
        container.getInput().addPrimaryListener(exit);
        container.getInput().addPrimaryListener(settings);
        container.getInput().addPrimaryListener(credits);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        gc.getSoundController().fadeOut();
        container.getInput().removeAllListeners();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(background, x1, 0);
        g.drawImage(background, x2, 0);
        title.drawCentered(WINDOW_WIDTH / 2.0f, titleY);

        for (int i = 0; i < 4; i++) {
            card[i].draw(215 + (i * 100), 225);
        }

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
