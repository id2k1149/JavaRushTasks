package com.javarush.games.snake;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;

    @Override
    public void initialize() {
        //  Создаем игровое поле
        setScreenSize(WIDTH, HEIGHT);
        createGame();

        //  Выключаем отображение сетки
//        showGrid(false);

        //  Меняем фон центральной клетки на синий, и отображаем в ней “Х”
//        setCellValueEx(1, 1, Color.BLUE, "X", Color.ORANGE, 50);

    }

    // Всё, что должно происходить в игре
    // на протяжении одного хода, описывается здесь
    @Override
    public void onTurn(int step) {
        snake.move();
        drawScene();
    }

    // действия, которые нужно выполнить для создания игры
    private void createGame(){
        Snake snake = new Snake(WIDTH / 2,HEIGHT / 2);
        this.snake = snake;
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void drawScene(){
        //  Меняем фон
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
    }

}
