package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution_java {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1,1,0,1,1,1},
                {1,0,1,0,1,1},
                {1,1,1,1,0,0},
                {0,1,1,1,1,0},
                {1,1,1,1,1,0} };  // maxSquare = 9

        int[][] matrix2 = {
                {0,1,0,1,1,1},
                {1,1,1,0,1,1},
                {1,0,1,1,1,1},
                {0,1,1,0,1,1},
                {1,1,1,1,1,0} };  // maxSquare = 4

        int[][] matrix3 = {
                {0,1,1,1,1,0},
                {1,1,1,0,1,1},
                {1,1,0,0,0,0},
                {0,1,1,0,1,1},
                {1,0,0,0,0,1} };  // maxSquare = 4

        System.out.println("Must be 9: " + maxSquare(matrix1));
        System.out.println("Must be 4: " + maxSquare(matrix2));
        System.out.println("Must be 4: " + maxSquare(matrix3));

    }

    public static int maxSquare(int[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int[][] temp = new int[rows + 1][cols + 1];
        int maxLen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    temp[i][j] = Math.min(Math.min(temp[i][j - 1], temp[i - 1][j]), temp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(maxLen, temp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }
}
