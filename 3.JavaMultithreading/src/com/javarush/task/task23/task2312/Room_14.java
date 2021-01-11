package com.javarush.task.task23.task2312;

import java.awt.event.KeyEvent;

public class Room_14 {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public static Room_14 game;

    public Room_14(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /**
     * Основной цикл программы.
     * Тут происходят все важные действия
     */
    public void run() throws InterruptedException {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //пока змея жива
        while (snake.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                    //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                    //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   //двигаем змею
            print();        //отображаем текущее состояние игры
            sleep();        //пауза между ходами
        }

        System.out.println("Game Over!");
    }

    public void print() {
        //Создаем массив, куда будем "рисовать" текущее состояние игры
        int[][] screen = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0) screen[i][j] = 8;
                if (j == 0) screen[i][j] = 8;
                if (i == height - 1) screen[i][j] = 8;
                if (j == width - 1) screen[i][j] = 8;
            }
        }

        //Рисуем все кусочки змеи
        int x_head = game.snake.getX();
        int y_head = game.snake.getY();
        screen[y_head][x_head] = 2;

        for (int i = 1; i < game.snake.getSections().size(); i++) {
            screen[game.snake.getSections().get(i).getY()][game.snake.getSections().get(i).getX()] = 1;
        }

        //Рисуем мышь
        int x_mouse = game.mouse.getX();
        int y_mouse = game.mouse.getY();
        screen[y_mouse][x_mouse] = 3;

        //Выводим все это на экран
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
//                System.out.print(screen[i][j]);
                if (screen[i][j] == 8) System.out.print(".");
                if (screen[i][j] == 0) System.out.print(" ");
                if (screen[i][j] == 1) System.out.print("x");
                if (screen[i][j] == 2) System.out.print("X");
                if (screen[i][j] == 3) System.out.print("^");
            }
            System.out.println();
        }
    }

    public void eatMouse() {
        createMouse();
    }

    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        mouse = new Mouse(x, y);
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Room_14(30, 10, new Snake(10, 5));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
        game.print();
    }

    /**
     * Программа делает паузу, длинна которой зависит от длины змеи.
     */
    public void sleep() throws InterruptedException {
        // делаем паузу, длинна которой зависит от длинны змеи
        int level = snake.getSections().size();
        int time1 = 520 - 20 * level;
        int time2 = 200 + 25 * (15 - level);
        if (level <= 11) Thread.sleep(time1);
        else if (level <= 15) Thread.sleep(time2);
        else Thread.sleep(200);
    }

    //java
//    private int initialDelay = 520;
//    private int delayStep = 20;
//    public void sleep() {
//        try {
//            int level = snake.getSections().size();
//            int delay = level < 15 ? (initialDelay - delayStep * level) : 200;
//            Thread.sleep(delay);
//        } catch (InterruptedException e) {
//        }
//    }
}
