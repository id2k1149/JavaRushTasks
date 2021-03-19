package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    public static final int COMPLEXITY = 5;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private List<Bullet> playerBullets;
    private static final int PLAYER_BULLETS_MAX = 1;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        playerBullets = new ArrayList<>();
        score = 0;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();

        for (Bullet each: playerBullets) {
            each.draw(this);
        }

        playerShip.draw(this);

        for (Bullet each: enemyBullets) {
            each.draw(this);
        }

        enemyFleet.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.DARKGREEN, "");
            }
        }
        for (Star each: stars) {
            each.draw(this);
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8 ; i++) {
            double randX = Math.random() * WIDTH;
            double randY = Math.random() * HEIGHT;
            Star star = new Star(randX, randY);
            stars.add(star);
        }
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        for (Bullet each: enemyBullets) {
            each.move();
        }
        for (Bullet each: playerBullets) {
            each.move();
        }
        playerShip.move();
    }

    private void removeDeadBullets() {
        List<Bullet> copyList = enemyBullets;
        for (int i = 0; i < copyList.size(); i++) {
            if ((copyList.get(i).y >= HEIGHT - 1) || copyList.get(i).isAlive == false) {
                enemyBullets.remove(i);
            }
        }

        /*
        Iterator<Bullet> iterator = playerBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if ((bullet.y + bullet.height < 0) || (!bullet.isAlive)) {
                iterator.remove();
            }
        }
        */

        playerBullets.removeIf(bullet -> (bullet.y + bullet.height < 0) || (!bullet.isAlive));
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
//        enemyFleet.verifyHit(playerBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if (enemyFleet.getBottomBorder() >= playerShip.y) {
            playerShip.kill();
        }
        if (!playerShip.isAlive) {
            stopGameWithDelay();
        }
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) {
            showMessageDialog(Color.WHITE, "YOU WON !!!", Color.GREEN, 70);
        }
        else {
            showMessageDialog(Color.WHITE, "GAME OVER", Color.RED, 70);
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }

    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) enemyBullets.add(bullet);
        setScore(score);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {

        if (key == Key.SPACE && isGameStopped) {
            createGame();
            return;
        }

        if (key == Key.LEFT) {
            playerShip.setDirection(Direction.LEFT);
        }

        if (key == Key.RIGHT) {
            playerShip.setDirection(Direction.RIGHT);
        }

        if (key == Key.SPACE) {
            Bullet bullet = playerShip.fire();
            if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                playerBullets.add(bullet);
            }
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.LEFT && playerShip.getDirection() == Direction.LEFT) {
            playerShip.setDirection(Direction.UP);
        }
        if (key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT) {
            playerShip.setDirection(Direction.UP);
        }
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if ((x < 0) || (y < 0) || (x >= WIDTH) || (y >= HEIGHT)) {
            return;
        }
        super.setCellValueEx(x, y, cellColor, value);
    }
}
