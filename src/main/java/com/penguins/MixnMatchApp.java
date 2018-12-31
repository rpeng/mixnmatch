package com.penguins;

import org.newdawn.slick.*;

import java.util.logging.Logger;

public class MixnMatchApp extends BasicGame {

    public static final Logger logger = Logger.getLogger(MixnMatchApp.class.getName());

    public static void main(String[] args) {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new MixnMatchApp("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            logger.info(ex.toString());
        }

    }

    public MixnMatchApp(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }
}