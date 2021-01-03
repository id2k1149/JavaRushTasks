package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.util.ArrayList;

public class Solution {
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
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {

        int n = a.length;
        int m = n + 2;
        byte[][] b = new byte[m][m];
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == m -1 || j == 0 || j == m -1 ) b[i][j] = 0;
                else b[i][j] = a[i-1][j-1];
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (b[i][j] == 1 && b[i][j+1] == 0 && b[i+1][j] == 0){
                    count++;
                }
            }
        }

        return count;
    }
}
