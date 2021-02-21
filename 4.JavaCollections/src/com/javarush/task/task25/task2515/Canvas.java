package com.javarush.task.task25.task2515;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height][width];
    }

    // метод будет "ставить точку в координатах x,y цветом c".
    public void setPoint(double x, double y, char c) {
        int intX = (int) Math.round (x);
        int intY = (int) Math.round (y);
        if (intX >= 0 && intX < matrix[0].length
        && intY >= 0 && intY < matrix.length) matrix[intY][intX] = c;
    }

    //  метод копирует переданную ему картинку (матрицу) в матрицу Canvas.
    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        int intX = (int) Math.round (x);
        int intY = (int) Math.round (y);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) setPoint(intX+j, intY+i, c);
            }
        }
    }

    // метод будет очищать матрицу, чтобы на ней снова можно было рисовать.
    public void clear() {
        matrix = new char[height][width];

//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                matrix[i][j] = ' ';
//            }
//        }
    }

    // метод отрисовывает матрицу на экран
    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }
}
