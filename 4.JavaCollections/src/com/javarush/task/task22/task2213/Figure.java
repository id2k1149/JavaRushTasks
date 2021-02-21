package com.javarush.task.task22.task2213;

public class Figure {
    private int x; //  координаты
    private int y;
    private int[][] matrix; // форма

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public void left() {
        x--;
        if (!isCurrentPositionAvailable()) x++;
    }

    public void right() {
        x++;
        if (!isCurrentPositionAvailable()) x--;
    }

    public void down() {
        y++;
    }

    public void up() {
        y--;
    }

    // для поворота фигурки вокруг главной диагонали.
    public void rotate() {

    }

    // падение фигурки вниз до дна.
    public void downMaximum() {

    }

    // проверка - может ли фигурка быть помещена в текущую позицию.
    public boolean isCurrentPositionAvailable() {
        return true;
    }

    // когда фигурка достигла дна или уперлась в другую фигурку
    public void landed() {

    }
}
