package com.javarush.games.game2048;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private  int[][] gameField = new int[SIDE][SIDE];

    @Override
    public void initialize() {
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
