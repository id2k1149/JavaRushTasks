package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame_15 extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        List<GameObject> listOfNeighbors;
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (!gameField[y][x].isMine) {
                    listOfNeighbors = getNeighbors(gameField[y][x]);
                    for (GameObject each: listOfNeighbors) {
                        if (each.isMine) gameField[y][x].countMineNeighbors++;
                    }
                }
            }
        }
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME IS OVER", Color.BLUE, 40);
    }

    private void openTile(int x, int y) {

        if (gameField[y][x].isFlag || gameField[y][x].isOpen || isGameStopped == true) {
            return;
        } else  {
            if (gameField[y][x].isMine) {
                setCellValueEx(x, y, Color.RED, MINE);
                gameOver();
            }
            else {
                int minesNumber = gameField[y][x].countMineNeighbors;
                setCellNumber(x, y, minesNumber);
                if (minesNumber == 0) {
                    setCellValue(x, y, "");
                    gameField[y][x].isOpen = true;
                    setCellColor(x, y, Color.GREEN);
                    for (GameObject each: getNeighbors(gameField[y][x])) {
                        if (!each.isOpen) {
                            openTile(each.x, each.y);
                            each.isOpen = true;
                            setCellColor(each.x, each.y, Color.GREEN);
                        }
                    }
                }
                countClosedTiles--;
                score += 5;
                setScore(score);
                gameField[y][x].isOpen = true;
                setCellColor(x, y, Color.GREEN);
            }
        }
        if (countClosedTiles == countMinesOnField) {
            win();
        }
    }

    private void markTile(int x, int y) {
        if (isGameStopped) return;

        if (gameField[y][x].isOpen) return;

        if (!gameField[y][x].isFlag && countFlags == 0) return;

        if (gameField[y][x].isFlag) {
            gameField[y][x].isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.ORANGE);
        }
        else {
            gameField[y][x].isFlag = true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU WIN", Color.BLACK, 50);

    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
        }
        else openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }
}