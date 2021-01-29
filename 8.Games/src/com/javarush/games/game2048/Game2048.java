package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
//    private int[][] gameField =
//    {
//        {  2,    4,    8,  16},
//        { 32,   64,  128, 256},
//        {512, 1024, 2048,   0},
//        {  2,    4,    8,  16}
//    };

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        //  Меняем фон
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                int value = gameField[y][x];
//                 setCellColor(x, y, Color.GRAY);
                setCellColoredNumber(x, y, value);
            }
        }
    }

    private void createNewNumber() {
        int randomX, randomY, randomNumber;
        do {
            randomX = getRandomNumber(SIDE);
            randomY = getRandomNumber(SIDE);
        }
        while (gameField[randomX][randomY] != 0);

        randomNumber = getRandomNumber(10);
        if (randomNumber == 9) gameField[randomX][randomY] = 4;
        else gameField[randomX][randomY] = 2;

    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0: return Color.WHITE;
            case 2: return Color.LIGHTPINK;
            case 4: return Color.BLUEVIOLET;
            case 8: return Color.BLUE;
            case 16: return Color.CYAN;
            case 32: return Color.LIGHTSEAGREEN;
            case 64: return Color.LIMEGREEN;
            case 128: return Color.ORANGE;
            case 256: return Color.INDIANRED;
            case 512: return Color.RED;
            case 1024: return Color.MAGENTA;
            case 2048: return Color.MEDIUMVIOLETRED;
        }
        return Color.WHITE;
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color cellColor = getColorByValue(value);
        if (value == 0) {
            setCellValueEx(x, y, cellColor, "");
        }
        else {
            setCellValueEx(x, y, cellColor, Integer.toString(value));
        }
    }
}
