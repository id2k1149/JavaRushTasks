package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, 8);
    }

    public static void printMatrix(int m, int n, int value) {
        System.out.println("Заполняем объектами int");
        short new_value = (short) value;
        printMatrix(m, n, new_value);
    }

    public static void printMatrix(int m, int n, short value) {
        System.out.println("Заполняем объектами short");
        byte new_value = (byte) value;
        printMatrix(m, n, new_value);
    }

    public static void printMatrix(int m, int n, byte value) {
        System.out.println("Заполняем объектами byte");
        long new_value = (long) value;
        printMatrix(m, n, new_value);
    }

    public static void printMatrix(int m, int n, long value) {
        System.out.println("Заполняем объектами long");
        double new_value = (double) value;
        printMatrix(m, n, new_value);
    }

    public static void printMatrix(int m, int n, double value) {
        System.out.println("Заполняем объектами double");
        char new_value = (char) value;
        printMatrix(m, n, new_value);
    }

    public static void printMatrix(int m, int n, char value) {
        System.out.println("Заполняем объектами char");
        String new_value = String.valueOf(value);
        printMatrix(m, n, new_value);
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        short new_m = (short) m;
        printMatrix(new_m, n, value);
    }

    public static void printMatrix(short m, int n, String value) {
        System.out.println("Заполняем объектами short m");
        short new_n = (short) n;
        int new_m = (int) m;
        printMatrix(new_m, new_n, value);
    }

    public static void printMatrix(int m, short n, String value) {
        System.out.println("Заполняем объектами short n");
        int new_n = (int) n;
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        System.out.println("Заполняем объектами Object");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

}
