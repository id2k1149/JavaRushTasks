package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private static final int RACE_GOAL_CARS_COUNT = 40;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
            super.setCellColor(x, y, color);
        }
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        isGameStopped = false;
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        progressBar.draw(this);
        finishLine.draw(this);
        roadManager.draw(this);
        roadMarking.draw(this);
        player.draw(this);

    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (x == CENTER_X) {
                    setCellColor(CENTER_X, y, Color.WHITE);
                }
                else if (x >= ROADSIDE_WIDTH && x < (WIDTH - ROADSIDE_WIDTH)) {
                    setCellColor(x, y, Color.DIMGREY);
                }
                else {
                    setCellColor(x, y, Color.GREEN);
                }
            }
        }
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        player.move();
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "GAME OVER", Color.BLUE, 70);
        stopTurnTimer();
        player.stop();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU WON", Color.BLUE, 70);
        stopTurnTimer();
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        roadManager.generateNewRoadObjects(this);
        if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {
            finishLine.show();
        }
        if (finishLine.isCrossed(player)) {
            win();
            drawScene();
            return;
        }
        moveAll();
        score -= 5;
        setScore(score);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case RIGHT: {
                player.setDirection(Direction.RIGHT);
                break;
            }
            case LEFT: {
                player.setDirection(Direction.LEFT);
                break;
            }
            case UP: {
                player.speed = 2;
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
        if (key.equals(Key.RIGHT)
                && player.getDirection().equals(Direction.RIGHT)) {
            player.setDirection(Direction.NONE);
        }
        if (key.equals(Key.LEFT)
                && player.getDirection().equals(Direction.LEFT)) {
            player.setDirection(Direction.NONE);
        }
        if (key.equals(Key.UP)) player.speed = 1;
    }
}
