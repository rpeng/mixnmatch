package com.penguins;

import com.penguins.states.MemoryGameState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Logger;

public class MixnMatchApp extends StateBasedGame {
    private static final Logger logger = Logger.getLogger(MixnMatchApp.class.getName());

    private GameController gameController;

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new MixnMatchApp());
            appgc.setDisplayMode(800, 600, false);
            appgc.setShowFPS(true);
            appgc.setAlwaysRender(true);
            appgc.setTargetFrameRate(30);
            appgc.start();
        } catch (SlickException ex) {
            logger.info(ex.toString());
        }

    }

    public MixnMatchApp() {
        super("Mix and Match!");
        this.gameController = new GameController();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MemoryGameState(gameController));
    }
}