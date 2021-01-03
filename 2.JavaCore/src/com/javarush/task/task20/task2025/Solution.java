package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Алгоритмы-числа
*/

public class Solution {

    public static long[] getNumbers(long N) {
        if(N <= 0) return new long[0];
//        System.out.println(Long.MAX_VALUE);
//        Long.MAX_VALUE == 9 223 372 036 854 775 807; (19 digits)

        // двухмерный массив - матрица степеней
        long[][] power_matrix = new long[10][19];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 19; j++) {
                power_matrix[i][j] = Math.round(Math.pow(i, j+1));
            }
        }

        // массив уже вычисленных чисел армстронга
        long[] armstrong = {1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407,
                1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315,
                24678050, 24678051, 88593477,146511208, 472335975, 534494836, 912985153, 4679307774L,
                32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L, 49388550606L, 82693916578L,
                94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L, 21897142587612075L,
                35641594208964132L, 35875699062250035L, 1517841543307505039L, 3289582984443187032L,
                4498128791164624869L, 4929273885928088826L};


        ArrayList<Long>  my_list = new ArrayList<>();
        for (long each: armstrong) {
            if (each < N) my_list.add(each);
        }

        long[] result = new long[my_list.size()];
        for (int i = 0; i < my_list.size(); i++) {
            result[i] = my_list.get(i);
        }

        return result;
    }


    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
