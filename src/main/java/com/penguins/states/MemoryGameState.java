package com.penguins.states;

import com.penguins.GameController;
import com.penguins.components.TileComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MemoryGameState extends BasicGameState {
    public static final int ID = 1;
    private final GameController gc;
    private TileComponent[][] tileComponents;

    public MemoryGameState(GameController controller) {
        this.gc = controller;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        char[][] boardTiles = gc.getBoard().getTiles();
        tileComponents = new TileComponent[boardTiles.length][boardTiles[0].length];

        for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[0].length; j++) {
                TileComponent component = new TileComponent(container, boardTiles[i][j]);
                int x = 200 + j * (80 + 10);
                int y = 100 + i * (80 + 10);
                component.setLocation(x, y);
                tileComponents[i][j] = component;
            }
        }
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        container.getInput().removeAllListeners();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (int i = 0; i < tileComponents.length; i++) {
            for (int j = 0; j < tileComponents[0].length; j++) {
                tileComponents[i][j].render(container, g);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
}
