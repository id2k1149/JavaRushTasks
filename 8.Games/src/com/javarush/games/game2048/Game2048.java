package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;

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
                setCellColoredNumber(x, y, value);
            }
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU WIN", Color.BLUE, 70);
    }

    private void createNewNumber() {
        if (getMaxTileValue() == 128) win();
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

    private int getMaxTileValue() {
        int max = 0;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] > max) max = gameField[i][j];
            }
        }
        return max;
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

    private boolean compressRow(int[] row) {
        boolean changes = false;
        boolean repeat;
        do {
            repeat = false;
            for (int i = 0; i < row.length-1; i++) {
                if (row[i] == 0 && row[i+1] > 0) {
                    row[i] = row[i+1];
                    row[i+1] = 0;
                    changes = true;
                    repeat = true;
                }
            }
        } while (repeat);
        return changes;
    }

    private boolean mergeRow(int[] row) {
        boolean changes = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == row[i + 1] && row[i] > 0) {
                row[i] *= 2;
                row[i + 1] = 0;
                changes = true;
            }
        }
        return changes;
    }

    private void rotateClockwise() {

        int[][] temp = new int[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                temp[i][j] = gameField[SIDE - 1 - j][i];
            }
        }

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                gameField[i][j] = temp[i][j];
            }
        }
    }

    private void moveLeft (){
        boolean change = false;
        for (int i = 0; i < SIDE; i++) {
            if ( compressRow( gameField[i] )) change = true;
            if ( mergeRow( gameField[i] )) change = true;
            if ( compressRow( gameField[i] )) change = true;
        }
        if( change ) createNewNumber();
    }

    private void moveRight (){
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp (){
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown (){
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT: {
                moveLeft();
                drawScene();
                break;
            }
            case RIGHT: {
                moveRight();
                drawScene();
                break;
            }
            case UP: {
                moveUp();
                drawScene();
                break;
            }
            case DOWN: {
                moveDown();
                drawScene();
                break;
            }
        }
    }
}
