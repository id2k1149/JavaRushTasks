package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.*;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;

    @Override
    public void initialize() {
        //  Создаем игровое поле
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    // действия, которые нужно выполнить для создания игры
    private void createGame(){
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                int randomNumber = getRandomNumber(10);
                boolean isMine;
                if (randomNumber == 0) {
                    countMinesOnField++;
                    isMine = true;
                }
                else isMine = false;
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
    }
}
