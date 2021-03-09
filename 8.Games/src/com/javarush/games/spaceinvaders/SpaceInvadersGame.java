package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    public static final int COMPLEXITY = 5;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;

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
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
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
    }

    private void removeDeadBullets() {
        List<Bullet> copyList = enemyBullets;
        for (int i = 0; i < copyList.size(); i++) {
            if ((copyList.get(i).y >= HEIGHT - 1) || copyList.get(i).isAlive == false) {
                enemyBullets.remove(i);
            }
        }

        /*
        Iterator<Bullet> iterator = enemyBullets.iterator();

        while (iterator.hasNext()) {
            Bullet tmp = iterator.next();
            if ((tmp.y >= HEIGHT - 1) || (tmp.isAlive == false)) {
                iterator.remove();
            }

        }
         */
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        removeDeadBullets();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) enemyBullets.add(bullet);
        drawScene();
    }
}
