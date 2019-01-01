package com.penguins;

import com.penguins.model.Board;

public class GameController {
    private Board board;

    public GameController() {
        this.board = Board.randBoard(4, 5);
    }

    public Board getBoard() {
        return board;
    }
}
