package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        // Integer.compare(i, j)  дает
        // 1 если i > j,
        // 0 если  i = j и
        // -1 если i < j
        int result = Integer.compare(numberOfEmptyTiles, o.numberOfEmptyTiles);
        if (result != 0) return result;

        return Integer.compare(score,o.score);
    }
}
