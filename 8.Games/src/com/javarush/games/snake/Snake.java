package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
       GameObject part1 = new GameObject(x, y);
       snakeParts.add(part1);
       GameObject part2 = new GameObject(x + 1, y);
       snakeParts.add(part2);
       GameObject part3 = new GameObject(x + 2, y);
       snakeParts.add(part3);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void draw(Game game){
        Color snakeColor;
        if (isAlive) snakeColor = Color.BLACK;
        else snakeColor = Color.RED;

        game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, snakeColor, 75);
        for (int i = 1; i < snakeParts.size(); i++) {
            game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, snakeColor, 75);
        }
    }

    public void move() {
        if (isAlive) {
            GameObject newHead = createNewHead();
            if (newHead.x < 0 || newHead.x > SnakeGame.WIDTH - 1 ||
                    newHead.y < 0 || newHead.y > SnakeGame.HEIGHT - 1) isAlive = false;
            else {
                snakeParts.add(0, newHead);
                removeTail();
            }
        }
    }

    public  GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;;
        switch (direction) {
            case LEFT:
                return new GameObject(headX - 1, headY);
            case RIGHT:
                return new GameObject(headX + 1, headY);
            case UP:
                return new GameObject(headX, headY - 1);
            case DOWN:
                return new GameObject(headX, headY + 1);
            default: return null;
        }
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }
}
