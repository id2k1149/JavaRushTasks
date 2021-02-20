package com.javarush.task.task22.task2213;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static int height = 10;
    static int width = 5;

    static int[][] matrix = new int[][] {
            {1,1,1,1,1},
            {0,0,0,0,0},
            {0,0,1,1,0},
            {1,0,0,0,0},
            {1,1,0,0,0},
            {1,1,1,1,1},
            {1,1,1,0,0},
            {1,1,1,1,1},
            {1,1,1,1,0},
            {1,1,1,1,1}
    };

    public static void main(String[] args) {
        print();
        System.out.println("____________");
        removeFullLines();
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

    /**
     * Удаляем заполненные линии
     */
    static void removeFullLines() {
        //Создаем список для хранения линий
        List<int[]> list = new ArrayList<>();

        //Копируем все неполные линии в список.
        for (int y = 0; y < height; y++) {
            int count = 0;
            for (int x = 0; x < width; x++) {
                if (matrix[y][x] == 1) count++;
            }
            if (count < width) list.add(matrix[y]);
        }

        //Добавляем недостающие строки в начало списка.
        if (list.size() < height) {
            int newLines = height - list.size();
            for (int i = 0; i < newLines; i++) {
                list.add(i, new int[width]);
            }
        }

        //Преобразуем список обратно в матрицу
        for (int y = 0; y < height; y++) {
            matrix[y] = list.get(y);
        }
    }

}
