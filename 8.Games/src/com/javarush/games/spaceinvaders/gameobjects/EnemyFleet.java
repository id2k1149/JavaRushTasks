package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

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
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
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
        if (ships.size() == 0) {
            return;
        }
        else {
            boolean directionChanged = false;
            double speed = getSpeed();
            if (direction == Direction.LEFT && getLeftBorder() < 0) {
                direction = Direction.RIGHT;
                directionChanged = true;
            }
            if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
                direction = Direction.LEFT;
                directionChanged = true;
            }
            if (directionChanged) {
                for (EnemyShip each: ships) {
                    each.move(Direction.DOWN,speed);
                }
            }
            else {
                for (EnemyShip each: ships) {
                    each.move(direction,speed);
                }
            }
        }
        /*
         if (ships.isEmpty()) {
            return;
        }

        Direction currentDirection = direction;
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            currentDirection = Direction.DOWN;
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            currentDirection = Direction.DOWN;
        }

        double speed = getSpeed();
        for (EnemyShip ship : ships) {
            ship.move(currentDirection, speed);
        }
         */
    }

    public Bullet fire(Game game) {
        if (ships.isEmpty()) {
            return null;
        }
        if (game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) > 0) {
            return null;
        }
        int shipNumber = game.getRandomNumber(ships.size());
        EnemyShip ship = ships.get(shipNumber);
        return ship.fire();
    }

    /*
    public void verifyHit(List<Bullet> bullets) {
        for (EnemyShip ship: ships) {
            for (Bullet bullet: bullets) {
                if (ship.isCollision(bullet) && ship.isAlive && bullet.isAlive) {
                    ship.kill();
                    bullet.kill();
                }
            }
        }
    }
     */

    public void deleteHiddenShips() {
        for (EnemyShip ship : new ArrayList<>(ships)) {
            if (!ship.isVisible()) {
                ships.remove(ship);
            }
        }
    }

    public double getBottomBorder() {
        double max = 0;
        for (EnemyShip ship: ships) {
          if ((ship.y + ship.height) > max) max = ship.y + ship.height;
        }
        return max;
    }

    public int getShipsCount() {
        return ships.size();
    }

    public int verifyHit(List<Bullet> bullets) {
        int result = 0;
        if (bullets.size() == 0) return 0;
        for (EnemyShip ship: ships) {
            for (Bullet bullet: bullets) {
                if (ship.isCollision(bullet) && ship.isAlive && bullet.isAlive) {
                    ship.kill();
                    bullet.kill();
                    result += ship.score;
                }
            }
        }
        return result;
    }
}
