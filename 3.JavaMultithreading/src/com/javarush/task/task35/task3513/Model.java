package com.javarush.task.task35.task3513;

import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private static final int FIELD_WIDTH = 4; // размер поля
    private Tile[][] gameTiles; // игровое поле
    public int score; // счет
    public int maxTile; // значение макимальной плитки

    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (getEmptyTiles().size() > 0) {
            return true;
        }

        for (int i = 0; i < FIELD_WIDTH-1; i++) {
            for (int j = 0; j < FIELD_WIDTH-1; j++) {
                if (gameTiles[i][j].value == gameTiles[i+1][j].value
                        || gameTiles[i][j].value == gameTiles[i][j+1].value)
                    return true;
            }
        }

        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] gameToSave = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameToSave[i][j] = new Tile(tiles[i][j].value);
            }
        }

        // Метод saveState должен сохранять в стек previousStates
        // новый объект типа Tile[][] с помощью метода push.
        previousStates.push(gameToSave);

        // Метод saveState должен сохранять в стек previousScores
        // текущее значение поля score с помощью метода push.
        previousScores.push(score);

        isSaveNeeded = false;

    }

    public void rollback(){
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        score = 0;
        maxTile = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    public void randomMove() {
        int randomN = ((int) (Math.random() * 100)) % 4;
        switch(randomN) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        int weightGame = 0;
        for (int i = 0; i < gameTiles.length; i++){
            for (int j = 0; j < gameTiles[i].length; j++){
                weightGame += gameTiles[i][j].value;
            }
        }

        int weightStack = 0;
        Tile[][] tiles = (Tile[][])previousStates.peek();
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                weightStack += tiles[i][j].value;
            }
        }

        if (weightGame != weightStack)
            return true;
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        if (hasBoardChanged()){
            rollback();
            return new MoveEfficiency(getEmptyTiles().size(), score, move );
        }
        rollback();
        return new MoveEfficiency(-1, 0, move);
    }

    // метод будет выбирать лучший из возможных ходов и выполнять его
    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());

        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));

        if (queue.peek() != null) {
            queue.peek().getMove().move();
        }
    }

//    public void autoMove() {
//        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
//
//        queue.add(getMoveEfficiency(() -> left()));
//        queue.add(getMoveEfficiency(() -> right()));
//        queue.add(getMoveEfficiency(() -> up()));
//        queue.add(getMoveEfficiency(() -> down()));;
//
//        assert queue.peek() != null;
//        queue.peek().getMove().move();
//    }

//    private List<Tile> getEmptyTiles() {
//        List<Tile> emptyTilesList = new ArrayList<>();
//        for (int i = 0; i < FIELD_WIDTH; i++) {
//            for (int j = 0; j < FIELD_WIDTH; j++) {
//                if (gameTiles[j][i].isEmpty()) emptyTilesList.add(gameTiles[j][i]);
//            }
//        }
//        return emptyTilesList;
//    }

    // java
    private List<Tile> getEmptyTiles() {
        return Arrays.stream(gameTiles).
                flatMap(Arrays::stream).
                filter(Tile::isEmpty).
                collect(Collectors.toList());
    }

    private void addTile() {
        List<Tile> emptyTilesList = getEmptyTiles();
        if (emptyTilesList.size() > 0) {
            int randomIndex = (int) (emptyTilesList.size() * Math.random());
            int newValue = Math.random() < 0.9 ? 2 : 4;

            Tile tile = emptyTilesList.get(randomIndex);
            tile.value = newValue;
        }
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean changes = false;
        boolean repeat;
        do {
            repeat = false;
            for (int i = 0; i < tiles.length-1; i++) {
                if (tiles[i].value == 0 && tiles[i+1].value > 0) {
                    tiles[i].value = tiles[i+1].value;
                    tiles[i+1].value = 0;
                    repeat = true;
                    changes = true;
                }
            }
        } while (repeat);
        return changes;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changes = false;
        for (int i = 0; i < tiles.length-1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value > 0) {
                tiles[i].value *= 2;
                tiles[i + 1].value = 0;
                if (tiles[i].value > maxTile) maxTile = tiles[i].value;
                score += tiles[i].value;
                compressTiles(tiles);
                changes = true;
            }
        }
        return changes;
    }

    private void rotateClockwise(){

        Tile[][] temp = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                temp[i][j] = gameTiles[FIELD_WIDTH - 1 - j][i];
            }
        }

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = temp[i][j];
            }
        }
    }

    public void left (){
        if (isSaveNeeded) saveState(gameTiles);
        boolean changes = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if ( compressTiles(gameTiles[i]) || mergeTiles( gameTiles[i])) {
                changes = true;
            }
        }
        if (changes) addTile();
        isSaveNeeded = true;
    }

    public void right (){
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
    }

    public void up (){
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
    }

    public void down (){
        saveState(gameTiles);
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }
}
