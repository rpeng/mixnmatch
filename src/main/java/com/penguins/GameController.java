package com.penguins;

import com.penguins.model.Board;
import com.penguins.sound.SoundController;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameController {
    private Board board;
    private SoundController soundController;

    public GameController() {
        this.board = Board.randBoard(2, 4);
        this.soundController = new SoundController();
    }

    public SoundController getSoundController() {
        return soundController;
    }

    public Board getBoard() {
        return board;
    }

    public void init() throws SlickException {
        soundController.loadSounds();
    }
}
