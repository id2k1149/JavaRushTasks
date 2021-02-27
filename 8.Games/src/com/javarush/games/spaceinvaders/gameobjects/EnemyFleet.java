package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int y = 0; y < ROWS_COUNT; y++) {
            for (int x = 0; x < COLUMNS_COUNT ; x++) {
                EnemyShip enemyShip = new EnemyShip(x * STEP, y * STEP + 12);
                ships.add(enemyShip);
            }
        }
    }

    public void draw(Game game) {
        for (EnemyShip each: ships) {
            each.draw(game);
        }
    }

    private double getLeftBorder() {
        double leftBorder = ships.get(0).x;
        for (EnemyShip each: ships) {
            if (each.x < leftBorder) leftBorder = each.x;
        }
        return leftBorder;
    }

    private double getRightBorder() {
        double rightBorder = ships.get(0).x + ships.get(0).width;
        for (EnemyShip each: ships) {
           if ((each.x + each.width) > rightBorder) rightBorder = each.x + each.width;
        }
        return rightBorder;
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }

    public void move() {
    }
}
