package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private GameObject platform;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    private void createGame() {
        createGameObjects();
        drawScene();
        setTurnTimer(50);
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        isGameStopped = false;
        score = 1000;
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i, j, Color.BLACK);
            }
        }
        landscape.draw(this);
        rocket.draw(this);
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    private void check() {
        if (rocket.isCollision(landscape)) gameOver();
        if (rocket.isCollision(platform)
                && rocket.isStopped()) win();
    }

    private void win() {
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU WON", Color.BLUE, 70);
        stopTurnTimer();
    }

    private void gameOver() {
        rocket.crash();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "GAME OVER", Color.BLUE, 70);
        stopTurnTimer();
        score = 0;
    }


    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        if (score > 0) score--;
        setScore(score);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH  && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP: {
                isUpPressed = true;
                break;
            }
            case LEFT: {
                isLeftPressed = true;
                isRightPressed = false;
                break;
            }
            case RIGHT: {
                isRightPressed = true;
                isLeftPressed = false;
                break;
            }
            case SPACE: {
                if (isGameStopped) createGame();
                break;
            }
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP: {
                isUpPressed = false;
                break;
            }
            case LEFT: {
                isLeftPressed = false;
                break;
            }
            case RIGHT: {
                isRightPressed = false;
                break;
            }
        }
    }
}
