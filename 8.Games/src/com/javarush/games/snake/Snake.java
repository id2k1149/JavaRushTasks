package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
//    private static final String SNAKE_SIGN = "\uD83C\uDF4E";
//
//    public void draw(Game game) {
//        for (GameObject each: snakeParts) {
//            game.setCellValueEx(each.x, each.y, Color.NONE, SNAKE_SIGN, Color.RED, 75);
//        }
//    }

    public Snake(int x, int y) {
       GameObject part1 = new GameObject(x, y);
       snakeParts.add(part1);
       GameObject part2 = new GameObject(x + 1, y);
       snakeParts.add(part2);
       GameObject part3 = new GameObject(x + 2, y);
       snakeParts.add(part3);
    }
}
