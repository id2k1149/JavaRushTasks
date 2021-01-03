package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.util.ArrayList;

public class Solution_01 {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
//        int count2 = getRectangleCount(a2);
//        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int n = a.length;
        byte[][] b = new byte[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("i = " + i + " j = " + j + " a = " + a[i][j]);
                if (i < n - 1){
                    if (a[i][j] == 1 &&
                            (a[i + 1][j] == 1)) {
                        b[i][j] = 0;
                    }
                    else b[i][j] = a[i][j];
                }
                else {
                    if (a[i][j] == 1 &&
                            (b[i - 1][j] == 1)) {
                        b[i][j] = 0;
                    }
                    else b[i][j] = a[i][j];
                }
                System.out.println("i = " + i + " j = " + j + " b = " + b[i][j]);

            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println(" ");
        }
        return 0;
    }
}
