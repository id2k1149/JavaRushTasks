package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
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

    @Override
    public void onTurn(int step) {
        drawScene();
    }
}
