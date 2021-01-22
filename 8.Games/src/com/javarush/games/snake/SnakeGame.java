package com.javarush.games.snake;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    @Override
    public void initialize() {

        //  Создаем игровое поле
        setScreenSize(WIDTH, HEIGHT);

        //  Выключаем отображение сетки
//        showGrid(false);

        //  Меняем фон центральной клетки на синий, и отображаем в ней “Х”
//        setCellValueEx(1, 1, Color.BLUE, "X", Color.ORANGE, 50);


    }

}
