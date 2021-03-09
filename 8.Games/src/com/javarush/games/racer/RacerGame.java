package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;

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
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
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
    }

    @Override
    public void onTurn(int step) {
        moveAll();
        drawScene();
    }
}
