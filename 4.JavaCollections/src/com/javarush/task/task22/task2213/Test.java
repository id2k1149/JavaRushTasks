package com.javarush.task.task22.task2213;

public class Test {
    static int height = 10;
    static int width = 5;

    static int[][] matrix = new int[][] {
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,1,1,0}
    };

    public static void main(String[] args) {
        print();
    }

    static void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix[y][x]==0) {
                    System.out.print(".");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
