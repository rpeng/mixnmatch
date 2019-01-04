package com.penguins;

import com.penguins.font.FontLoader;
import com.penguins.model.Board;
import com.penguins.sound.SoundController;
import org.newdawn.slick.SlickException;

public class GameController {
    private Board board;
    private SoundController soundController;
    private FontLoader fontLoader;

    public GameController() {
        this.board = Board.randBoard(2, 4);
        this.soundController = new SoundController();
        this.fontLoader = new FontLoader();
    }

    public SoundController getSoundController() {
        return soundController;
    }

    public FontLoader getFontLoader(){
        return fontLoader;
    }

    public Board getBoard() {
        return board;
    }

    public void init() throws SlickException {
        soundController.loadSounds();
        fontLoader.loadFonts();
    }
}
