package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.ShapeMatrix;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;

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
}
