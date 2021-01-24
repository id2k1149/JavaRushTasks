package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.*;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;

    @Override
    public void initialize() {
        //  Создаем игровое поле
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    // действия, которые нужно выполнить для создания игры
    private void createGame(){
        drawScene();
    }

    private void drawScene(){
        //  Меняем фон
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                setCellColor(x, y, Color.DARKSEAGREEN);
            }
        }
    }
}
