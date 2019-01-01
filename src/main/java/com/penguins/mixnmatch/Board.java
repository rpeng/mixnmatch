package com.penguins.mixnmatch;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int ASCII_START = 33;
    private static final int ASCII_END = 126;

    private char[][] tiles;
    private int rows;
    private int cols;

    public Board(char[][] tiles) {
        this.tiles = tiles;
        this.rows = tiles.length;
        this.cols = tiles.length > 0 ? tiles[0].length : 0;
    }

    public static Board randBoard(int rows, int cols) {
        int asciiRange = (ASCII_END - ASCII_START) * 2;
        Preconditions.checkArgument(rows * cols < asciiRange,
                "Board is too big! %s x %s cant fit %s characters", rows, cols, asciiRange);

        List<Integer> indexes = IntStream.range(0, rows * cols).boxed().collect(Collectors.toList());
        List<Integer> characters = IntStream.range(ASCII_START, ASCII_END).boxed().collect(Collectors.toList());

        Collections.shuffle(indexes);
        Collections.shuffle(characters);

        char[][] tiles = new char[rows][cols];

        for (int i = 0; i < indexes.size(); i += 2) {
            int firstIndex = indexes.get(i);
            int secondIndex = indexes.get(i + 1);
            int character = characters.get(i);
            tiles[firstIndex / cols][firstIndex % cols] = (char) character;
            tiles[secondIndex / cols][secondIndex % cols] = (char) character;
        }

        return new Board(tiles);
    }

    public char[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                stringBuilder.append(tiles[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(Board.randBoard(3,4));
    }
}
